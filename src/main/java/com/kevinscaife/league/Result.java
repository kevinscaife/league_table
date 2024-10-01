package com.kevinscaife.league;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public class Result {
    private String homeTeam;
    private Integer homeScore;
    private String awayTeam;
    private Integer awayScore;

    public static Result from(String line) {
        final var bits = Arrays.stream(line.split(",")).map(String::trim).toArray(String[]::new);

        if(bits.length != 2 ) {
            throw new IllegalArgumentException("Invalid formatted line: " + line);
        }

        final var homeFinalSpace = bits[0].lastIndexOf(" ");
        final var awayFinalSpace = bits[1].lastIndexOf(" ");

        if(homeFinalSpace == -1 || awayFinalSpace == -1) {
            throw new IllegalArgumentException("Invalid formatted line: " + line);
        }

        return new Result(
                bits[0].substring(0, homeFinalSpace),
                Integer.parseInt(bits[0].substring(homeFinalSpace + 1)),
                bits[1].substring(0, awayFinalSpace),
                Integer.parseInt(bits[1].substring(awayFinalSpace+1)));
    }

    public Integer getHomePoints() {
        return getPoints(homeScore, awayScore);
    }

    public Integer getAwayPoints() {
        return getPoints(awayScore, homeScore);
    }

    public Integer getPoints(Integer score, Integer otherScore) {
        if (score > otherScore) {
            return 3;
        } else if (score.equals(otherScore)) {
            return 1;
        } else {
            return 0;
        }
    }

}
