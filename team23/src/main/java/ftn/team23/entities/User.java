package ftn.team23.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_data")
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
public abstract class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_data_id_generator")
    private Long id;

    @Column(unique=true)
    private String email;
    private String password;
    private String name;
    private String surname;
    private String livingAddress;
    private String telephoneNumber;
    private Timestamp lastPasswordResetDate;
    private boolean activated;      //atribut koji oznacava da li je nalog aktiviran
    private String codeActivation;  //kod koji je poslat u emailu za aktivaciju naloga

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    /*@Column(name = "deleted")
    private boolean deleted;*/

    //private boolean isEmailVerified;
    public User() {
    }

    public User(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
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

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
