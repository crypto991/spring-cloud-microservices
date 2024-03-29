# Currency Exchange microservices with Spring Boot and Spring Cloud



* Establishing Communication between Microservices
* Centralized Microservice Configuration with Spring Cloud Config Server
* Using Spring Cloud Bus to exchange messages about Configuration updates
* Simplify communication with other Microservices using Feign REST Client
* Implement client side load balancing with Ribbon
* Implement dynamic scaling using Eureka Naming Server and Ribbon
* Implement API Gateway with Zuul
* Implement Distributed tracing with Spring Cloud Sleuth and Zipkin
* Implement Fault Tolerance with Zipkin

| Application | Port |
| --- | --- |
| Limit Service | 8080,8081, ... |
| Spring Cloud Config Server | 8888 |
| Currency Exchange Service | 8000,8001,8002, .. |
| Currency Conversion Service | 8100,8101,8102,.. |
| Netflix Eureka Naming Server | 8761 |
| Netflix Zuul API Gateway Server	| 8765 |
| Zipkin Distributed Tracing Server | 9411 |

# URLs


| Application | URL |
| --- | --- |
| Limit Service | http://localhost:8080/limits POST -> http://localhost:8080/actuator/refresh |
| Spring Cloud Config Server | http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev |
| Currency Converter Service - Direct Call | http://localhost:8100/currency-converter/from/USD/to/RSD/quantity/10 |
| Currency Converter Service - Feign | 	http://localhost:8100/currency-converter-feign/from/EUR/to/RSD/quantity/10000 |
| Currency Exchange Service | http://localhost:8000/currency-exchange/from/EUR/to/RSD http://localhost:8001/currency-exchange/from/USD/to/RSD |
| Eureka | http://localhost:8761/ |
| Zuul - Currency Exchange & Exchange Services | http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/RSD http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/RSD/quantity/10 | 
| Zipkin | http://localhost:9411/zipkin/ |
| Spring Cloud Bus Refresh	| http://localhost:8080/bus/refresh |

# Zipkin Installation

Quick Start Page
* https://zipkin.io/pages/quickstart

Downloading Zipkin Jar
* https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec

Command to run
```bash
RABBIT_URI=amqp://localhost java -jar zipkin-server-2.5.2-exec.jar
```
# VM Arguments
```bash
-Dserver.port=8001
```

# Commands
```bash
mkdir git-configuration-repo
cd git-configuration-repo/
git init
git add -A
git commit -m "first commit"
```

# Spring Cloud Configuration
```bash
spring.cloud.config.failFast=true
```
