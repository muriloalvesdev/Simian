mvn clean install -DskipTests -Pprod && docker build -t muriloalvesdev/simian . && docker-compose up
