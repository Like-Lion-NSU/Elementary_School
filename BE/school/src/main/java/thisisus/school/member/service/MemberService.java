package thisisus.school.member.service;

import thisisus.school.member.dto.MemberInfoResponse;
import thisisus.school.member.dto.UpdateMemberRequest;

public interface MemberService {

    void update(Long memberId, UpdateMemberRequest updateMemberRequest);

    void delete(Long memberId);

    MemberInfoResponse findMember(Long memberId);
}