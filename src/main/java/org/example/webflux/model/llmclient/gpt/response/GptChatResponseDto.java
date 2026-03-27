package org.example.webflux.model.llmclient.gpt.response;

import lombok.*;
import org.example.webflux.exception.CustomErrorType;
import org.example.webflux.exception.ErrorTypeException;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GptChatResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -4491952688806595202L;

    private List<GptChoice> choices;

    public GptChoice getSingleChoice() {
        return choices.stream().findFirst().orElseThrow(() -> new ErrorTypeException("[GptResponse] There is no choices.", CustomErrorType.GPT_RESPONSE_ERROR));
    }
}
