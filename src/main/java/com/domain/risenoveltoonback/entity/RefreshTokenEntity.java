package com.domain.risenoveltoonback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

@Builder
@Entity
@Getter 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refresh_token_entity")
public class RefreshTokenEntity {
   
    @Id    
    @Column(name = "user_id") // DB 컬럼명을 user_id로 지정    
    private String userId; // 사용자의 ID     
    
    @Column(name = "refresh_token")    
    private String refreshToken;     // 토큰 갱신 시 내용 변경을 위한 메서드   
    
    public void updateRefreshToken(String token) {        
        this.refreshToken = token;    
    }
}
