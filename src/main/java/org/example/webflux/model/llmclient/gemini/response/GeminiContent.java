package org.example.webflux.model.llmclient.gemini.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.llmclient.gemini.GeminiMessageRole;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeminiContent implements Serializable {
    @Serial
    private static final long serialVersionUID = 3564141764241373513L;

    private List<GeminiPart> parts;
    private GeminiMessageRole role;

    public GeminiContent(List<GeminiPart> parts) {
        this.parts = parts;
    }
}
