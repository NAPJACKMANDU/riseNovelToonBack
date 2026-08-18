package com.domain.risenoveltoonback.model.joinLogin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyPageDataDto {
    private String userId;
    private String nickname;
    private String cpName;
    private int currentBalance;
}
