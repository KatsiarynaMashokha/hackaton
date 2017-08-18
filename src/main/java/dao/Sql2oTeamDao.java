package dao;

import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
        String sql = "INSERT INTO teams (teamName, description) VALUES (:teamName, :description)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql)
                    .addParameter("teamName", team.getTeamName())
                    .addParameter("description", team.getDescription())
                    .addColumnMapping("TEAMNAME", "teamName")
                    .addColumnMapping("DESCRIPTION", "description")
                    .executeUpdate()
                    .getKey();
            team.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Team> allTeams() {
        String sql = "SELECT * FROM teams";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .executeAndFetch(Team.class);
        }
    }

    @Override
    public void update(int id, String newName, String newDescription) {
       String sql = "UPDATE teams SET teamName = :teamName, description = :description WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("teamName", newName)
                    .addParameter("description", newDescription)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM teams WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public Team findById(int id) {
        String sql = "SELECT * FROM teams WHERE id = :id";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Team.class);
        }
    }
}
