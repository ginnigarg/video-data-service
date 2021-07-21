# video-data-service

## Overview
The project fetches data regarding different videos from Youtube V3 Search API every 10 secs and saves the data in the database. <br>
The data saved in database can be fetched using the API mentioned below which would return different attributes such as title, description, thumbnails, etc.

## How to create docker image for the service(Optional)
1. Clone the project
2. If you wish to push docker image to your own docker registry, modify the configuration of docker plugin in pom.xml to set the destination docker repository
3. "mvn clean install" or "mvn clean install -Ddockerfile.skip" (You need to be in directory with pom.xml file)<br><br>
The above step can be skipped and already built docker image (guneetginnigarg/video-data-service:1.0.0) can be used

## How to run the service
1. Move to the docker directory in the project
2. Add your youtube API key in properties/application.properties. The property to be used "google.youtube.api-key"
3. Docker compose already has an inbuilt postgresql container but if you wish to use our own database, modify the following configurations in properties/application.properties
<br>a. spring.datasource.url -- URL of the postgresql database.
<br>b. spring.datasource.username -- Username of the postgresql database. 
<br>c. spring.datasource.password -- Password of the postgresql database.
3. Run the "docker-compose up -d" (You need to be docker directory) <br><br>

## API to get the video data from Database
curl --location --request GET 'http://localhost:8080/v1/videos'
<br><br>
The response in paginated.
<br>
Query Parameters(All Properties are optional):
1. pageNumber -- Page Number of the response -- Default value is 1. Page Number starts with 1
2. pageSize -- Offset for the page in the response -- Default value is 10. Page Size can not be greater than 1000
3. title -- Return the data with the mentioned title.
4. description -- Return the data with the mentioned description.
