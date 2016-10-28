package com.triga.guarded;

/**
 * Created by Gard and Trine!! :) on 27.10.2016.
 */

public class AppUser {
    private String firstName;
    private String lastName;
    private String password;
    private Integer phoneNumber;
    private String familyCode;
    private Boolean guardian;

    public AppUser(String firstName, String lastName, String password, Integer phoneNumber, String familyCode, Boolean guardian) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.familyCode = familyCode;
        this.guardian = guardian;
    }

    public Boolean getGuardian() {
        return guardian;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getphoneNumber() {
        return phoneNumber;
    }

    public String getFamilyCode() {
        return familyCode;
    }
}
