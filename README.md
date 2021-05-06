# doulevo-user-service

#### Prerequisite
- Docker desktop
- Maven(3.3+)

#### 1. Building the project
Clone the parent project `https://github.com/ashutosh049/doulevo-boot-starter-parent.git`, build it as:-

In project root dir:-

`mvn clean install`

Now in doulevo-user-api , build the project `mvn clean install`

#### 2. Building the docker image
In project root dir:-

Replace the `github-userid` and `github-pasword` with your credentials and press enter.

```jshelllanguage
docker build --no-cache --build-arg APP_DOULEVO_GITHUB_API_USERNAME='<github-userid>' --build-arg APP_DOULEVO_GITHUB_API_PASSWORD='<github-pasword>'  --build-arg SPRING_PROFILES_ACTIVE='stag' --pull -t doulevo/doulevo-user-service:demo .
```


> Here we are just building the docker image to be run in local, we will NOT push the image to docker registry so that the credentials are not exposed and can safely be removed by removing the image, all in your local system.


#### 3. Run the container

```jshelllanguage
docker run -d --name=doulevo-user-service -p=2221:2221 doulevo/doulevo-user-service:demo
```

#### 4. Test the apis
 Application will start on port 2221 under context path 'doulevo-user-api'
 
 ###### Fetch random github users
 
 ```jshelllanguage
curl --location --request GET 'http://localhost:2221/doulevo-user-api/github/users'
```

 ###### Fetch github user with username
 
 ```jshelllanguage
curl --location --request GET 'http://localhost:2221/doulevo-user-api/github/users/ashutosh049'
```

 ###### Fetch/download github user's image with username

 
 ```jshelllanguage
curl --location --request GET 'http://localhost:2221/doulevo-user-api/github/users/ashutosh049/getAvatar'
```

> This you might want to test in postman for a better visibility

#### 5. Stop the container
 `docker stop doulevo-user-service`