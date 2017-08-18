package models;

import java.util.UUID;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class Member {
    private int memberId; // db autoincrement id
    private int teamId;   // id that describes belonging to particular team
    private String name;
    private int age;

    public Member(String name, int age, int teamId) {
        this.name = name;
        this.age = age;
        this.teamId = teamId;
    }

    // getters
    public int getMemberId() {
        return memberId;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // setters

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (memberId != member.memberId) return false;
        if (teamId != member.teamId) return false;
        if (age != member.age) return false;
        return name.equals(member.name);
    }

    @Override
    public int hashCode() {
        int result = memberId;
        result = 31 * result + teamId;
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        return result;
    }
}
