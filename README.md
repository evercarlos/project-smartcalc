## MICROSERVICIO DE CÁLCULO DE PORCENTAJE ```SMARTCALC```

Este microservicio tiene como objetivo realizar cálculos de porcentaje basados en datos obtenidos de un servicio externo. 
El valor calculado se almacena en caché utilizando Redis para optimizar el rendimiento y reducir las llamadas innecesarias
 al servicio externo`

### FUNCIONES

- ``Almacenamiento en Caché``: El porcentaje calculado por el servicio externo se guarda en caché en Redis.
- ``Duración del Cache``: El valor en caché se mantiene durante un período de 30 minutos

### REQUISITOS

- Java 21
- Spring Boot 3.2.5
- Docker

### COMPONENTES

- JAVA: 21
- SPRING BOOT: 3.2.5
- DOCKER
- Redis
- Retry

### INSTALACIÓN

- Clonar el repositorio:

```bash
  git clone https://github.com/evercarlos/project-smartcalc.git
 ```

- Entrar en el directorio del proyecto:
  ```bash
  cd smartcalc
  ```
- Compilar el proyecto
   ```bash
    mvn clean install
  ```
- Construir y levantar los contenedores con Docker Compose
   ```bash
    docker-compose up --build -d
  ```
### URL DEL MICROSERVICIO Y DOCUMENTACIÓN SWAGGER
- http://localhost:9002
### ENDPOINTS DISPONIBLES
1. Lista de historial de llamadas con paginación
   
Realiza una consulta para obtener el historial de las llamadas con paginación.

 ```bash
curl --location 'http://localhost:8092/api/v1/histories/withPagination?page=0&size=10&sort=id%2Casc'
 ```
Respuesta:

```json
{
  "content": [
    {
      "id": 68,
      "date": "2025-04-30T14:16:34",
      "endpoint": "http://micro_mock:8080/calculator/findPercentage",
      "parameterJson": "{\"numberOne\":10.0,\"numberTwo\":20.0}",
      "response": 0,
      "error": "Unable to connect to Redis"
    },
    {
      "id": 69,
      "date": "2025-04-30T14:38:07",
      "endpoint": "http://micro_mock:8080/calculator/findPercentage",
      "parameterJson": "{\"numberOne\":10.0,\"numberTwo\":20.0}",
      "response": 33,
      "error": null
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 40,
    "sort": [
      {
        "direction": "ASC",
        "property": "id",
        "ignoreCase": false,
        "nullHandling": "NATIVE",
        "ascending": true,
        "descending": false
      }
    ],
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 6,
  "first": true,
  "size": 40,
  "number": 0,
  "sort": [
    {
      "direction": "ASC",
      "property": "id",
      "ignoreCase": false,
      "nullHandling": "NATIVE",
      "ascending": true,
      "descending": false
    }
  ],
  "numberOfElements": 6,
  "empty": false
}
```
2. Lista de historial de llamadas sin paginación

Realiza una consulta para obtener el historial de todas las llamadas sin paginación.

 ```bash
curl --location 'http://localhost:8092/api/v1/histories'
 ```
Respuesta:

```json
[
  {
    "id": 68,
    "date": "2025-04-30T14:16:34",
    "endpoint": "http://micro_mock:8080/calculator/findPercentage",
    "parameterJson": "{\"numberOne\":10.0,\"numberTwo\":20.0}",
    "response": 0,
    "error": "Unable to connect to Redis"
  },
  {
    "id": 69,
    "date": "2025-04-30T14:38:07",
    "endpoint": "http://micro_mock:8080/calculator/findPercentage",
    "parameterJson": "{\"numberOne\":10.0,\"numberTwo\":20.0}",
    "response": 33,
    "error": null
  }
]
```

3. Generación de calculo con percentaje dinámico

Realiza una consulta para calcular el porcentaje dinámicamente entre dos números.

 ```bash
curl --location 'http://localhost:9002/api/v1/calculator?numberOne=20&numberTwo=80'
 ```
Respuesta:

 ```json
  110
 ```