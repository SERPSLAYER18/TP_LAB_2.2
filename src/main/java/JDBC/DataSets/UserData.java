package JDBC.DataSets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserData {
    long id;
    String first;
    String last;
    int age;

    public UserData(long id, String first, String last, int age) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.age = age;
    }

    public UserData(ResultSet set) throws SQLException {
        this(
                set.getLong(1),
                set.getString(2),
                set.getString(3),
                set.getInt(4));

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
        return "JDBC.DataSets.UserData{" +
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
        return id == userData.id && age == userData.age && first.equals(userData.first) && last.equals(userData.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first, last, age);
    }
}
