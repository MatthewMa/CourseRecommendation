package matthew.pecompass.components.Model;

/**
 * Created by Sihua on 2016/11/28.
 */

public class User {
    public int userId;
    public String userEmail;
    public String userPwd;
    public String userName;
    public int Karma;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(int userId, String userName, int karma) {
        this.userId = userId;
        this.userName=userName;
        Karma = karma;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public int getKarma() {
        return Karma;
    }

    public void setKarma(int karma) {
        Karma = karma;
    }
}
