package org.example.model;

import java.util.Objects;

public class User {
    private String uesrId;
    private String name;


    public User(String uesrId, String name) {
        this.uesrId = uesrId;
        this.name = name;
    }

    public boolean equalUser(User user){
        return this.equals(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(uesrId, user.uesrId) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uesrId, name);
    }
}
