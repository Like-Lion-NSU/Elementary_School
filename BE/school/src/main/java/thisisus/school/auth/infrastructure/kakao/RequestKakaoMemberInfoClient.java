package thisisus.school.auth.infrastructure.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "memberForKakao", url = "https://kapi.kakao.com")
public interface RequestKakaoMemberInfoClient {

    @PostMapping("/v2/user/me")
    KakaoMemberInfoResponse getId(
        @RequestHeader("Authorization") String authorization);
}