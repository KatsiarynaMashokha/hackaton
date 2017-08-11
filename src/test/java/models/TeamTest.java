package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class TeamTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // helper method to create a new team
    public Team createTeam() {
        return new Team("Sharks", "Harvard computer science second year students");
    }

    @Test
    public void TeamIntsantiatesCorrectly() {
        Team testTeam = createTeam();
        assertTrue(testTeam instanceof Team);
    }

    @Test
    public void GetTeamName_GetDescriptionCorrectTeamNameAndDescriptionOfTheTeamAreReturned_teamName_description() {
        Team testTeam = createTeam();
        assertEquals("Sharks", testTeam.getTeamName());
        assertEquals("Harvard computer science second year students", testTeam.getDescription());
    }




}