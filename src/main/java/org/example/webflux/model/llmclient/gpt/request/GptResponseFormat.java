package org.example.webflux.model.llmclient.gpt.request;

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
public class GptResponseFormat implements Serializable {

    @Serial
    private static final long serialVersionUID = -3567302061404127646L;

    private String type;
}
