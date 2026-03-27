package org.example.webflux.model.llmclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.llmclient.gemini.response.GeminiChatResponseDto;
import org.example.webflux.model.llmclient.gpt.response.GptChatResponseDto;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LlmChatResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -6930497766224677701L;

    private String llmResponse;

    public LlmChatResponseDto(GptChatResponseDto gptChatResponseDto) {
        this.llmResponse = gptChatResponseDto.getSingleChoice().getMessage().getContent();
    }

    public static LlmChatResponseDto getLlmChatResponseDtoFromStream(GptChatResponseDto gptChatResponseDto) {
        return new LlmChatResponseDto(gptChatResponseDto.getSingleChoice().getDelta().getContent());
    }

    public LlmChatResponseDto(GeminiChatResponseDto geminiChatResponseDto) {
        this.llmResponse = geminiChatResponseDto.getSingleText();
    }

}
