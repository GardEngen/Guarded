/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.triga.guardedserver;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gard
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class AppUser implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.TABLE)
    @Column(name = "AppUser_ID")
     Long id;
     String firstName;
     String lastName;
     String password;
     Integer phoneNumber;
     String familyCode;
     Boolean guardian;

    public AppUser() {
    }

    public AppUser(String firstName, String lastName, String password, Integer phoneNumber, String familyCode, Boolean guardian) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.familyCode = familyCode;
        this.guardian = guardian;
    }
     
     
}
