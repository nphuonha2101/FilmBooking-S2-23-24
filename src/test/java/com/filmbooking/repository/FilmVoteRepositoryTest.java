package com.filmbooking.repository;
import com.filmbooking.model.FilmVote;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class FilmVoteRepositoryTest {

                @Test
                void select() {
                    assertDoesNotThrow(() -> {
                        FilmVoteRepository filmVoteRepository = new FilmVoteRepository();
                        System.out.println(filmVoteRepository.selectAll());
                    });
                }
}
