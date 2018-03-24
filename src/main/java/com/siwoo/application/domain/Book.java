package com.siwoo.application.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Getter @Setter @ToString(callSuper = true)
@Entity @DiscriminatorValue("book")
public class Book extends Item  implements Serializable {

    private String author;
    private String isbn;

}
