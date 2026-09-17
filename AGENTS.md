# Project Instructions: API Documentos (Spring Boot)

## Objetivo
Mantener y escalar el backend de documentos, respetando las convenciones específicas adoptadas para su estructura y conexión de base de datos.

## 🛠 Convenciones Estrictas del Proyecto (NO VIOLAR)

### 1. Nomenclatura del Paquete DTO
- Todos los Data Transfer Objects (DTOs) residen obligatoriamente en un paquete llamado **`DTO` (completamente en mayúsculas)**, ej: `com.sole.api_documentos.DTO`.
- Evita utilizar minúsculas para este paquete en particular para no romper imports existentes.

### 2. APIs y Versionado
- Todos los controladores (Controllers) están expuestos bajo el prefijo de versionado estricto: **`/api/v2/`**. 
- Todo nuevo endpoint debe respetar esta ruta base en su `@RequestMapping`.

### 3. Stack Tecnológico Específico
- El proyecto utiliza características avanzadas (Java 25 y dependencias Spring Boot muy modernas 4.1.0).
- La conexión a base de datos es exclusivamente hacia **Microsoft SQL Server** (`mssql-jdbc`). Evita asumir comportamientos o dialectos de PostgreSQL o MySQL al escribir queries complejas, usa la sintaxis estándar JPQL o características compatibles de SQL Server.

## 🧠 Arquitectura Base y Skills Globales a utilizar
Al realizar cambios arquitectónicos, básate en las siguientes Skills globales (disponibles en `.agents/skills/`):
- `springboot-api-design` (Controladores, `ResponseEntity`, paginación).
- `springboot-business-logic` (Servicios, Interfaces/Impl).
- `springboot-data-mapping` (JPA, MapStruct para mapear Entity <-> DTO).
- `springboot-security-jwt` (Autenticación sin estado).

## Antes de modificar código
1. No modifiques ni refactorices código que ya funciona a menos que sea estrictamente solicitado (como arreglar nombres fuera de convención si el usuario lo pide).
2. Valida siempre que las interfaces (Services o Mappers) sean consistentes con sus implementaciones.

## Calidad
Antes de terminar, comprobar si el build de maven compila exitosamente, ya que MapStruct genera código en tiempo de compilación.
