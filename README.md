# DNA Analyzer API 

[![Java CI with Maven](https://github.com/RayDiazVega/dna-analyzer-api/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/RayDiazVega/dna-analyzer-api/actions/workflows/build.yml) ![code quality score](https://api.codiga.io/project/32332/score/svg) ![.github/workflows/coverage.yml](https://github.com/RayDiazVega/dna-analyzer-api/blob/main/.github/badges/coverage.svg)


API REST para identificar si un humano es mutante según su ADN y proveer estadísticas de las verificaciones de ADN.

- Enlace al problema planteado [aqui](https://github.com/RayDiazVega/dna-analyzer-api/blob/main/static/Examen_Mercadolibre_-_Mutantes.pdf)

## Instrucciones de ejecucion

Instalar previamente las siguientes herramientas:
```sh
Java 11
Maven 3.8.5
Git 2.35.1
Postman
```

Clonar el repositorio con el comando:
```sh
git clone https://github.com/RayDiazVega/dna-analyzer-api.git
```

En la carpeta del proyecto ejecutar los comandos:
```sh
mvn clean package
java -jar target/dna-analyzer-api-0.0.1-SNAPSHOT.jar
```

Abrir Postman, descargar e importar la coleccion [dna-analyzer-api](https://github.com/RayDiazVega/dna-analyzer-api/blob/main/static/dna-analyzer-api.postman_collection.json). 

Si se quiere probar la API de manera local use los endpoints `POST /mutant/` y `GET /stats`  en la carpeta localhost de la coleccion o ejecutar los siguientes comandos: 
```sh
curl --location --request POST 'http://localhost:8080/dna-analyzer-api/mutant/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}'
```
 ```sh
curl --location --request GET 'http://localhost:8080/dna-analyzer-api/stats'
```

Tambien se puede probar de manera local accediendo al Swagger en http://localhost:8080/dna-analyzer-api/swagger-ui/index.html#/

Para probar la API hosteada en AWS use los endpoints `POST /mutant/` y `GET /stats`  en la carpeta AWS Endpoints de la coleccion o ejecutar los comandos anteriores cambiando 
`localhost:8080` por `dnaanalyzerapi-env.eba-pbepzfyx.sa-east-1.elasticbeanstalk.com`:
```sh
curl --location --request POST 'http://dnaanalyzerapi-env.eba-pbepzfyx.sa-east-1.elasticbeanstalk.com/dna-analyzer-api/mutant/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}'
```
 ```sh
curl --location --request GET 'http://dnaanalyzerapi-env.eba-pbepzfyx.sa-east-1.elasticbeanstalk.com/dna-analyzer-api/stats'
```
Tambien se puede probar la API hosteada en AWS accediendo al Swagger en http://dnaanalyzerapi-env.eba-pbepzfyx.sa-east-1.elasticbeanstalk.com/dna-analyzer-api/swagger-ui/index.html#/

