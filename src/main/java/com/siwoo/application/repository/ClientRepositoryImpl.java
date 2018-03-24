package com.siwoo.application.repository;

import com.siwoo.application.domain.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository @Transactional(readOnly = true)
public class ClientRepositoryImpl implements ClientRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = false)
    @Override
    public void save(Client client){
        if(client.getId() == null) {
            entityManager.persist(client);
        } else {
            entityManager.merge(client);
        }
    }

    @Override
    public Client findById(Long id) {
        return entityManager.find(Client.class,id);
    }

    private static final String FIND_BY_NAME = "select c from Client c where c.nickname = :nickname ";
    @Override
    public List<Client> findByNickName(String nickname) {
        Assert.hasText(nickname,"nickname must contain letters");
        return entityManager.createQuery(FIND_BY_NAME,Client.class)
                .setParameter("nickname",nickname)
                .getResultList();
    }

    private static final String FIND_ALL = "select c from Client c ";
    @Override
    public List<Client> findAll() {
        return entityManager.createQuery(FIND_ALL,Client.class).getResultList();
    }


}
