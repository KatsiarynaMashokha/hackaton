package dao;

import models.Member;

import java.util.List;

/**
 * Created by katsiarynamashokha on 8/18/17.
 */
public interface MemberDao {
    // create a new member
    void add(Member member);

    // show all members of the given team
    List<Member> currentMembersByTeams(int teamId);

    // find a members by its id
    Member findById(int id);

    // update a member's information
    void update(int memberId, String name, int age);

    // delete a member
    void deleteById(int id);
}
