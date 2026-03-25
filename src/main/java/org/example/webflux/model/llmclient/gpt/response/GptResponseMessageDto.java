package org.example.webflux.model.llmclient.gpt.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GptResponseMessageDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -380752960545706014L;

    private String finishReason;
    private GptResponseMessageDto message;
    private String content;
}
