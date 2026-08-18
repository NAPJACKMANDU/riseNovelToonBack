package com.domain.risenoveltoonback.model.joinLogin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JoinFormDto {
    private String userId;
    private String password;
    private String nickname;
    private String cpName;
}
