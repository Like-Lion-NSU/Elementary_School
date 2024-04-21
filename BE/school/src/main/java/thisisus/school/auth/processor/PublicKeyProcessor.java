package thisisus.school.auth.processor;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import thisisus.school.auth.exception.PublicKeyGenerationFailedException;
import thisisus.school.auth.infrastructure.kakao.RequestKakaoOauthClient;
import thisisus.school.auth.infrastructure.oidc.PublicKeyDto;
import thisisus.school.auth.infrastructure.oidc.PublicKeysDto;

@Component
@RequiredArgsConstructor
public class PublicKeyProcessor {

  private final RequestKakaoOauthClient requestKakaoOauthClient;

  public PublicKeysDto getKakaoPublicKeys() {
    return requestKakaoOauthClient.getPublicKeys();
  }

  public RSAPublicKey getRSAPublicKey(PublicKeyDto key) {
    BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(key.getN()));
    BigInteger exponent = new BigInteger(1,Base64.getUrlDecoder().decode(key.getE()));
    try {
      KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());
      RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
      return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e){
      throw new PublicKeyGenerationFailedException();
    }
  }
}
