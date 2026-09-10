package com.sole.api_documentos.security.controller;

import com.sole.api_documentos.repository.UsuarioRepository;
import com.sole.api_documentos.security.dto.JwtAuthResponseDTO;
import com.sole.api_documentos.security.dto.LoginDTO;
import com.sole.api_documentos.security.dto.LoginResponseDTO;
import com.sole.api_documentos.security.jwt.JwtGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    /*
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> authenticationUser(@RequestBody LoginDTO loginDto) {
        Authentication authenntication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenntication);

        String token = jwtGenerator.generateToken(authenntication);

        return new ResponseEntity<>( new JwtAuthResponseDTO(token), HttpStatus.OK);
    } */

    @PostMapping("/login")
    public ResponseEntity<?> authenticationUser(@RequestBody LoginDTO loginDto) {
        try {
            // 1. Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 2. Generar el token
            String token = jwtGenerator.generateToken(authentication);

            // 3. Extracción segura del username
            String user = "";
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                user = ((UserDetails) principal).getUsername();
            } else if (principal != null) {
                user = principal.toString();
            }

            // 4. Obtener el agente y almacén del usuario (una sola consulta)
            var usuario = usuarioRepository.findById(user);
            String agente  = usuario.map(u -> u.getAgente()).orElse("");
            String almacen = usuario.map(u -> u.getAlmacen()).orElse("");

            // 5. Crear la Cookie HttpOnly
            ResponseCookie springCookie = ResponseCookie.from("token", token)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(60 * 60)
                    .sameSite("Strict")
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                    .body(new LoginResponseDTO(user, agente, almacen));

        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            // AQUÍ CAERÁ SI LA CONTRASEÑA ES INCORRECTA
            System.out.println("Error de login: Contraseña incorrecta para el usuario " + loginDto.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");

        } catch (org.springframework.security.authentication.InternalAuthenticationServiceException e) {
            // AQUÍ CAERÁ SI EL USUARIO NO EXISTE EN LA BASE DE DATOS
            System.out.println("Error de login: El usuario no existe o hay un problema con UserDetailsService");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");

        } catch (Exception e) {
            // CUALQUIER OTRO ERROR IMPREVISTO
            System.out.println("Error inesperado durante el login: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<LoginResponseDTO> getCurrentSession(Authentication authentication){
        if(authentication == null || !authentication.isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String user = "";
        Object principal = authentication.getPrincipal();
        if(principal instanceof  UserDetails) {
            user = ((UserDetails) principal).getUsername();
        }else if (principal != null){
            user = principal.toString();
        }

        // Obtener el agente y almacén del usuario (una sola consulta)
        var usuario = usuarioRepository.findById(user);
        String agente  = usuario.map(u -> u.getAgente()).orElse("");
        String almacen = usuario.map(u -> u.getAlmacen()).orElse("");

        return  ResponseEntity.ok(new LoginResponseDTO(user, agente, almacen));
    }


}
