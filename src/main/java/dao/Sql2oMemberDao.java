package dao;

import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by katsiarynamashokha on 8/18/17.
 */
public class Sql2oMemberDao implements MemberDao {
    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Member member) {
        String sql = "INSERT INTO members (teamId, name, age) VALUES (:teamId, :name, :age)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("teamId", member.getTeamId())
                    .addParameter("name", member.getName())
                    .addParameter("age", member.getAge())
                    .addColumnMapping("TEAMID", "teamId")
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("AGE", "age")
                    .executeUpdate()
                    .getKey();
            member.setMemberId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Member> currentMembersByTeams(int teamId) {
        String sql = "SELECT * FROM members WHERE teamId = :teamId ";
        try(Connection con = sql2o.open()) {
          return con.createQuery(sql)
                  .addParameter("teamId", teamId)
                  .executeAndFetch(Member.class);
        }
    }

    @Override
    public Member findById(int id) {
        String sql = "SELECT * FROM members WHERE memberId = :memberId";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("memberId", id)
                    .executeAndFetchFirst(Member.class);
        }
    }

    @Override
    public void update(int memberId, String name, int age) {

    }

    @Override
    public void deleteById(int id) {

    }
}
