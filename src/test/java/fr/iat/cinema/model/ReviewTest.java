package fr.iat.cinema.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

    // TODO : renommer les methodes de test et les messages d'exception en anglais

    @Test
    public void setArticle() {
        // On teste si le getter et le setter de Review fonctionne
        Review comment = new Review();
        comment.setArticle("test commentaire");
        assertEquals("test commentaire", comment.getArticle());
    }

    @Test
    public void etatInitial() {
        // On teste l'état initial du commentaire (AttenteModération)
        Review comment = new Review();
        assertEquals(Review.ATTENTE_MODERATION, comment.getState());
    }

    // On teste toutes les good transitions possibles et au moins un bad transition par état d'un commentaire
    @Test
    public void goodTransitionToPublie() {
        Review comment = new Review();
        try {
            comment.valider();
            assertEquals(Review.PUBLIE, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToPublie() {
        Review comment = new Review();
        try {
            comment.retenirPourModification();
            comment.valider();
            fail("Transition vers Publie non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.ATTENTE_MODIFICATION, comment.getState());

        }
    }

    @Test
    public void goodTransitionToSupprime() {
        Review comment = new Review();
        try {
            comment.valider();
            comment.supprimer();
            assertEquals(Review.SUPPRIME, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToSupprime() {
        Review comment = new Review();
        try {
            comment.retenirPourModification();
            comment.supprimer();
            fail("Transition vers Supprime non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.ATTENTE_MODIFICATION, comment.getState());

        }
    }

    @Test
    public void goodTransitionToAbandonne() {
        Review comment = new Review();
        try {
            comment.retenirPourModification();
            comment.annuler();
            assertEquals(Review.ABANDONNE, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToAbandonne() {
        Review comment = new Review();
        try {
            comment.valider();
            comment.annuler();
            fail("Transition vers Supprime non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.PUBLIE, comment.getState());

        }
    }

    @Test
    public void goodTransitionToRejete() {
        Review comment = new Review();
        try {
            comment.rejeter();
            assertEquals(Review.REJETE, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToRejete() {
        Review comment = new Review();
        try {
            comment.valider();
            comment.rejeter();
            fail("Transition vers Supprime non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.PUBLIE, comment.getState());

        }
    }

    @Test
    public void goodTransitionToAttenteModification() {
        Review comment = new Review();
        try {
            comment.rejeter();
            assertEquals(Review.REJETE, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToAttenteModification() {
        Review comment = new Review();
        try {
            comment.retenirPourModification();
            comment.rejeter();
            fail("Transition vers Supprime non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.ATTENTE_MODIFICATION, comment.getState());

        }
    }

    @Test
    public void goodTransitionToAttenteModerationFromAttenteModification() {
        Review comment = new Review();
        try {
            comment.editer();
            assertEquals(Review.ATTENTE_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void goodTransitionToAttenteModerationFromPublie() {
        Review comment = new Review();
        try {
            comment.editer();
            assertEquals(Review.ATTENTE_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void goodTransitionToAttenteModerationFromAttenteModeration() {
        Review comment = new Review();
        try {
            comment.editer();
            assertEquals(Review.ATTENTE_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }
}