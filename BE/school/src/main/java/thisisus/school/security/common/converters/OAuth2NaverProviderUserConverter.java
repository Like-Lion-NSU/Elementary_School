package thisisus.school.security.common.converters;

import thisisus.school.security.common.enums.OAuth2Config;
import thisisus.school.security.common.util.OAuth2Utils;
import thisisus.school.security.model.users.ProviderUser;
import thisisus.school.security.model.users.social.NaverUser;

public final class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest,ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.NAVER.getSocialName())) {
            return null;
        }

        return new NaverUser(OAuth2Utils.getSubAttributes(
                providerUserRequest.oAuth2User(), "response"),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}
