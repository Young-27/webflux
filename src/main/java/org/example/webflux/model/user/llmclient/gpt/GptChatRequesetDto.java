package org.example.webflux.model.user.llmclient.gpt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.llmclient.LlmModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GptChatRequesetDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 7476938608319376729L;

    private List message;
    private LlmModel model;
    private Boolean stream;
}
