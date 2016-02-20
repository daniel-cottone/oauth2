package com.brahalla.oauthprovider.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  @Value("${oauthprovider.token.expiration}")
  private Integer tokenExpiration;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private ClientDetailsService clientDetailsService;

  @Autowired
  private JwtTokenStore jwtTokenStore;

  @Autowired
  private TokenEnhancer tokenEnhancer;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
      .tokenServices(defaultTokenServices())
      //.userApprovalHandler(userApprovalHandler())
      .authenticationManager(this.authenticationManager);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
      .withClientDetails(this.clientDetailsService);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer
      .checkTokenAccess("isAuthenticated()")
      .tokenKeyAccess("permitAll()");

    oauthServer.allowFormAuthenticationForClients();
  }

  /*@Bean
  public ApprovalStore approvalStore() {
    final TokenApprovalStore tokenApprovalStore = new TokenApprovalStore();
    tokenApprovalStore.setTokenStore(this.jwtTokenStore);
    return tokenApprovalStore;
  }

  @Bean
  public UserApprovalHandler userApprovalHandler() {
    final TokenStoreUserApprovalHandler tokenStoreUserApprovalHandler = new TokenStoreUserApprovalHandler();
    final DefaultOAuth2RequestFactory defaultOAuth2RequestFactory = new DefaultOAuth2RequestFactory(this.clientDetailsService);
    tokenStoreUserApprovalHandler.setApprovalStore(approvalStore());
    tokenStoreUserApprovalHandler.setRequestFactory(defaultOAuth2RequestFactory);
    tokenStoreUserApprovalHandler.setClientDetailsService(this.clientDetailsService);
    tokenStoreUserApprovalHandler.setUseApprovalStore(true);
  }*/

  @Bean
  public AuthorizationServerTokenServices defaultTokenServices() {
    final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(this.jwtTokenStore);
    defaultTokenServices.setClientDetailsService(this.clientDetailsService);
    defaultTokenServices.setTokenEnhancer(this.tokenEnhancer);
    defaultTokenServices.setSupportRefreshToken(true);
    defaultTokenServices.setAccessTokenValiditySeconds(this.tokenExpiration);
    return defaultTokenServices;
  }

}
