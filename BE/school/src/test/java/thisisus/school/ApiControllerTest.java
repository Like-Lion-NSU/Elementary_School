package thisisus.school;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.*;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.jwt.JwtTokenProvider;
import thisisus.school.member.security.service.AuthService;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.domain.Role;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"server.port=8081"})
@AutoConfigureMockMvc(addFilters = false)
@Import({JwtTokenProvider.class}) // Import JwtTokenProvider for token generation
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private String jwtToken;
    private String refreshToken;

    @Before
    public void setup() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("g.g.horo@gmail.com");
        member.setRole(Role.STUDENT);


        memberRepository.save(member);        // Create a CustomUserDetails object or fetch it from your user repository

        CustomUserDetails userDetails = new CustomUserDetails(
                1L,
                "g.g.horo@gmail.com", // username
                Collections.singletonList(new SimpleGrantedAuthority("STUDENT")) // authorities (roles)
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
        refreshToken = tokenMap.get("refreshToken");

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void testGetCurrentUserWithValidToken() throws Exception {
        // Create a sample member
        Member member = new Member();
        member.setEmail("g.g.horo@gmail.com");
        when(memberRepository.findByEmail("g.g.horo@gmail.com")).thenReturn(java.util.Optional.of(member));

        // Perform the request with the JWT token
        mockMvc.perform(get("/me")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("g.g.horo@gmail.com"));
    }

    @Test
    public void testRefreshToken() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String newAccessToken = null; // 변수 선언

        // Create a JSON object with refresh token and access token
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("refreshToken", refreshToken);
        requestMap.put("accessToken", jwtToken);
        String requestJson = objectMapper.writeValueAsString(requestMap);

        // Perform the request with the JSON object as content
        MvcResult result = mockMvc.perform(post("/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(status().isOk())
//                .andExpect(content().json("{\"accessToken\": \"your_new_access_token_here\"}"))
                .andReturn();

        // Parse the response JSON to get the new access token
        String responseJson = result.getResponse().getContentAsString();
        JsonNode rootNode = objectMapper.readTree(responseJson);
        newAccessToken = rootNode.get("accessToken").asText(); // 변수에 값 저장

        // Now you have the new access token
        System.out.println("New Access Token: " + newAccessToken);

        // Use newAccessToken to perform further tests or validations
    }

    @PostMapping("/refresh") // /refresh에 대한 POST 매핑 추가
    public ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response, @RequestBody String accessToken) {
        return ResponseEntity.ok().body(authService.refreshToken(request, response, accessToken));
    }
}