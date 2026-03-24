package org.example.webflux.service.llmclient;

import org.example.webflux.model.user.llmclient.LlmChatRequestDto;
import org.example.webflux.model.user.llmclient.LlmChatResponseDto;
import org.example.webflux.model.user.llmclient.LlmType;
import reactor.core.publisher.Mono;

public class GptWebClientService implements LlmWebClientService{
    @Override
    public Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto) {
        return null;
    }

    @Override
    public LlmType getLlmType() {
        return null;
    }
}
