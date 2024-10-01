package com.kevinscaife.league;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResultTests {
    @ParameterizedTest
    @CsvSource(delimiter = ':', textBlock = """
                    Lions 1, Tigers 2:Lions:0:Tigers:3
                    Lions 3, Tigers 1:Lions:3:Tigers:0
                    Lions 2 5, Tigers 5:Lions 2:1:Tigers:1
            """)
    void tests_good_result_parsing(
            String line,
            String expectedHomeTeam, Integer expectedHomePoints,
            String expectedAwayTeam, Integer expectedAwayPoints) {

        final var result = Result.from(line);

        assertThat(result).isNotNull();
        assertThat(result.getHomeTeam()).isEqualTo(expectedHomeTeam);
        assertThat(result.getAwayTeam()).isEqualTo(expectedAwayTeam);
        assertThat(result.getHomePoints()).isEqualTo(expectedHomePoints);
        assertThat(result.getAwayPoints()).isEqualTo(expectedAwayPoints);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ':', textBlock = """
                    Lions 1,
                    Lions 1: Tiger 2"
                    Lions1, Tiger 2
                    Lions 1, Tiger2
                    Lions A, Tiger 2,
                    Lions 1, Tiger B
""")
    void tests_throw_IllegalArgumentException(String line) {
        assertThrows(IllegalArgumentException.class, () -> Result.from(line));
    }
}
