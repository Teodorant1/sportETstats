package sample;

public class account {

    String username;
    String email;
    String status;
    String password;
    int lastgameid;

    public account(String username, String email, String status, String password, int lastgameid) {
        this.username = username;
        this.email = email;
        this.status = status;
        this.password = password;
        this.lastgameid = lastgameid;
    }
    public int getLastgameid() {
        return lastgameid;
    }

    public void setLastgameid(int lastgameid) {
        this.lastgameid = lastgameid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
