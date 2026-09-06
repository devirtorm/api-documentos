package com.sole.api_documentos.security.service;


import com.sole.api_documentos.entity.Usuario;
import com.sole.api_documentos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow( () ->
                        new UsernameNotFoundException("Usuario no encontrado con esa clave: " + id ) );


        return new org.springframework.security.core.userdetails.User(
                usuario.getClave(), usuario.getContrasena(), Collections.emptyList()
        );
    }
}
