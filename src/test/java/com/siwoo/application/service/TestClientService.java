package com.siwoo.application.service;

import com.siwoo.application.domain.Address;
import com.siwoo.application.domain.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/app-context.xml")
public class TestClientService {

    @Autowired ApplicationContext c;
    @Autowired ClientService clientService;

    private static List<Client> fixtures;
    @Before
    public void setup() {
        assertNotNull(c);
        fixtures = new ArrayList<>();
        Client client = new Client();
        client.setNickname("siwooKing1");
        client.setAddress(new Address("Toronto","Altamontd Dr","msj s3s"));
        fixtures.add(client);
    }


    @Test
    public void join() {
        Client client = fixtures.get(0);
        clientService.join(client);
        Assert.notNull(client.getId(),"Id must be generated");
        log.info(client.toString());
    }

    @Test(expected = ClientServiceImpl.DuplicateNickNameException.class)
    public void duplicateJoin() {
        Client client = fixtures.get(0);
        clientService.join(client);
        Assert.notNull(client.getId(),"Id must be generated");
        log.info(client.toString());

        clientService.join(client);
        fail("expect duplicate nickname exception");
    }

}
