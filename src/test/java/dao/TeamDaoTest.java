package dao;

import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by katsiarynamashokha on 8/18/17.
 */
public class TeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        final String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    // helper to create a new team
    private Team createTeam() {
        return new Team("Eagles", "Second year harvard CS female students");
    }

    @Test
    public void add() throws Exception {
        Team testTeam = createTeam();
        Team testTeamTwo = createTeam();
        teamDao.add(testTeam);
        teamDao.add(testTeamTwo);
        assertEquals(1, testTeam.getId());
        assertEquals(2, testTeamTwo.getId());
    }

    @Test
    public void allTeams() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void deleteById() throws Exception {

    }

}