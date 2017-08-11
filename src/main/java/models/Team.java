package models;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class Team {
    private String teamName;
    private String description;
    private int numberOfMembers;
    private ArrayList<Member> attendees = new ArrayList<Member>();
    private static ArrayList<Team> listOfTeams = new ArrayList<>();

    public Team(String teamName, String description, int numberOfMembers) {
        this.teamName = teamName;
        this.description = description;
        this.numberOfMembers = numberOfMembers;
        listOfTeams.add(this);
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

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public static ArrayList<Team> getListOfTeams() {
        return listOfTeams;
    }
}
