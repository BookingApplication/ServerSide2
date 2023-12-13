package ftn.team23.dto;

import ftn.team23.entities.UserData;

public class LoginDTO {
    private String email;

    private String password;

    public LoginDTO() {}

    public LoginDTO(String email,
                    String password) {
        this.email = email;
        this.password = password;
    }

    public LoginDTO(UserData userData){
        this.email = userData.getEmail();
        this.password = userData.getPassword();
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
