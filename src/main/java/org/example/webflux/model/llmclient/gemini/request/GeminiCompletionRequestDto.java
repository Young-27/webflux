package org.example.webflux.model.llmclient.gemini.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.llmclient.gemini.GeminiMessageRole;
import org.example.webflux.model.llmclient.gpt.GptMessageRole;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeminiCompletionRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1779358948531415223L;

    private GeminiMessageRole role;
    private String content;

    public GeminiCompletionRequestDto(String content) {
        this.content = content;
    }
}

