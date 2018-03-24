package com.siwoo.application.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderItem.class)
public abstract class OrderItem_ {

	public static volatile SingularAttribute<OrderItem, Item> item;
	public static volatile SingularAttribute<OrderItem, Integer> count;
	public static volatile SingularAttribute<OrderItem, Integer> orderPrice;
	public static volatile SingularAttribute<OrderItem, Long> id;
	public static volatile SingularAttribute<OrderItem, Order> order;

}

