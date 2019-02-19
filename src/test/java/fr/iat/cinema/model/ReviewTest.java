package fr.iat.cinema.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

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

    @Test
    public void goodTransitionToPublie() {
        // On teste les transitions autorisées vers Publie
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
        // On teste les transitions non autorisées vers Publie
        Review comment = new Review();
        try {
            comment.retenirPourModification();
            comment.valider();
            fail("Transition vers Publie non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.ATTENTE_MODIFICATION, comment.getState());

        }
    }
}