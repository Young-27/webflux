package org.example.webflux.model.user.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.exception.CommonError;
import org.example.webflux.model.llmclient.LlmChatResponseDto;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserChatResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6174642156193522786L;

    private String response;
    private CommonError error;

    public UserChatResponseDto(LlmChatResponseDto llmChatResponseDto) {
        this.response = llmChatResponseDto.getLlmResponse();
        this.error = llmChatResponseDto.getError();
    }
}
