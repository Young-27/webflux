package org.example.webflux.service.user.chat;

import lombok.RequiredArgsConstructor;
import org.example.webflux.model.llmclient.LlmChatResponseDto;
import org.example.webflux.model.user.chat.UserChatRequestDto;
import org.example.webflux.model.user.chat.UserChatResponseDto;
import org.example.webflux.model.llmclient.LlmChatRequestDto;
import org.example.webflux.service.llmclient.LlmWebClientService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserChatServiceImpl implements UserChatService{

    private final LlmWebClientService llmWebClientService;

    @Override
    public Mono<UserChatResponseDto> getOneShotChat(UserChatRequestDto userChatRequestDto) {
        LlmChatRequestDto llmChatRequestDto = new LlmChatRequestDto(userChatRequestDto, "요청에 적절히 응답해주세요.");
        Mono<LlmChatResponseDto> chatCompletioMono = llmWebClientService.getChatCompletion(llmChatRequestDto);
        return chatCompletioMono.map(UserChatResponseDto::new);
    }

}
