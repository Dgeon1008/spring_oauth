package com.app.oauth.service;

import com.app.oauth.domain.OauthMemberVO;
import com.app.oauth.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    @Override
    public Optional<OauthMemberVO> getMemberById(Long id) {
        return memberDAO.findById(id);
    }

    @Override
    public List<OauthMemberVO> getAllMembers() {
        return memberDAO.findAll();
    }

    @Override
    public Long getMemberIdByMemberEmail(String memberEmail) {
        return memberDAO.findByEmail(memberEmail);
    }

    @Override
    public void register(OauthMemberVO oauthMemberVO) {
        memberDAO.save(oauthMemberVO);
    }

    @Override
    public void modify(OauthMemberVO oauthMemberVO) {
        memberDAO.update(oauthMemberVO);
    }

    @Override
    public void delete(Long id) {
        memberDAO.delete(id);
    }
}
