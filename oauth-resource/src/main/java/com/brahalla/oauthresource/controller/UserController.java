package com.brahalla.oauthresource.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

  @RequestMapping(method = RequestMethod.GET)
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<?> getPrincipal(Principal principal) {
    return ResponseEntity.ok(principal);
  }

}
