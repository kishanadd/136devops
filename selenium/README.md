### Install Selenium Hub.

For this you need to have docker already installed. 

You can follow the following process to install docker.

https://gitlab.com/batch37/docker/blob/master/README.md


Install SeleniumHub using docker, But in order to run component which is required we better run it with docker compose.

Run the following steps to install docker-compose and install selenium Hub with it. 

```
$ sudo curl -L "https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
$ sudo chmod +x /usr/local/bin/docker-compose
$ docker-compose version 
```

Create a file `docker-compose.yml` with following content.

```
# To execute this docker-compose yml file use `docker-compose -f <file_name> up`
# Add the `-d` flag at the end for detached execution
version: "3"
services:
  selenium-hub:
    image: selenium/hub:3.141.59-neon
    container_name: selenium-hub
    ports:
      - "4444:4444"
  chrome:
    image: selenium/node-chrome:3.141.59-neon
    volumes:
      - /dev/shm:/dev/shm
    depends_on:
      - selenium-hub
    environment:
      - HUB_HOST=selenium-hub
      - HUB_PORT=4444
  firefox:
    image: selenium/node-firefox:3.141.59-neon
    volumes:
      - /dev/shm:/dev/shm
    depends_on:
      - selenium-hub
    environment:
      - HUB_HOST=selenium-hub
      - HUB_PORT=4444
```

Run the following command. 

```
$ docker-compose up -d
```

mvn clean install "-Dremote=true" "-DseleniumGridURL=http://IP_ADDRESS:4444/hub" "-Dbrowser=Chrome" "-Doverwrite.binaries=true"
##
