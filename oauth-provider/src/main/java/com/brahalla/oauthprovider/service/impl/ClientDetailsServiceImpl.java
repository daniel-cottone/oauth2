package com.brahalla.oauthprovider.service.impl;

import com.brahalla.oauthprovider.domain.entity.Client;
import com.brahalla.oauthprovider.repository.ClientRepository;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;

@Service
@Primary
public class ClientDetailsServiceImpl implements ClientDetailsService {

  private final Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private ClientRepository clientRepository;

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    Client client = this.clientRepository.findByClientId(clientId);

    if (client == null) {
      throw new NoSuchClientException(String.format("No client with requested id: %s", clientId));
    }

    BaseClientDetails baseClientDetails = new BaseClientDetails();
    baseClientDetails.setClientId(client.getClientId());
    baseClientDetails.setClientSecret(client.getClientSecret());
    baseClientDetails.setResourceIds(StringUtils.commaDelimitedListToSet(client.getResourceIds()));
    baseClientDetails.setScope(StringUtils.commaDelimitedListToSet(client.getScopes()));
    baseClientDetails.setAuthorizedGrantTypes(StringUtils.commaDelimitedListToSet(client.getGrantTypes()));
    baseClientDetails.setRegisteredRedirectUri(StringUtils.commaDelimitedListToSet(client.getRedirectUris()));
    return baseClientDetails;
  }

}
