package org.example.webflux.model.llmclient.gpt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.llmclient.gpt.GptMessageRole;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GptCompletionRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4779358948531415223L;

    private List<GptCompletionRequestDto> message;
    private GptMessageRole role;
    private String content;

    public GptCompletionRequestDto(GptMessageRole gptMessageRole, String systemPrompt) {
    }
}

