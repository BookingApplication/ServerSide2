package ftn.team23.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_data")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@TableGenerator(name="user_data_id_generator",
        table="primary_keys",
        pkColumnName="key_pk",
        pkColumnValue="user_data",
        valueColumnName="value_pk")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_data_id_generator")
    private Long id;
    private String email;
    private String password;
    private String username;
    private String name;
    private String surname;
    private String livingAddress;
    private String telephoneNumber;
    private Timestamp lastPasswordResetDate;
    private boolean activated;      //atribut koji oznacava da li je nalog aktiviran
    private String codeActivation;  //kod koji je poslat u emailu za aktivaciju naloga
    private Timestamp accountVerificationRequestDate;   //datum slanja zahteva za aktivaciju naloga
    private String profilePicture;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Column(name = "deleted")
    private boolean deleted;


    public User(String email, String password, String name, String surname, String livingAddress, String telephoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.livingAddress = livingAddress;
        this.telephoneNumber = telephoneNumber;
    }

    public String getUsername() {
        return email;
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

    @Override
    public boolean isEnabled() {
//        return isActivated();
        return true;
    }
}
