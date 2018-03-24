package com.siwoo.application.service;

import com.siwoo.application.domain.Client;

import java.util.List;

public interface ClientService {
    Client join(Client client);
    List<Client> findClients();
    Client find(long id);
}
