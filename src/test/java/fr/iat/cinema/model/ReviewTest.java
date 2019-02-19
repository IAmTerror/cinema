package fr.iat.cinema.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

    // TODO : ajouter des tests en fonction de routes ou l'utilisateur tenterait de forcer la logique du cycle...
    // de vie d'un commentaire : ex : un utilisateur tente de publier un commentaire MustBeModified

    @Test
    public void setArticle() {
        // We test if the getter and the setter of Review works
        Review comment = new Review();
        comment.setArticle("test commentaire");
        assertEquals("test commentaire", comment.getArticle());
    }

    @Test
    public void etatInitial() {
        // We test the initial state of the comment (WAITING_MODERATION)
        Review comment = new Review();
        assertEquals(Review.WAITING_MODERATION, comment.getState());
    }

    // We test all possible good transitions and at least one bad transition by state of a comment
    @Test
    public void goodTransitionToPublie() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            assertEquals(Review.PUBLISHED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToPublie() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.validByModerator();
            fail("Transition vers Publie non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.MUST_BE_MODIFIED, comment.getState());

        }
    }

    @Test
    public void goodTransitionToSupprime() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.deleteByUser();
            assertEquals(Review.DELETED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToSupprime() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.deleteByUser();
            fail("Transition vers Supprime non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.MUST_BE_MODIFIED, comment.getState());

        }
    }

    @Test
    public void goodTransitionToAbandonne() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.abandonByUser();
            assertEquals(Review.ABANDONED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToAbandonne() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.abandonByUser();
            fail("Transition vers Supprime non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.PUBLISHED, comment.getState());

        }
    }

    @Test
    public void goodTransitionToRejete() {
        Review comment = new Review();
        try {
            comment.rejectedByModerator();
            assertEquals(Review.REJECTED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToRejete() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.rejectedByModerator();
            fail("Transition vers Supprime non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.PUBLISHED, comment.getState());

        }
    }

    @Test
    public void goodTransitionToAttenteModification() {
        Review comment = new Review();
        try {
            comment.rejectedByModerator();
            assertEquals(Review.REJECTED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToAttenteModification() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.rejectedByModerator();
            fail("Transition vers Supprime non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.MUST_BE_MODIFIED, comment.getState());

        }
    }

    @Test
    public void goodTransitionToAttenteModerationFromAttenteModification() {
        Review comment = new Review();
        try {
            comment.editByUser();
            assertEquals(Review.WAITING_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void goodTransitionToAttenteModerationFromPublie() {
        Review comment = new Review();
        try {
            comment.editByUser();
            assertEquals(Review.WAITING_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void goodTransitionToAttenteModerationFromAttenteModeration() {
        Review comment = new Review();
        try {
            comment.editByUser();
            assertEquals(Review.WAITING_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }
}