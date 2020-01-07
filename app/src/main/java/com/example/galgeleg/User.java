package com.example.galgeleg;

public class User {

    private int userID;
    private String userName;
    private int userScore;

    public int getUserID(){
        return userID;
    }
    public String getUserName(){return userName;}
    public int getUserScore(){return userScore;}

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
}
