package ftn.team23.dto;

import ftn.team23.entities.User;


public class LoginDTO {
    private String email;

    private String password;

    public LoginDTO() {}

    public LoginDTO(String email,
                    String password) {
        this.email = email;
        this.password = password;
    }

    public LoginDTO(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
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

}
