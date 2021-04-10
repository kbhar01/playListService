
# Playlist Service

<p align="center">
  <a href="#">
    <img src="./img/img.png" alt="playlist logo" width="247" height="73">
  </a>
</p>

<h3 align="center">Playlist Service (Back-End Spring Boot REST API)</h3>

<p align="center">
  Playlist is a service where the user can go and create song playlists with multiple songs of their choice.
  <br>

  <br>
</p>

## Table of contents

- [Technology Used](#technology-used)
- [Installation Instruction](#installation-instruction)
- [Auto-Deploy Instructions](#auto-deploy-instructions)
- [API](#api)
- [What's Included](#whats-included)
- [Creators](#developers)


## Technology Used

- Gradle as Build Tool
- Spring boot is used for the backend Services.
- H2 database for Tests
- Postgres database to store the data.
- Junit and Mockito for Tests.
- Deployed on Heroku

## Installation Instruction

Software Requirement:
1. Gradle ([install](https://gradle.org/install/))

2. Java (minimum java 8) ([install](https://www.oracle.com/java/technologies/javase-downloads.html))

3. Docker ([install](https://docs.docker.com/get-docker/))

4. Heroku CLI - Optional for Deployment. ([install](https://devcenter.heroku.com/articles/heroku-cli))
   Follow these easy step:


1. Clone the repository:
     ```
     https://github.com/kbhar01/playListService.git
     ```
2. Execute this on command line:

     ```shell
     $ cd playlistService
     $ docker build -t <your email>/playlistService .
     $ docker network create --driver bridge playlistService-network
     $ docker run -d -p 5432:5432 -e POSTGRES_USER=devuser -e  POSTGRES_PASSWORD=cog -e POSTGRES_DB=playlistdb --network playlistService-network --name playlistServicepostgres postgres
     $ docker run -d -p 8081:8080 -e SPRING_PROFILES_ACTIVE=docker --name playlistService <your email>/playlistService
     ```
3. Heroku Deployment (Heroku CLI Required.)

   ```shell
   $ heroku login
   $ heroku create
   $ heroku git:remote -a
   $ heroku container:login
   $ heroku container:push web
   $ heroku container:release web
   ```


4. Enjoy

## Auto-Deploy Instructions
Follow these to deploy the container automatically.
Files Required:
- Dockerfile
  Steps:

1. Login into heroku.
```shell
heroku login
```

2. Add Heroku Git Remote
```shell
# playlist-service is my app name. Please use yours 
heroku git:remote -a playlistService 
```

3. Use Heroku Stack to set heroku.yml
```shell
heroku stack:set container
```

4. Commit and Push into heroku remote.
```shell
git add --all 
git commit -m "heroku commit"
git push heroku master 
```

5. Open App
```shell
heroku open
```

Hint: For auto deploy please follow step 4.
## Sample Server
Feel free to play with our sample server.

-  ([Server# 1](https://zn-playlistservice.herokuapp.com/))

## API
```text
Base URL: /, Version: 1.1, local port: 8081

Default request content-types: application/json

Default response content-types: application/json

Schemes: http 
```

## API Reference
<table style="
    width: 100%;
    max-width: 100%;
    margin-bottom: 20px;
    border: 1px solid #ddd;
    border-collapse: collapse;
    border-spacing: 0;
    background-color: transparent;
    display: table;
">
    <thead>
    <tr>
        <th>Path</th>
        <th>Operation</th>
        <th>Description</th>
        <th>Controller -> Method</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td style="border: 1px solid #ddd;padding: 5px;" rowspan="2">
            <a href="#summary">/</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <a href="#get-entries">GET</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>Get list of playlists. </p>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>PlayListController -> getPlayLists()</p>
        </td>
    </tr>
    <tr>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <a href="#post-comment">POST</a>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>Add a new Playlist</p>
        </td>
        <td style="border: 1px solid #ddd;padding: 5px;">
            <p>PlayListController -> addNewPlayList()</p>
        </td>
    </tr>
    </tbody>
</table>
#### API Details

#### POST /
Response Header
```text
    Status: 201 CREATED
```
Request Body
```json5
{
   "name":"Playlist Name",
   "songList":["Song Name 1", "Song Name 2"]
}
```

Response Header
```text
[Content-Type:"application/json"]
```

Response Body
```json5
{
  "message": "Playlist is Successfully Created."
}
```

#### GET /
Response Header
```text
    Status: 200 OK
```

Response Body
```json5
[
   {
      "name":"Playlist 1",
      "songList":["Song 1","Song 2"]
   },
   {
      "name":"Playlist 2",
      "comment":"Song 3"
   }
]
```

## What's included

Within the download you'll find the following directories and files, logically grouping common assets and providing both compiled and minified variations. You'll see something like this:

<details>
<summary>
File Tree.
</summary>


```text
│   .gitignore
│   build.gradle
│   Dockerfile
│   gradlew
│   gradlew.bat
│   Readme.md
│   settings.gradle
└───src
    ├───docs
    │   └───asciidocs
    ├───main
    │   ├───generated
    │   ├───java
    │   │   └───com
    │   │       └───cognizant
    │   │           └───playlistService
    │   │               ├───controller
    │   │               ├───entity
    │   │               ├───repository
    │   │               ├───request
    │   │               ├───response
    │   │               └───service
    │   └───resources
    │       ├───static
    │       └───templates
    └───test
        └───java
            └───com
                └───cognizant
                    └───playlistService
                        ├───integrationTest
                        └───unitTest


```

</details>


## Developers
**Zackry Neagley**

- <https://github.com/zaneagley>

**Koustav Bhar**

- <https://github.com/kbhar01>


