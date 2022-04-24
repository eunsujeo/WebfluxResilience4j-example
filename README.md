# Spring webflux + Resilience4j
Spring Webflux에 Resilience4j를 이용해 서킷브레이커 예제

## required dependency
```groovy
implementation 'org.springframework.boot:spring-boot-starter-webflux'
implementation 'io.github.resilience4j:resilience4j-spring-boot2:1.7.1'
implementation 'io.github.resilience4j:resilience4j-reactor:1.7.1'
```

## reference
https://www.baeldung.com/resilience4j
https://github.com/resilience4j/resilience4j