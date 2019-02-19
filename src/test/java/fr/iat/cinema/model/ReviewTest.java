package fr.iat.cinema.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

    @Test
    public void setArticle() {
        // We test if the getter and the setter of Review works
        Review comment = new Review();
        comment.setArticle("test review");
        assertEquals("test review", comment.getArticle());
    }

    @Test
    public void initialState() {
        // We test the initial state of the comment (WAITING_MODERATION)
        Review comment = new Review();
        assertEquals(Review.WAITING_MODERATION, comment.getState());
    }

    // We test all possible good transitions and at least one bad transition by state of a comment
    @Test
    public void goodTransitionToPublished() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            assertEquals(Review.PUBLISHED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition expected");
        }
    }

    @Test
    public void badTransitionToPublished() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.validByModerator();
            fail("Transition to PUBLISHED not allowed");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.MUST_BE_MODIFIED, comment.getState());

        }
    }

    @Test
    public void goodTransitionToDeleted() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.deleteByUser();
            assertEquals(Review.DELETED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition expected");
        }
    }

    @Test
    public void badTransitionToDeleted() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.deleteByUser();
            fail("Transition to DELETED not allowed");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.MUST_BE_MODIFIED, comment.getState());

        }
    }

    @Test
    public void goodTransitionToAbandoned() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.abandonByUser();
            assertEquals(Review.ABANDONED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition expected");
        }
    }

    @Test
    public void badTransitionToAbandoned() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.abandonByUser();
            fail("Transition to ABANDONED not allowed");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.PUBLISHED, comment.getState());

        }
    }

    @Test
    public void goodTransitionToRejected() {
        Review comment = new Review();
        try {
            comment.rejectedByModerator();
            assertEquals(Review.REJECTED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition expected");
        }
    }

    @Test
    public void badTransitionToRejected() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.rejectedByModerator();
            fail("Transition to REJECTED not allowed");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.PUBLISHED, comment.getState());

        }
    }

    @Test
    public void goodTransitionToMustBeModified() {
        Review comment = new Review();
        try {
            comment.rejectedByModerator();
            assertEquals(Review.REJECTED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition expected");
        }
    }

    @Test
    public void badTransitionToMustBeModified() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.rejectedByModerator();
            fail("Transition to DELETED not allowed");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.MUST_BE_MODIFIED, comment.getState());

        }
    }

    @Test
    public void goodTransitionEditByUserFromWAITING_MODERATION() {
        Review comment = new Review();
        try {
            assertEquals(Review.WAITING_MODERATION, comment.getState());
            comment.editByUser();
            assertEquals(Review.WAITING_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("No expected transition");
        }
    }


    @Test
    public void goodTransitionEditByUserFromMUST_BE_MODIFIED() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            assertEquals(Review.MUST_BE_MODIFIED, comment.getState());
            comment.editByUser();
            assertEquals(Review.WAITING_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition expected");
        }
    }

    @Test
    public void badTransitionEditByUserFromABANDONED() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.abandonByUser();
            comment.editByUser();
            fail("Expected unauthorized transition");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.ABANDONED, comment.getState());
        }
    }
}