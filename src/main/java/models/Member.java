package models;

import java.util.UUID;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class Member {
    private String id;
    private String name;
    private int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = UUID.randomUUID().toString();
        //Team.getAttendees().add(this);

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
