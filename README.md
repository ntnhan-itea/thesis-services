# Step 1: Run container docker
`docker run --name thesis-db-postgres -e POSTGRES_DB=thesis-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -v C:/opt/workspace/projects/thesis/my-data:/var/lib/postgresql/data -p 5432:5432 -d postgres:13.6`

# Step 2: Connect DB by tool DBeaver 
    - Host: localhost
    - Port: 5432
    - Database: thesis-db
    - Username: admin
    - Password: 123

# Step 3: in project
    + `mvn clean install`
    + `mvn spring-boot:run`