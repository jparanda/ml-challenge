# ML-Challenge Magneto API

## Objetivo del API

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

### Pre-requisitos

* Maven 3.6.1
* JDK 8 +
* Docker y Docker Compose

## Ejecutar test unitarios

Solo tendras que ejecutar el comando:

```
mvn clean test
```

## EndPoints API Descripción

* **POST Request:**
    * Descripción : Validar si un DAN dado es mutante o human
    * URL : http://{server}:{port}/magneto/api/v1/mutant
    * Body Request : 
    { "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
    * Respuesta experada :
        * HTTP 200 : cuando el DNA es valido para mutante.
        * HTTP 403 : cuando el DNA es valido para humano, es decir no es mutante.
        
* **GET Request:**
    * Descripción : Retorna la relación entre el número de mutantes y el número de humanos
    * URL : http://{server}:{port}/magneto/api/v1/stats
    * Respuesta experada :
        * HTTP 200 : Para respuesta correcta y {"count_mutant_dna": 40, "count_human_dna": 100, "ratio": 0.4} 
        * HTTP 204 : Cuando no hay contenido en la base de datos.

## Ejecutar la aplicaciòn
* Fork del repositorio https://github.com/jparanda/ml-challenge
* Clone ambiente local
* Ubicarse en el folder local del repositorio:
```
cd /ml-challenge/mutant-search-api
```
* Ejecutar docker build para construir la imagen del API :
```
docker build -t magneto-api-image .
```
* Ejecutar docker compose:
```
docker-compose up
```
