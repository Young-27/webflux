package org.example.webflux.model.llmclient.gemini.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeminiPart implements Serializable {
    @Serial
    private static final long serialVersionUID = 6472710749098718099L;

    private String text;
}
