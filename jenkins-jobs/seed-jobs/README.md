# Seed Job Maker.

Run the following commands to setup seed-job-maker.

```shell 
$ sudo yum install maven git -y 
$ git clone https://github.com/rsacramento18/jenkinsxml2jobdsl.git
$ cd jenkinsxml2jobdsl
$ ./gradlew build
```

Those above commans will generate a jar file and all we need to use is that jar file. 

Following is the syntax to use it.

`java -jar build/libs/jenkinsxml2jobdsl.jar -u <jenkins username> -a <jenkins api token> -j <jenkins server> -p <jenkins port> job1 job2 ... jobN`

Following are the examples


```shell 
$ java -jar build/libs/jenkinsxml2jobdsl.jar -u admin -a admin -j 18.191.206.52 -p 8080 free-style-template
```