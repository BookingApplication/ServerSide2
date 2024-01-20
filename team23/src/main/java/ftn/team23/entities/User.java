package ftn.team23.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Time;
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
    @Email(message="{user.email.format}")
    private String email;
    @Length(min=3, max=300,message = "{user.password.length}")
    private String password;
    @Length(min=1, max=50,message = "{user.name.length}")
    private String name;
    @Length(min=1, max=50,message = "{user.surname.length}")
    private String surname;
    @Length(min=1, max=50,message = "{user.livingAddress.length}")
    private String livingAddress;
    @Pattern(regexp = "^[0-9]*$", message = "{user.telephoneNumber.pattern}")
    @Length(min=6, max=10,message = "{user.telephoneNumber.length}")
    private String telephoneNumber;
    @NotNull(message = "{user.lastPasswordResetDate.notNull}")
    private Timestamp lastPasswordResetDate;
    @NotNull(message = "{user.activated.notNull}")
    private boolean activated;      //atribut koji oznacava da li je nalog aktiviran
    @Length(min=6, max=200,message = "{user.codeActivation.length}")
    private String codeActivation;  //kod koji je poslat u emailu za aktivaciju naloga
    @NotNull(message = "{user.accountVerificationRequestDate.notNull}")
    private Timestamp accountVerificationRequestDate;   //datum slanja zahteva za aktivaciju naloga
    @Length(max=50,message = "{user.profilePicture.length}")
    private String profilePicture;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

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
