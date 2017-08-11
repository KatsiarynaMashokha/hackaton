package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by katsiarynamashokha on 8/11/17.
 */
public class MemberTest {

    // helper method to create a new team member
    public Member createMember() {
        return new Member("Anica White", 16);
    }

    @Test
    public void MemberInstantiatesCorrectly_true() {
        Member testMember = createMember();
        assertTrue(testMember instanceof Member);
    }

}