package uk.sky.ovp.interview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieDatabaseTest {
    private MovieCheck movieCheck = new MovieCheck();

    @Test
    void findByNameWorksInAbsentScenario() {
        Optional<Movie> movie = movieCheck.findByTitle("Matrix 4");
        assertFalse(movie.isPresent());
    }

    @Test
    void findByNameThatIsPresentScenario() {
        Optional<Movie> movie = movieCheck.findByTitle("The Container of pudding");
        assertTrue(movie.isPresent());
    }

    @Test
    void findByNameThatHasPudding() {
        Collection<Movie> movieCollection = movieCheck.fuzzyMatch(".*pudding*.");
        assertTrue(!movieCollection.isEmpty());
    }
}
