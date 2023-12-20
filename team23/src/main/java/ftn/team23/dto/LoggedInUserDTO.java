package ftn.team23.dto;

import ftn.team23.entities.User;

public class LoggedInUserDTO {
    private String name;
    private String surname;
    private String email;

    LoggedInUserDTO(){}
    LoggedInUserDTO(User u) {this(u.getName(), u.getSurname(), u.getEmail());}

    public LoggedInUserDTO(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
