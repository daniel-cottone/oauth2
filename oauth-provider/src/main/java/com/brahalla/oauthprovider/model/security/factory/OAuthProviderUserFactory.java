package com.brahalla.oauthprovider.model.security.factory;

import com.brahalla.oauthprovider.domain.entity.User;
import com.brahalla.oauthprovider.model.security.OAuthProviderUser;

import java.util.Collection;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
public class OAuthProviderUserFactory implements FactoryBean<OAuthProviderUser> {

  private Logger logger = Logger.getLogger(this.getClass());

  public OAuthProviderUser create(User user) {
    return new OAuthProviderUser(
      user.getId(),
      user.getUsername(),
      user.getPassword(),
      user.getEmail(),
      user.getLastChange(),
      AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
    );
  }

  @Override
  public OAuthProviderUser getObject() {
    return new OAuthProviderUser();
  }

  @Override
  public Class<OAuthProviderUser> getObjectType() {
    return OAuthProviderUser.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

}
