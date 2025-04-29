package com.app.oauth.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OauthMemberVO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberPicture;
    private String memberName;
    private String memberNickName;
    private String memberProvider;
}
