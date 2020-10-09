package web.model;

import java.util.Date;

public class User {

    private long id;
    private String name;
    private String lastName;
    private String email;
    private Date dateBirthday;

    public User() {
    }

    public User(long id, String name, String lastName, String email, Date dateBirthday) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateBirthday = dateBirthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(Date dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateBirthday=" + dateBirthday +
                '}';
    }
}
