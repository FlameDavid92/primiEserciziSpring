package it.corsobackendtree.provaspringjava15.models;

import java.util.Objects;

public class UserCriptato {
    private final String username;
    private final int passwordCriptata;

    public UserCriptato(String username, String password) {
        this.username = username;
        passwordCriptata = password.hashCode();
    }

    //getter
    public String getUsername() {
        return username;
    }
    public int getPasswordCriptata() {
        return passwordCriptata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCriptato that = (UserCriptato) o;
        return Objects.equals(username, that.username);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
