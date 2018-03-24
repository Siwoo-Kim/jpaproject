package com.siwoo.application.service;

import com.siwoo.application.domain.Client;
import com.siwoo.application.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service @Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    @Autowired ClientRepository clientRepository;

    @Transactional(readOnly = false)
    @Override
    public Client join(Client client) {
        Assert.notNull(client,"client must not be null");
        Assert.hasText(client.getNickname(),"nickname must contain letters");
        validateDuplicate(client);
        clientRepository.save(client);
        return client;
    }

    public static class DuplicateNickNameException extends RuntimeException{
        public DuplicateNickNameException(String message) {
            super(message);
        }
    }

    private void validateDuplicate(Client client) {
        List<Client> members = clientRepository.findByNickName(client.getNickname());
        if(!members.isEmpty()) {
            throw new DuplicateNickNameException("Client[nickname:" + client.getNickname() + "] duplicate");
        }
    }

    @Override
    public List<Client> findClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client find(long id) {
        return clientRepository.findById(id);
    }
}
