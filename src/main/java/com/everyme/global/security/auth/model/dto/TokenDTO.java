package com.everyme.global.security.auth.model.dto;

import com.everyme.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "TBL_TOKEN")
public class TokenDTO {

    @Id
    @Column(name = "TOKEN_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;

    @Column(name = "USER_NO", nullable = false)
    private Integer userNo;

    @Column(name = "TOKEN_VALUE", nullable = false)
    private  String tokenValue;

    @Column(name = "EXPIRED_DATE")
    private int expiredDate;

    public TokenDTO() {
    }

    public TokenDTO(int tokenId, Integer userNo, String tokenValue, int expiredDate) {
        this.tokenId = tokenId;
        this.userNo = userNo;
        this.tokenValue = tokenValue;
        this.expiredDate = expiredDate;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public int getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(int expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return "TokenDTO{" +
                "tokenId=" + tokenId +
                ", userNo=" + userNo +
                ", tokenValue='" + tokenValue + '\'' +
                ", expiredDate=" + expiredDate +
                '}';
    }
}
