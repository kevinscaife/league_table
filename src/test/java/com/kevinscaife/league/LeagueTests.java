package com.kevinscaife.league;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class LeagueTests {
    @Test
    public void validLeagueTest() {
        final var league = new League();
        league.addResult(new Result("A", 5, "B", 3));
        league.addResult(new Result("C", 0, "D", 0));

        final var finalPositions = league.calculateFinalPositions();

        assertThat(finalPositions.getFirst()).contains("1. A, 3Pts");
        assertThat(finalPositions.get(1)).contains("2. C, 1Pt");
        assertThat(finalPositions.get(2)).contains("2. D, 1Pt");
        assertThat(finalPositions.get(3)).contains("4. B, 0Pts");
    }
}
