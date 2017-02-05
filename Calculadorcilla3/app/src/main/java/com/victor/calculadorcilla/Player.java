package com.victor.calculadorcilla;

/**
 * Created by marcos on 06/07/2015.
 */
public class Player {
    private String Photo;
    private String User;
    private int Score;

    Player(String Photo, String User, int Score){
        this.Photo = Photo;
        this.User = User;
        this.Score = Score;

    }
    Player(){

    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
}
