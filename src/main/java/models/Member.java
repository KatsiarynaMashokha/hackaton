package models;

import java.util.UUID;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class Member {
    private String memberId;
    private String name;
    private int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
        this.memberId = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMemberId() {
        return memberId;
    }
}
