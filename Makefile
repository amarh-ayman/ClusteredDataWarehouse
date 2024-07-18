export db_type=POSTGRESSQL
export db_driver=org.postgresql.Driver
export SPRING_DATASOURCE_URL=jdbc:postgresql://172.17.0.3:5432/test
export SPRING_DATASOURCE_USERNAME=test
export SPRING_DATASOURCE_PASSWORD=test
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64/

run:
	java -jar target/ClusteredDataWarehouse-1.0-SNAPSHOT.jar

build:
	mvn clean install

build-run:
	make build
	make run