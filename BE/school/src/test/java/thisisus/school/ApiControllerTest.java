package thisisus.school;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import thisisus.school.member.controller.ApiController;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.jwt.JwtTokenProvider;
import thisisus.school.member.security.service.CustomUserDetails;

import java.util.Collections;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static thisisus.school.member.domain.Role.STUDENT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"server.port=8081"})
@AutoConfigureMockMvc(addFilters = false)
@Import({JwtTokenProvider.class}) // Import JwtTokenProvider for token generation
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private String jwtToken;

    @Before
    public void setup() {
//        Member member = new Member();
//        member.setId(1L);
//        member.setEmail("student@example.com");
//        member.setRole(STUDENT);
//
//        memberRepository.save(member);        // Create a CustomUserDetails object or fetch it from your user repository
        CustomUserDetails userDetails = new CustomUserDetails(
                1L,
                "g.g.horo@gmail.com", // username
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT")) // authorities (roles)
        );

        // Create an Authentication object using the CustomUserDetails
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, // principal (CustomUserDetails)
                "password", // credentials (password)
                userDetails.getAuthorities() // authorities (roles)
        );

        // Generate a JWT token using the Authentication object
        Map<String, String> tokenMap = jwtTokenProvider.generateToken(authentication);
        jwtToken = tokenMap.get("accessToken");
    }

    @Test
    public void testGetCurrentUserWithValidToken() throws Exception {
        // Create a sample member
        Member member = new Member();
        member.setEmail("g.g.horo@gmail.com");
//        when(memberRepository.findByEmail("g.g.horo@gmail.com")).thenReturn(java.util.Optional.of(member));

        // Perform the request with the JWT token
        mockMvc.perform(get("/me")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("g.g.horo@gmail.com"));
    }

    /*@RestController
    public static class TestControllerTest {

        private final MemberRepository memberRepository;

        @Autowired
        public TestControllerTest(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        @GetMapping("/me")
        public Member getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {
            return memberRepository.findByEmail(user.getEmail()).orElseThrow(() -> new IllegalStateException("not found user"));
        }
    }*/
}