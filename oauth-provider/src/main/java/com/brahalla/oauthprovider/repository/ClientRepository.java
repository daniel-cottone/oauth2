package com.brahalla.oauthprovider.repository;

import com.brahalla.oauthprovider.domain.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

  public Client findByClientId(String clientId);

}
