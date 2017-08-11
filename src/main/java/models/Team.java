package models;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class Team {
    int id;
    private String teamName;
    private String description;
    private int numberOfMembers;
    ArrayList<Member> attendees = new ArrayList<>();
    private static ArrayList<Team> listOfTeams = new ArrayList<>();

    public Team(String teamName, String description) {
        this.id = listOfTeams.size();
        this.teamName = teamName;
        this.description = description;
        this.numberOfMembers = 7;
        listOfTeams.add(this);
    }

    public int getId() {
        return id;
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

    public static Team findById(int id) {
        return listOfTeams.get(id);
    }
}
