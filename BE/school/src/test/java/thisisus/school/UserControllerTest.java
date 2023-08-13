//package thisisus.school;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import thisisus.school.member.domain.Member;
//import thisisus.school.member.domain.Role;
//import thisisus.school.member.repository.MemberRepository;
//import thisisus.school.member.security.jwt.JwtTokenProvider;
//import thisisus.school.member.security.service.CustomUserDetails;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static thisisus.school.member.domain.Role.STUDENT;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ComponentScan(basePackages = "thisisus.school.member.security.jwt")
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//
//    private MemberRepository memberRepository = mock(MemberRepository.class);
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider; // Inject JwtTokenProvider
//
//    @Test
//    public void testGetCurrentUser() throws Exception {
//        // Given
//        CustomUserDetails userDetails = new CustomUserDetails(
//                1L,                               // User ID
//                "user@example.com",               // User Email
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"))  // User Authorities
//        );
//
//        Member member = Member.builder()
//                .id(1L)                              // User ID
//                .name("User Name")                      // User Name
//                .email("user@exapmple.com")               // User Email
//                .role(Role.STUDENT)                // User Role
//                .point(0L)                             // User Point
//                .lastLogin(LocalDateTime.now())              // User Last Login
//                .provider("provider")
//                .build();                       // User Provider
//
//        memberRepository.save(member);
//
//        when(memberRepository.findByEmail(userDetails.getEmail())).thenReturn(Optional.ofNullable(member));
//
//        // Generate JWT token
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        Map<String, String> tokens = jwtTokenProvider.generateToken(authentication); // Use Authentication
//
//        String accessToken = tokens.get("accessToken");
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/me")
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userDetails.getEmail()));
//    }
//}