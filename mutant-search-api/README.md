###### **ML-Challenge--Magneto API**

**Objetivo del API:**

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

**EndPoints API**

**_POST:_**
**Descripción** : Validar si un DAN dado es mutante o human

**URL**         : http://{server}:{port}/magneto/api/v1/mutant

**body**        : { "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}

**Respuestas esperadas:** HTTP 200 cuando el DNA es validado para mutante, HTTP 403 cuando el DNA es de humano.


**_GET:_**

**_Descripción :_** Retornar la relación entre el número de mutantes y el número de humanos

**URL**        : http://{server}:{port}/magneto/api/v1/stats
