package models;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class Team {
    private String teamName;
    private String description;
    private ArrayList<Member> attendees = new ArrayList<Member>();

    public Team(String teamName, String description) {
        this.teamName = teamName;
        this.description = description;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Member> addMember(Member newMember) {
       attendees.add(newMember);
       return attendees;
    }

    public ArrayList<Member> getAttendees() {
        return attendees;
    }
}
