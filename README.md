[![Build Status](https://travis-ci.com/muriloalvesdev/Simian.svg?token=ioemJXEZtsUqgszw2Nq4&branch=main)](https://travis-ci.com/muriloalvesdev/simian)
[![codecov](https://codecov.io/gh/muriloalvesdev/Simian/branch/main/graph/badge.svg?token=9BOGVW2OBM)](https://codecov.io/gh/muriloalvesdev/Simian)

## Simian

## License
[Apache License 2.0](https://github.com/muriloalvesdev/buy-manager/blob/master/LICENSE)

## Tecnologias 

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework de Desenvolvimento para a Linguagem Java.

- [Lombok](https://projectlombok.org/) - Biblioteca Java focada em produtividade e redução de código boilerplate que, por meio de anotações adicionadas ao nosso código, ensinamos o compilador (maven ou gradle) durante o processo de compilação a criar código Java.

- [JUnit5](https://junit.org/junit5/) - Framework facilita a criação e manutenção do código para a automação de testes com apresentação dos resultados.

- [Mockito](https://site.mockito.org/) - Estrutura de teste de código aberto para Java liberada sob a licença MIT. A estrutura permite a criação de objetos duplos de teste em testes de unidade automatizados com o objetivo de desenvolvimento orientado a teste ou desenvolvimento orientado a comportamento.

- [PostgreSQL](https://www.postgresql.org/download/) - Banco de dados.

- [Hibernate](https://hibernate.org/) - Framework para persistência de dados. (ORM)

- [JPA](https://hibernate.org/orm/) - Especificação do Java que dita como os Frameworks ORM devem ser implementados.

- [Docker](https://www.docker.com/) - Plataforma open source que facilita a criação e administração de ambientes isolados. Ele possibilita o empacotamento de uma aplicação ou ambiente dentro de um container, se tornando portátil para qualquer outro host que contenha o Docker instalado.

- [Swagger](https://swagger.io/) - Essencialmente uma linguagem de descrição de interface para descrever APIs RESTful expressas usando JSON.


## Para compilar e executar o projeto você precisa ter instalado

 - [Maven](https://maven.apache.org/) - Ferramenta de automação de compilação utilizada primariamente em projetos Java.
 - [Docker](https://docs.docker.com/get-docker/) - Execução de aplicativos de containers.


## Instalação

 - Clone o projeto: `$ git clone https://github.com/muriloalvesdev/simian.git`
 - Após concluir o Download do projeto, acesse o diretório do mesmo: `$ cd simian/`
 
```
OBS: Sobre a instalação existem duas formas, abaixo está o passo a passo, sobre como realizar este procedimento. 
Mas caso você queira instalar rapidamente o projeto, basta executar o script contido na raiz do projeto: 
$ sudo chmod u+x docker-run.sh
$ sudo ./docker-run.sh
```
 
### Instando dependências e executando os testes com Maven:

- Para instalar as dependências e executar os testes, utilize o comando: `$ mvn clean install -Ptest`

## Docker - Construindo a aplicação

- Execute o script `$ sudo ./docker-run.sh` para construir a aplicação. 
Este script compila o projeto com o profile de `PROD`, cria uma imagem Docker da aplicação e por fim utiliza o 
Docker Compose para baixar a imagem do PostgreSQL (banco de dados) e sobe a aplicação com a imagem do projeto gerada a partir do docker build (Dockerfile).
- Após executar o script você ja deve ter a aplicação sendo executada.

```
OBS: O projeto é executado na porta 8080 e o banco de dados é executado na porta 5432, verifique se essas 
portas estão disponíveis caso ocorra algum erro. Tenha certeza de que você está com o Docker e o 
Maven instalados para que você não tenha problemas ao compilar, testar e executar a aplicação.
```

## Ainda com Docker - Deixei a imagem disponível para Download no [DockerHub](https://hub.docker.com/r/muriloalvesdev/simian)
Caso você queira apenas baixar a imagem e executar a aplicação, basta seguir os passos abaixo:
`OBS: Você precisa ter o PostgreSQL pré instalado com um database chamado "simian" .`

- Baixe a imagem Docker, utilizando o comando: `$ docker pull muriloalvesdev/simian`
- Execute o comando `$ docker run <id_imagem>` para executar o container.
- E agora você ja deve ter a aplicação sendo executada.

## Hospedagem da aplicação
Aplicação hospedada no Heroku, você pode realizar os request através desta url base: https://melibr-simian.herokuapp.com/
```
Lembrando que o primeiro request sempre será mais lento no Heroku, pois quando a aplicação fica sem receber nenhuma requisição 
(dentro de 30 min deste o ultimo request ou deploy) a aplicação é derrubada, pois o plano utilizado para esta hospedagem é FREE.
```
