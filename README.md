# service-api-gateway

#### Functionalies
A rest API end point for the RPC backend services.

#### Requirements
Java 1.8 & above <br />
Maven <br />

#### Instructions to run the application

1. Go to the source folder
2. Build the project with command - ```mvn clean package```
4. Run the jar generated inside target folder with command, <br /> 
```java -jar target/service-api-gateway-1.0-SNAPSHOT.jar server service-config.yml```

#### Supported end points
1. ```GET BASE_URL/youtube-api/search?type=video&q=query&page=1&limit=5```
- Fetches videos from YouTube APIs if not present in the database in the reverse chronological order by it's publishing time.

2. GET ```BASE_URL/youtube-api/search?type=video_tags&q=query&page=1&limit=5```
- Fetches videos from the database that best matches with the input query in the reverse chronological order by it's publishing time..
