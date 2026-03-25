package org.example.webflux.model.llmclient.gpt.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.llmclient.gpt.GptMessageRole;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GptChoice implements Serializable {
    @Serial
    private static final long serialVersionUID = 4675769808942366852L;

    private String finishReason;
    private GptResponseMessageDto message;
}
