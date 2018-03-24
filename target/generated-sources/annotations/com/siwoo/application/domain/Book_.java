package com.siwoo.application.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ extends com.siwoo.application.domain.Item_ {

	public static volatile SingularAttribute<Book, String> author;
	public static volatile SingularAttribute<Book, String> isbn;

}

