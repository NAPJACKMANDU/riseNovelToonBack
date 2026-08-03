package com.domain.risenoveltoonback.entity;

import jakarta.persistence.*;
import lombok.*;
import com.domain.risenoveltoonback.entity.BaseTimeEntity;
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String id;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Builder.Default
    @Column(name = "current_balance")
    private Integer currentBalance = 0;

    // 포인트 잔액 업데이트 메서드
    public void updateBalance(int amount) {
        this.currentBalance += amount;
    }
}