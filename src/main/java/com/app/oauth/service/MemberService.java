package com.app.oauth.service;

import com.app.oauth.domain.OauthMemberVO;

import java.util.List;
import java.util.Optional;

public interface MemberService {

//    회원 조회
    public Optional<OauthMemberVO> getMemberById(Long id);

//    회원 전체 조회
    public List<OauthMemberVO> getAllMembers();

//    이메일로 아이디 찾기
    public Long getMemberIdByMemberEmail(String memberEmail);

//    회원 가입
    public void register(OauthMemberVO oauthMemberVO);

//    회원 수정
    public void modify(OauthMemberVO oauthMemberVO);

//    회원 탈퇴
    public void delete(Long id);


}
