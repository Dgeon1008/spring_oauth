package com.app.oauth.mapper;

import com.app.oauth.domain.OauthMemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

//    회원 조회
    public Optional<OauthMemberVO> select(Long id);

//    회원 전체 조회
    public List<OauthMemberVO> selectAll();

//    이메일로 아이디 찾기
    public Long selectByEmail(String memberEmail);

//    회원가입
    public void insert(OauthMemberVO oauthMemberVO);

//    회원 수정
    public void update(OauthMemberVO oauthMemberVO);

//    회원 탈퇴
    public void delete(Long id);

}
