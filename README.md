```
docker built -t playlistservice:dev .

docker network create --driver bridge playlistservice-network

docker run --name playlistservice-postgres --network playlistservice-network -e POSTGRES_PASSWORD=open_admin -e POSTGRES_DB=postgres -d postgres

docker run -d -p 1000:8080 --name playlistservice  -e PORT=8080 --rm playlistservice:dev

docker run -d -p 1000:8080 --name playlistservice --network playlistservice-network -e PORT=8080 -e "SPRING_PROFILES_ACTIVE=docker" --rm playlistservice:dev


```