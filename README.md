# PACT Consumer API

## Prerequsites:

* [Docker](https://docs.docker.com/get-docker/)
* [OpenJDK 11](https://jdk.java.net/archive/)
* [Maven](https://maven.apache.org/download.cgi)

## Overview

This POC contains two API Services and PostgresQL Database.

The Consumer API exposes 2 endpoints:
* `POST /users` - Create a user (must provide an `email` and `name`). This will check the provided `email` against the Provider API.
* `GET /users` - Get all users

The Provider API exposes 1 endpoint:
* `GET is-eligible?email=XXX` - Provide an email. If contains `@gmail.com` then return `true`, else `false`.

## Docker Postgres Setup

Create Docker container with Postgres database:

    docker create --name postgres-demo -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres:11.5-alpine

Start container:

    docker start postgres-demo

Stop container:

    docker stop postgres-demo

Connect to PSQL prompt from docker:

    docker exec -it postgres-demo psql -U postgres

Create the Database:

    psql> create database pact_poc;

Setup the Schema (quit psql first):

    docker cp schema.sql postgres-demo:/schema.sql
    docker exec -it postgres-demo psql -d pact_poc -f schema.sql -U postgres

## Consumer API

To run:

    cd pact-consumer-api
    mvn spring-boot:run

To access:

    localhost:8080

## Provider API

To run:

    cd pact-provided-api
    mvn spring-boot:run

To access:

    localhost:8081
