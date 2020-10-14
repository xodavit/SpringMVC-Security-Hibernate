package web.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

//    @Column
//    private Date dateBirthday;

    public User() {
    }

    public User(String name, String lastName, String email/*, Date dateBirthday*/) {

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        //this.dateBirthday = dateBirthday;
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

//    public Date getDateBirthday() {
//        return dateBirthday;
//    }
//
//    public void setDateBirthday(Date dateBirthday) {
//        this.dateBirthday = dateBirthday;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                //", dateBirthday=" + dateBirthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(lastName, user.lastName) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, email);
    }
}
