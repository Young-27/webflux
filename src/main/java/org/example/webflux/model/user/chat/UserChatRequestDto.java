package org.example.webflux.model.user.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.user.llmclient.LlmModel;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserChatRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -2800005539847408045L;

    private String request;
    private LlmModel llmModel;
}
