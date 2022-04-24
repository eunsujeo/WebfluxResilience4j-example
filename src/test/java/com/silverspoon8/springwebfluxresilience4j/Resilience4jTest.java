package com.silverspoon8.springwebfluxresilience4j;

import java.time.Duration;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@Execution(ExecutionMode.CONCURRENT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class Resilience4jTest {

    private final WebTestClient webClient = WebTestClient.bindToServer()
        .baseUrl("http://localhost:8083")
        .responseTimeout(Duration.ofMillis(30000))
        .build();

    // 먼저 10번 실행 중 3개 에러 발생 시켜서 에러 퍼센트를 만들어주면 다음 요청에 switch가 열리는지 확인해보자.
    // 우선 10개 요청을 실패 7개 성공 3개를 나눠서 실행시켜보자.

    @RepeatedTest(3)
    public void success() {
        this.webClient.get().uri("/circuit?name=silverspoon8&price=100")
            .exchange()
            .expectStatus().isOk();
    }

    @RepeatedTest(7)
    void testFail() {
        this.webClient.get().uri("/circuit?name=silverspoon81&price=100")
            .exchange()
            .expectStatus().is5xxServerError();
    }
}
