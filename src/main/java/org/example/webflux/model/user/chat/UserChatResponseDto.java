package org.example.webflux.model.user.chat;

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
public class UserChatResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6174642156193522786L;

    private String response;
}
