package org.example.webflux.model.user.llmclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.user.chat.UserChatRequestDto;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LlmChatRequestDto implements Serializable {


    @Serial
    private static final long serialVersionUID = -2189328081146658696L;

    private String userRequest;
    /**
     * systemPrompt가 userRequest에 포함되는 내용보다 더 높은 강제성과 우선순위를 가진다.
     */
    private String systemPrompt;
    private Boolean userJson;
    private LlmModel llmModel;

    public LlmChatRequestDto(UserChatRequestDto userChatRequestDto, String systemPrompt) {
        this.llmModel = userChatRequestDto.getLlmModel();
        this.systemPrompt = systemPrompt;
        this.userRequest = userChatRequestDto.getRequest();
    }
}
