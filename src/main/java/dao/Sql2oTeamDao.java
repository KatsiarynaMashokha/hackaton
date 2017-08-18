package dao;

import models.Team;
import org.sql2o.Sql2o;

import java.util.List;

/**
 * Created by katsiarynamashokha on 8/18/17.
 */
public class Sql2oTeamDao implements TeamDao{
    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Team team) {

    }

    @Override
    public List<Team> allTeams() {
        return null;
    }

    @Override
    public void update(int id, String newName) {

    }

    @Override
    public void deleteById(int id) {

    }
}
