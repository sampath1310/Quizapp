package AppicationFunction;

public class User {

    private String username;

    private String email;

    private String phno;

    private String catogery;

    public User(String username,String email,String phno,String catogery){
        this.catogery=catogery;
        this.username=username;
        this.email=email;
        this.phno=phno;
    }

    public  User(){

    }

    public String getCatogery() {
        return this.catogery;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhno() {
        return this.phno;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCatogery(String catogery) {
        this.catogery = catogery;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
