package com.brahalla.oauthprovider.configuration;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtTokenConfiguration {

  @Value("${oauthprovider.token.key.private}")
  private String privateKey;

  @Value("${oauthprovider.token.key.public}")
  private String publicKey;

  @Bean
  public JwtTokenStore tokenStore() {
    return new JwtTokenStore(tokenEnhancer());
  }

  @Bean
  public JwtAccessTokenConverter tokenEnhancer() {
    final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    jwtAccessTokenConverter.setSigningKey(this.privateKey);
    jwtAccessTokenConverter.setVerifierKey(this.publicKey);
    return jwtAccessTokenConverter;
  }

  @Bean
  public TokenEnhancerChain tokenEnhancerChain() {
    final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    final List<TokenEnhancer> tokenEnhancerList = Arrays.asList(new CustomTokenEnhancer(), tokenEnhancer());
    tokenEnhancerChain.setTokenEnhancers(tokenEnhancerList);
    return tokenEnhancerChain;
  }

  private static class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
      final DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
      final User user = (User) authentication.getPrincipal();
      result.getAdditionalInformation().put("authorities", user.getAuthorities());
      return result;
    }
  }

}
