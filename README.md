# DNA Analyzer API 

[![Java CI with Maven](https://github.com/RayDiazVega/dna-analyzer-api/actions/workflows/pipeline.yml/badge.svg?branch=main)](https://github.com/RayDiazVega/dna-analyzer-api/actions/workflows/pipeline.yml) ![Coverage](.github/badges/jacoco.svg) ![Branches](.github/badges/branches.svg)




API REST para identificar si un humano es mutante según su ADN y proveer estadísticas de las verificaciones de ADN.

- Enlace al problema planteado [aqui](.github/docs/Examen_Mercadolibre_-_Mutantes.pdf)

## Instrucciones de ejecucion

Instalar previamente las siguientes herramientas:

```text
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

Abrir Postman, descargar e importar la
coleccion [dna-analyzer-api](.github/docs/dna-analyzer-api.postman_collection.json).

Si se quiere probar la API de manera local use los endpoints `POST /mutant/` y `GET /stats`  en la
carpeta localhost de la coleccion o ejecutar los siguientes comandos:

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

Tambien se puede probar de manera local accediendo al Swagger
en http://localhost:8080/dna-analyzer-api/swagger-ui/index.html#/

Para probar la API hosteada en AWS use los endpoints `POST /mutant/` y `GET /stats`  en la carpeta
AWS Endpoints de la coleccion o ejecutar los comandos anteriores cambiando `localhost:8080`
por `dnaanalyzerapi-env.eba-pbepzfyx.sa-east-1.elasticbeanstalk.com`:
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

Tambien se puede probar la API hosteada en AWS accediendo al Swagger
en http://dnaanalyzerapi-env.eba-pbepzfyx.sa-east-1.elasticbeanstalk.com/dna-analyzer-api/swagger-ui/index.html#/

### Operaciones

|  ENDPOINT  |              POST              |                   GET                   |
|:----------:|:------------------------------:|:---------------------------------------:|
| `/mutant/` | Valid DNA sequence, return 200 |         Not allowed, return 400         |
|  `/stats`  |    Not allowed, return 400     | Get verification statistics, return 200 |

## Pruebas

Se preparo un conjunto de pruebas unitarias en la ruta `src/test` con una cobertura del **84%**, se
pueden ejecutar las pruebas en la raiz del proyecto con el comando:

```sh
mvn test
```

Adicionalmente se realizo
un [tets de carga](.github/docs/dna-analyzer-api.postman_load_testing.json) donde se hicieron 200
peticiones a la API con un tiempo de respuesta promedio de 333.37ms y en un tiempo total de 65.131s

