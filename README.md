# Test CIN

Este proyecto es una aplicación de ejemplo para gestionar órdenes API REST en Spring Boot.

## Requisitos

- Java 17 requerido

## Configuración del Proyecto

1. Abre el proyecto con IntelliJ Community (o el entorno que prefieras).
2. Espera a que se arme el proyecto.

## Ejecución de la Aplicación

1. Ejecuta el archivo `OrdersApplication.java`:
    - Haz clic derecho en el archivo.
    - Selecciona `Run` en el menú contextual.

## Pruebas de la Aplicación

Puedes probar la aplicación utilizando Postman o tu navegador:

```http
GET http://localhost:8080/orders
```
## Nota
1. Puedes instalar o cambiar el proyecto como quieras.
2. El proyecto usa por defecto la base de datos H2, no necesitas configurar nada.
3. Si quieres resetear la base de datos elimina la carpeta 'data' y vuelve a correr el proyecto.