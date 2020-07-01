package com.example.myadmin.testing101;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;

public class MyViewModel extends AndroidViewModel {
    private String username;
    private String email;

    public MyViewModel(@NonNull Application application) {
        super(application);

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

