package ftn.team23.dto;

import ftn.team23.entities.UserData;

public class AccountDataDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String livingAddress;
    private String telephoneNumber;

    public AccountDataDTO(){}

    public AccountDataDTO(UserData u) {this(u.getEmail(), u.getPassword(), u.getName(), u.getSurname(), u.getLivingAddress(), u.getTelephoneNumber());}

    public AccountDataDTO(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.livingAddress = livingAddress;
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
