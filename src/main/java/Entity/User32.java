package Entity;

public class User32 {
    private int id;
    private String username;
    private String user_address;
    private String phone;
    private String user_level;
    private String accname;
    private String accpassword;

    public User32() {
        this.id = 0;
        this.username = "admin";
        this.user_address = "admin";
        this.phone = "admin";
        this.user_level = "admin";
        this.accname = "admin";
        this.accpassword = "admin";
    }

    public User32(int id, String username, String user_address, String phone, String user_level, String accname, String accpassword) {
        this.id = id;
        this.username = username;
        this.user_address = user_address;
        this.phone = phone;
        this.user_level = user_level;
        this.accname = accname;
        this.accpassword = accpassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_address() {
        return user_address;
    }
}
