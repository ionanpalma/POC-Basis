package lol.api.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddUser {

    @NotNull(message = "Username is mandatory")
    @Size(min = 6, max = 24, message = "Username must be between 6 and 24 characters")
    private String username;

    @NotNull(message = "Email is mandatory")
    @Email(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Email is not well formatted")
    private String email;

    @NotNull(message = "Password is mandatory")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;

    public AddUser() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}