package org.example.webflux.model.llmclient.gpt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.llmclient.LlmChatRequestDto;
import org.example.webflux.model.llmclient.LlmModel;
import org.example.webflux.model.llmclient.gpt.GptMessageRole;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GptChatRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6674873513869421361L;

    private List<GptCompletionRequestDto> message;
    private LlmModel model;
    private Boolean stream;
    private GptResponseFormat response_format;

    public GptChatRequestDto(LlmChatRequestDto llmChatRequestDto) {
//        if (Optional.ofNullable(llmChatRequestDto.getUseJson()).filter(useJson -> useJson).isPresent()) {
        if (llmChatRequestDto.isUseJson()) {
            response_format = new GptResponseFormat("json_object");
        }
        this.message = List.of(new GptCompletionRequestDto(GptMessageRole.SYSTEM, llmChatRequestDto.getSystemPrompt()),                new GptCompletionRequestDto(GptMessageRole.USER, llmChatRequestDto.getUserRequest()));
        this.model = llmChatRequestDto.getLlmModel();
    }
}
