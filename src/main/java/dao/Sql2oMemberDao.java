package dao;

import models.Member;
import org.sql2o.Sql2o;

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

    }

    @Override
    public List<Member> currentMembers() {
        return null;
    }

    @Override
    public Member findById(int id) {
        return null;
    }

    @Override
    public void update(int memberId, String name, int age) {

    }

    @Override
    public void deleteById(int id) {

    }
}
