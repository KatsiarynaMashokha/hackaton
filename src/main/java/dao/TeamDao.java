package dao;

import models.Team;

import java.util.List;

/**
 * Created by katsiarynamashokha on 8/18/17.
 */
public interface TeamDao {
    // create a new team
    void add(Team team);

    // get all teams
    List<Team> allTeams();

    // update an existing team
    void update(int id, String newName, String newDescription);

    // delete a single team
    void deleteById(int id);

    // find a team by its id
    Team findById(int id);
}
