# video-data-service

## How to create docker image for the service
1. Clone the project
2. Modify the configuration of docker plugin in pom.xml to set the destination docker repository (If required)
3. "mvn clean install" or "mvn clean install -Ddockerfile.skip" (You need to be in directory with pom.xml file)<br><br>
The above step can be skipped and already built docker image (guneetginnigarg/video-data-service:0.0.1-SNAPSHOT) can be used

## How to run the service
1. Move to the docker directory in the project
2. Modify the following configurations in properties/application.properties
<br>a. spring.datasource.url -- URL of the postgresql database.
<br>b. spring.datasource.username -- Username of the postgresql database. 
<br>c. spring.datasource.password -- Password of the postgresql database.
<br>d. google.youtube.api-key -- Youtube API v3 API Key
3. Run the "docker-compose up -d" (You need to be docker directory) 
Datasource properties can be ignored if required. Docker compose already has a inbuilt postgresql container

