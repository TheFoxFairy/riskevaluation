package com.gov.risk.auth.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public interface ClientService extends ClientDetailsService {
    @Override
    ClientDetails loadClientByClientId(String s) throws ClientRegistrationException;
}
