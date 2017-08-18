package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by katsiarynamashokha on 8/18/17.
 */
public class MemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        final String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);
        con = sql2o.open();
    }

    // helper to create a new team member
    private Member createMember() {
        return new Member("Alice", 19, 34);
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add() throws Exception {
        Member testMember  = createMember();
        memberDao.add(testMember);
        assertEquals(1, testMember.getMemberId());
        assertEquals(34, testMember.getTeamId());
    }

    @Test
    public void currentMembersByTeams() throws Exception {
        Member testMember  = createMember();
        Member testMemberTwo  = createMember();
        memberDao.add(testMember);
        memberDao.add(testMemberTwo);
        assertEquals(2, memberDao.currentMembersByTeams(34).size());
    }

    @Test
    public void findById() throws Exception {
        Member testMember  = createMember();
        Member testMemberTwo  = createMember();
        memberDao.add(testMember);
        memberDao.add(testMemberTwo);
        Member foundMember = memberDao.findById(testMember.getMemberId());
        assertEquals(testMember, foundMember);
        assertEquals(testMember.getMemberId(), foundMember.getMemberId());
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void deleteById() throws Exception {

    }

}