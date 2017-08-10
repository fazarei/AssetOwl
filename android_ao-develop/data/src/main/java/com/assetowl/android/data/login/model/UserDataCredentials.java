package com.assetowl.android.data.login.model;

import com.assetowl.domain.login.model.UserCredentials;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by patrickyin on 14/2/17.
 */

public class UserDataCredentials implements UserCredentials {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    public UserDataCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // immutable
    public void setUsername(String email) {
        throw new IllegalStateException();
    }

    // immutable
    public void setPassword(String password) {
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDataCredentials that = (UserDataCredentials) o;

        if (!email.equals(that.email)) return false;
        return password.equals(that.password);

    }
    public boolean equalsUsername(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDataCredentials that = (UserDataCredentials) o;
        return email.equals(that.email);

    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
