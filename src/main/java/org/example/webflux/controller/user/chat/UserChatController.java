package org.example.webflux.controller.user.chat;

import lombok.RequiredArgsConstructor;
import org.example.webflux.model.user.chat.UserChatRequestDto;
import org.example.webflux.model.user.chat.UserChatResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class UserChatController {

    @PostMapping("/oneshot")
    public Mono<UserChatResponseDto> oneShatChat(@RequestBody UserChatRequestDto userChatRequestDto) {
        // 서비스에서 request 가공해서 response 돌려줘야함.
        return Mono.empty();
    }

}
