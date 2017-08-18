package models;

import java.util.ArrayList;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class Team {
    private int id;
    private String teamName;
    private String description;

    public Team(String teamName, String description) {
        this.teamName = teamName;
        this.description = description;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDescription() {
        return description;
    }


    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (!teamName.equals(team.teamName)) return false;
        return description.equals(team.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + teamName.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
