package com.siwoo.application.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Item.class)
public abstract class Item_ {

	public static volatile SingularAttribute<Item, Integer> price;
	public static volatile SingularAttribute<Item, String> name;
	public static volatile SingularAttribute<Item, Integer> stockQuantity;
	public static volatile SingularAttribute<Item, Long> id;
	public static volatile ListAttribute<Item, Category> categories;

}

