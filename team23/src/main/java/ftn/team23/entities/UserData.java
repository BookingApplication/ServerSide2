package ftn.team23.entities;

import ftn.team23.enums.UserRole;
import jakarta.persistence.*;
//import lombok.Data;

import java.io.Serializable;

@Entity
////opcija 2, uz identity generated value
//@Inheritance(strategy = InheritanceType.JOINED)
////opcija 3, uz identity generated value
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
////

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@TableGenerator(name="user_data_id_generator",
        table="primary_keys",
        pkColumnName="key_pk",
        pkColumnValue="user_data",
        valueColumnName="value_pk")
public class UserData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(unique=true)
    private String email;
    private String password;
    private String name;
    private String surname;
    private String livingAddress;
    private String telephoneNumber;
    private boolean activated;      //atribut koji oznacava da li je nalog aktiviran
    private String codeActivation;  //kod koji je poslat u emailu za aktivaciju naloga

    @Enumerated(EnumType.STRING)
    private UserRole role;          //enumeracija za role (HOST, GUEST, ADMIN)

    @Column(name = "deleted")
    private boolean deleted;

    //private boolean isEmailVerified;
    public UserData() {
    }

    public UserData(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.livingAddress = livingAddress;
        this.telephoneNumber = telephoneNumber;
        //this.isEmailVerified = isEmailVerified;
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

    /*
    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }
    */

}
