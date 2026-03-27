package org.example.webflux.controller.facade;

import lombok.RequiredArgsConstructor;
import org.example.webflux.model.facade.FacadeHomeResponseDto;
import org.example.webflux.service.facade.FacadeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/facade")
@RequiredArgsConstructor
public class FacadeController {

    private final FacadeService facadeService;

    @PostMapping("/home")
    public Mono<FacadeHomeResponseDto> homeFacade() {
        return facadeService.getFacadeHomeResponseDto();
    }

}
