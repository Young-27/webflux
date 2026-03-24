package org.example.webflux.model.user.llmclient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LlmModel {
    GPT_40("gpt-4o", LlmType.GPT),
    GEMINI_2_0_FLASH("gemini-2.0-flash", LlmType.GEMINI)
    ;

    private final String code;
    private final LlmType llmType;
}
