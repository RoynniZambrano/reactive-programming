package com.royforthewin.springreactive.fluxAndMono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

    @Test
    public void fluxTest(){
       Flux<String> stringFlux = Flux.just("spring", "Spring boot", "Reactive Spring")
            //   .concatWith(Flux.error(new RuntimeException("Exception")))
               .concatWith(Flux.just("after error"))
               .log();

       stringFlux.subscribe(System.out::println
               ,(e) -> System.err.println(e),
               () -> System.out.println("Completed"));

    }
}
