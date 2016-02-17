package com.brahalla.oauthprovider.service.impl;

import com.brahalla.oauthprovider.domain.entity.User;
import com.brahalla.oauthprovider.model.security.OAuthProviderUser;
import com.brahalla.oauthprovider.model.security.factory.OAuthProviderUserFactory;
import com.brahalla.oauthprovider.repository.UserRepository;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OAuthProviderUserFactory oAuthProviderUserFactory;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
      return this.oAuthProviderUserFactory.create(user);
    }
  }

}
