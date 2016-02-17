package com.brahalla.oauthprovider.domain.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "clients")
public class Client {

  private Long id;
  private String clientId;
  private String clientSecret;
  private String resourceIds;
  private String scopes;
  private String grantTypes;
  private String authorities;
  private String redirectUris;

  public Client() {
    super();
  }

  public Client(String clientId, String clientSecret, String resourceIds, String scopes, String grantTypes, String authorities, String redirectUris) {
    this.setClientId(clientId);
    this.setClientSecret(clientSecret);
    this.setResourceIds(resourceIds);
    this.setScopes(scopes);
    this.setGrantTypes(grantTypes);
    this.setAuthorities(authorities);
    this.setRedirectUris(redirectUris);
  }

  @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_seq")
	@SequenceGenerator(name = "clients_seq", sequenceName = "clients_seq", allocationSize = 1)
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "client_id")
  public String getClientId() {
    return this.clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  @Column(name = "client_secret")
  public String getClientSecret() {
    return this.clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  @Column(name = "resource_ids")
  public String getResourceIds() {
    return this.resourceIds;
  }

  public void setResourceIds(String resourceIds) {
    this.resourceIds = resourceIds;
  }

  @Column(name = "scopes")
  public String getScopes() {
    return this.scopes;
  }

  public void setScopes(String scopes) {
    this.scopes = scopes;
  }

  @Column(name = "grant_types")
  public String getGrantTypes() {
    return this.grantTypes;
  }

  public void setGrantTypes(String grantTypes) {
    this.grantTypes = grantTypes;
  }

  @Transient
  public String getAuthorities() {
    return this.authorities;
  }

  public void setAuthorities(String authorities) {
    this.authorities = authorities;
  }

  @Column(name = "redirect_uris")
  public String getRedirectUris() {
    return this.redirectUris;
  }

  public void setRedirectUris(String redirectUris) {
    this.redirectUris = redirectUris;
  }

}
