package models;

import java.util.ArrayList;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class Team {
    private int id;
    private String teamName;
    private String description;
    public ArrayList<Member> attendees = new ArrayList<>();
    private static ArrayList<Team> listOfTeams = new ArrayList<>();

    public Team(String teamName, String description) {
        this.id = listOfTeams.size();
        this.teamName = teamName;
        this.description = description;
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

    public static ArrayList<Team> getListOfTeams() {
        return listOfTeams;
    }

    public static Team findById(int id) {
        return listOfTeams.get(id);
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Member findById(String memberId) {
        Member result = null;
        for (Member member : attendees) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return result;
    }

    public ArrayList<Member> deleteMember(String memberId) {
        Member member = findById(memberId);
        attendees.remove(member);
        return attendees;
    }

    public void clearAll() {
        attendees.clear();
    }
}
