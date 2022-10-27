@REM mvn spring-boot:run

docker network create theis-network || true &^
docker run --name thesis-db-postgres -e POSTGRES_DB=thesis-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -p 5432:5432 --net thesis-network -d postgres:13.6 &^

mvn clean install &^
docker build -t management -f Dockerfile.dev . &^
docker run --name management --net thesis-network -p 8080:8080 management -d