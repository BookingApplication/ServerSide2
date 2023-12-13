package ftn.team23.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String email;
    private String password;
    private boolean isEmailVerified;

    private String name;
    private String surname;
    private String livingAddress;
    private String telephoneNumber;

    public UserData() {
    }

    public UserData(String email, String password, boolean isEmailVerified, String name, String surname, String livingAddress, String telephoneNumber) {
        this.email = email;
        this.password = password;
        this.isEmailVerified = isEmailVerified;
        this.name = name;
        this.surname = surname;
        this.livingAddress = livingAddress;
        this.telephoneNumber = telephoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
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
