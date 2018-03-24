package com.siwoo.application.domain;

import com.siwoo.application.domain.Delivery.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Delivery.class)
public abstract class Delivery_ {

	public static volatile SingularAttribute<Delivery, Address> address;
	public static volatile SingularAttribute<Delivery, Long> id;
	public static volatile SingularAttribute<Delivery, Order> order;
	public static volatile SingularAttribute<Delivery, Status> status;

}

