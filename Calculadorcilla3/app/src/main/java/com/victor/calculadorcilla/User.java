package com.victor.calculadorcilla;
import io.realm.RealmList;
import io.realm.RealmObject;
 import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private String name;
    private String pass;
    private int best_score4;
    private int best_score6;
    private String photo;

    public User(){
        best_score4=0;
        best_score6=0;
        photo=null;
    }

    public int getBest_score4() {
        return best_score4;
    }

    public void setBest_score4(int best_score4) {
        this.best_score4 = best_score4;
    }

    public int getBest_score6() {
        return best_score6;
    }

    public void setBest_score6(int best_score6) {
        this.best_score6 = best_score6;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }
}
