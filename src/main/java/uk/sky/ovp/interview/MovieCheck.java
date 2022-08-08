package uk.sky.ovp.interview;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieCheck implements MovieDatabase {

    private final MovieReader movieReader = new MovieReader();

    private Stream<Movie> getMovies() {
        try {
            return MovieReader.getMovies(
                    Files.newInputStream(Path.of("src","main","resources","movies.json"))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        List<Movie> movieList = this.getMovies().filter(e -> e.getTitle() != null && e.getTitle().equals(title)).collect(Collectors.toList());

        if (movieList.size() > 0) {
            return Optional.of(movieList.get(0));
        }

        return Optional.empty();
    }

    @Override
    public Collection<Movie> fuzzyMatch(String titleRegexp) {
        return this.getMovies().filter(e -> e.getTitle().matches(titleRegexp)).collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public Collection<Movie> findByGenres(String... genres) {
        return null;
    }

    @Override
    public Collection<Movie> findByGenres(GenreQuery query) {
        return null;
    }
}
