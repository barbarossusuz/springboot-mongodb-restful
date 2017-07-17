# Maven  Spring Boot - MongoDb project


#### What's Need
* Maven
* SpringBoot
* Mongodb server (to install: `brew install mongodb`) (to run: `mongod` )
* Robomongo (mongodb management tool)

#### How To Run
* Check application.properties for database configuration
* Maven clear - install and package(for generating query classes in target directory with querydsl library) .
* Run project with SpringBoot application class(it will create database).
* To set or get data use restful services


### Modules (What's Inside)
#### Mongodb
Basic crud operation and some query operations like;
"find hotels with pricePerNight with given value and at least 1 reviews with a rating greater than with given value".


#### Mongodb2
Image operations using Gridfs with restapi.
Upload, delete and download image from mangodb.
