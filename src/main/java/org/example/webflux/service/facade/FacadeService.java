package org.example.webflux.service.facade;


import org.example.webflux.model.facade.FacadeHomeResponseDto;
import reactor.core.publisher.Mono;

public interface FacadeService {

    Mono<FacadeHomeResponseDto> getFacadeHomeResponseDto();


}
