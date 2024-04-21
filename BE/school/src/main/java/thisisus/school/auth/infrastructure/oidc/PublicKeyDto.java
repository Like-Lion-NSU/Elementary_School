package thisisus.school.auth.infrastructure.oidc;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PublicKeyDto {

    private String kid;
    private String kty;
    private String alg;
    private String use;
    private String n;
    private String e;
}