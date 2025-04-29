package com.app.oauth.mapper;

import com.app.oauth.domain.OauthMemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class OauthMemberTests {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void selectTest(){
        log.info("{}", memberMapper.select(1L));
    }

//    회원 전체 조회 테스트
    @Test
    public void selectAllTest(){
        log.info("{}", memberMapper.selectAll());
    }

//
    @Test
    public void selectByIdTest(){
        log.info("{}", memberMapper.selectByEmail("test@test.com"));
    }

//    회원가입 테스트
    @Test
    public void insertTest(){
        OauthMemberVO oauthMemberVO = new OauthMemberVO();
        oauthMemberVO.setMemberEmail("test@test.com");
        oauthMemberVO.setMemberPassword("123456");
        oauthMemberVO.setMemberName("홍길동");
        memberMapper.insert(oauthMemberVO);
    }

//    회원 수정 테스트
    @Test
    public void updateTest(){
//        세션
        Long memberId = memberMapper.selectByEmail("test@test.com");
        memberMapper.select(memberId).ifPresent(member -> {
            OauthMemberVO oauthMemberVO = new OauthMemberVO();
            oauthMemberVO.setId(member.getId());
            oauthMemberVO.setMemberEmail(member.getMemberEmail());
            oauthMemberVO.setMemberPassword("1111");
            oauthMemberVO.setMemberName(member.getMemberName());
            oauthMemberVO.setMemberNickName("개복치 2단계");
            oauthMemberVO.setMemberPicture(member.getMemberPicture());
            oauthMemberVO.setMemberProvider(member.getMemberProvider());
            memberMapper.update(oauthMemberVO);
        });

    }

    //        회원 탈퇴
    @Test
    public void deleteTest(){
        Long memberId = memberMapper.selectByEmail("test@test.com");
        memberMapper.delete(memberId);
    }


}
