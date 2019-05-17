# elk

## Installation 

```
curl -s https://raw.githubusercontent.com/linuxautomations/elkstack/master/setup.sh | sudo bash 
```


## Grok Filter Links

https://grokdebug.herokuapp.com/

https://raw.githubusercontent.com/elastic/logstash/v1.4.2/patterns/grok-patterns



----------------------------------
### Single File Configuration
----------------------------------

Filebeat Config:

```
filebeat.prospectors:
- input_type: log
  paths:
    - /home/student/apache-tomcat-8.5.38/logs/catalina.out
output.logstash:
  hosts: ["172.31.1.81:5044"]
```

LogStash Config:

```
input {
  beats {
    port => 5044
    host => "0.0.0.0"
  }
}

filter {
  grok {
    match => { "message" => "%{MONTHDAY:Monthday}-%{MONTH:Month}-%{YEAR:Year} %{HOUR:Hour}:%{MINUTE:Minute}:%{SECOND:Second}.%{INT:MicroSeconds} %{WORD:Severity} %{GREEDYDATA}" }
  }
}

output {
  elasticsearch {
    hosts => ["localhost:9200"]
    index => "catalina-%{+YYYY.MM.dd}"
  }
}

```

----------------------------------
### Multiple Line Configuration
----------------------------------

Filebeat config:

```
filebeat.prospectors:
- input_type: log
  paths:
    - /home/student/apache-tomcat-8.5.38/logs/catalina.out
  multiline.pattern: "^[0-9]{2}-"
  multiline.negate: true
  multiline.match: after
output.logstash:
  hosts: ["172.31.1.81:5044"]
  
```



----------------------------------
### Multiple File Configuration
----------------------------------


FileBeat Config:

```
filebeat.prospectors:
- input_type: log
  paths:
    - /home/student/apache-tomcat-8.5.38/logs/catalina.out
  multiline.pattern: "^[0-9]{2}-"
  multiline.negate: true
  multiline.match: after
  fields:
    logtype: java
- input_type: log
  paths:
    - /var/log/secure
  fields:
    logtype: secure
output.logstash:
  hosts: ["172.31.1.81:5044"]
```



LogStash Config:

```
input {
  beats {
    port => 5044
    host => "0.0.0.0"
  }
}

filter {
  if [fields][logtype] == "java" {
    grok {
      match => { "message" => "%{MONTHDAY:Monthday}-%{MONTH:Month}-%{YEAR:Year} %{HOUR:Hour}:%{MINUTE:Minute}:%{SECOND:Second}.%{INT:MicroSeconds} %{WORD:Severity} %{GREEDYDATA}" }
    }
	mutate {
		add_field => ["index_name", "catalina"]
            }
  }
  else if [fields][logtype] == "secure" {
    grok {
      match => { "message" => "%{MONTH:Month}\s*%{MONTHDAY:MonthDay}\s*%{HOUR:Hour}:%{MINUTE:Minute}:%{SECOND:Second}\s*%{HOSTNAME:Hostname}\s*%{WORD:Method}:\s*%{GREEDYDATA:Message}" }
    }
      mutate {
	add_field => ["index_name", "secure"]
      }

  }

}

output {
  elasticsearch {
    hosts => ["localhost:9200"]
    index => ["%{index_name}-%{+YYYY.MM.dd}"]
  }
}
```

