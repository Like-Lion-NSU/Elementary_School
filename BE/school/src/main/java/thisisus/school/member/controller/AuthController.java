package thisisus.school.member.controller;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thisisus.school.member.security.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response, @RequestBody String accessToken) {
        return ResponseEntity.ok().body(authService.refreshToken(request, response, accessToken));
    }

    @GetMapping(value = "token")
    public String token(@RequestParam String token, @RequestParam String error) {
        if (StringUtils.isNotBlank(error)) {
            return error;
        }
        return token;
    }

}
