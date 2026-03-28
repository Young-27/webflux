package org.example.webflux.service.user.chat;

import org.example.webflux.model.user.chat.UserChatRequestDto;
import org.example.webflux.model.user.chat.UserChatResponseDto;
import reactor.core.publisher.Flux;

public interface ChainOfThoughService {
    Flux<UserChatResponseDto> getChainOfThoughtResponse(UserChatRequestDto userChatRequestDto);

}
