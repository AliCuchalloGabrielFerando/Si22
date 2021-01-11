package com.ali.si2.Modelos;

public class Usuario {
    private int id;
    private String name;
    private String email;
    private String password;
    private String current_team_id;
    private String profile_photo_path;
    private Boolean activo;

    public Usuario(int id, String name, String email, String password, String current_team_id, String profile_photo_path, Boolean activo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.current_team_id = current_team_id;
        this.profile_photo_path = profile_photo_path;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCurrent_team_id() {
        return current_team_id;
    }

    public void setCurrent_team_id(String current_team_id) {
        this.current_team_id = current_team_id;
    }

    public String getProfile_photo_path() {
        return profile_photo_path;
    }

    public void setProfile_photo_path(String profile_photo_path) {
        this.profile_photo_path = profile_photo_path;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
