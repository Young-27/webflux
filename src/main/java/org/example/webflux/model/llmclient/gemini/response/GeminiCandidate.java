package org.example.webflux.model.llmclient.gemini.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeminiCandidate implements Serializable {
    @Serial
    private static final long serialVersionUID = -6927822299874549417L;

    private GeminiContent content;
}
