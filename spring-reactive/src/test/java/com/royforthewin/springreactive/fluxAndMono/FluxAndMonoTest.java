package com.royforthewin.springreactive.fluxAndMono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("spring", "Spring boot", "Reactive Spring")
                //   .concatWith(Flux.error(new RuntimeException("Exception")))
                .concatWith(Flux.just("after error"))
                .log();

        stringFlux.subscribe(System.out::println
                , (e) -> System.err.println(e),
                () -> System.out.println("Completed"));

    }

    @Test
    public void fluxTestElements_WithoutError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .verifyComplete();
    }

    @Test
    public void fluxTestElements_WithError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                //  .expectError(RuntimeException.class)
                .expectErrorMessage("Exception Occurred")
                .verify();
    }


    @Test
    public void fluxTestElementsCount() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring");
        //.log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .verifyComplete();
    }


    @Test
    public void monoTest() {
        Mono<String> stringMono = Mono.just("Spring");
        StepVerifier.create(stringMono.log())
                .expectNext("Spring")
                .verifyComplete();

    }

}
