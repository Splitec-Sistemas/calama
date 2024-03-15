package org.splitec.model;

public class User {

    public User(
            String username,
            String password,
            int skinType)
    {
        this.id = username;
        this.password = password;
        this.healthPoints = 100;
        this.skinType = skinType;
    }

    private String id;
    private String password;
    private int healthPoints;
    private String name;
    private int skinType;
    private String refreshToken;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkinType() {
        return skinType;
    }

    public void setSkinType(int skinType) {
        this.skinType = skinType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}


