package com.siwoo.application.repository;

import com.siwoo.application.domain.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ClientRepository {

    @Transactional(readOnly = false)
    void save(Client client);

    Client findById(Long id);

    List<Client> findByNickName(String nickname);

    List<Client> findAll();
}

