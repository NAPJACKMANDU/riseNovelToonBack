package com.domain.risenoveltoonback.model.joinlogin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoDto {
    private String nickname;
    private String cpName;
    private int currentBalance;
    private String accessToken;
    private String refreshToken;
}
