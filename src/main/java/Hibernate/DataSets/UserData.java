package Hibernate.DataSets;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "users")
public class UserData implements Serializable { // Serializable Important to Hibernate!

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first")
    private String first;

    @Column(name = "last")
    private String last;

    @Column(name = "age")
    private int age;


    public UserData(String first, String last, int age) {
        this.first = first;
        this.last = last;
        this.age = age;
    }

    public UserData() {
    }

    public UserData(long id, String first, String last, int age) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UsersData{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id && age == userData.age && Objects.equals(first, userData.first) && Objects.equals(last, userData.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first, last, age);
    }
}