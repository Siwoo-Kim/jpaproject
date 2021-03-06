package com.siwoo.application.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString @Getter
@EqualsAndHashCode(of={"city","street","zipcode"})
public class Address implements Serializable{

    private String city;
    private String street;
    private String zipcode;

    protected Address() { }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
