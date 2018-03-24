package com.siwoo.application.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ {

	public static volatile SingularAttribute<Client, Address> address;
	public static volatile SingularAttribute<Client, String> nickname;
	public static volatile ListAttribute<Client, Order> orders;
	public static volatile SingularAttribute<Client, Long> id;

}

