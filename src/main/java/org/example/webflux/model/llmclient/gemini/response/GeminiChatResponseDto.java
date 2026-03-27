package org.example.webflux.model.llmclient.gemini.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.exception.CustomErrorType;
import org.example.webflux.exception.ErrorTypeException;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeminiChatResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4172986482252536748L;

    private List<GeminiCandidate> candidates;

    public String getSingleText() {
        return candidates.stream().findFirst()
                .flatMap(candidate -> candidate.getContent().getParts().stream().findFirst()
                        .map(GeminiPart::getText))
                .orElseThrow(() -> new ErrorTypeException("[GeminiResponse] There is no choices.", CustomErrorType.GEMINI_RESPONSE_ERROR));
    }

}
