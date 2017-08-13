package models;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class TeamTest {
    @After
    public void setUp() throws Exception {
        Team team = createTeam();
        team.clearAll();
    }

    // helper method to create a new team
    public Team createTeam() {
        return new Team("Sharks", "Harvard computer science second year students");
    }

    // helper method to create a new team member
    public Member createMember() {
        return new Member("Anica White", 16);
    }

    @Test
    public void TeamIntsantiatesCorrectly_true() {
        Team testTeam = createTeam();
        assertTrue(testTeam instanceof Team);
    }

    @Test
    public void GetTeamName_GetDescriptionCorrectTeamNameAndDescriptionOfTheTeamAreReturned_teamName_description() {
        Team testTeam = createTeam();
        assertEquals("Sharks", testTeam.getTeamName());
        assertEquals("Harvard computer science second year students", testTeam.getDescription());
    }

    @Test
    public void AddMember_makeSureNewMembersCanBeAddedToTeam_arrayOfMembers() {
        Team testTeam = createTeam();
        testTeam.addMember(createMember());
        testTeam.addMember(new Member("John Grey", 17));
        assertEquals(2, testTeam.getAttendees().size());
    }

    @Test
    public void GetId_TeamIdIsReturnedCorrectly_ID() {
        Team testTeam = createTeam();
        assertEquals(0, testTeam.getId());
    }

    @Test
    public void FindById_TheSearchedMemberIsReturned_Member() {
        Team testTeam = createTeam();
        Member memberOne = createMember();
        Member memberTwo = new Member("Allison Brown", 17);
        testTeam.addMember(memberOne);
        testTeam.addMember(memberTwo);
        assertEquals(memberOne, testTeam.findById(memberOne.getMemberId()));
    }

    @Test
    public void DeleteMember_memberIsRemoved() {
        Team testTeam = createTeam();
        Member testMember = createMember();
        testTeam.addMember(testMember);
        assertEquals(0,  testTeam.deleteMember(testMember.getMemberId()).size());
    }
}