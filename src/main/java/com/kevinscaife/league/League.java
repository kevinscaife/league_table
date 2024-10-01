package com.kevinscaife.league;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class League {
    private final HashMap<String, Integer> leagueTable = new HashMap<>();

    public void addResult(Result result) {
        addTeamPoints(result.getHomeTeam(), result.getHomePoints());
        addTeamPoints(result.getAwayTeam(), result.getAwayPoints());
    }

    private void addTeamPoints(String team, int points){
        if(leagueTable.containsKey(team)){
            leagueTable.put(team, leagueTable.get(team) + points);
        } else {
            leagueTable.put(team, points);
        }
    }

    public List<String> calculateFinalPositions() {
        int position = 0;
        int lastPoints = Integer.MAX_VALUE;
        int howManyInThisPosition = 1;

        List<String> finalPositions = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : getTable()) {
            if(entry.getValue() == lastPoints) {
                // Equal points with the previous person
                // Bump how many people are in this spot.
                howManyInThisPosition += 1;
            }  else {
                // We have a lower score
                // So update the position counter with however many positions are needed.
                position += howManyInThisPosition;
                howManyInThisPosition = 1;
            }
            lastPoints = entry.getValue();

            String positionString = position +
                    ". " +
                    entry.getKey() +
                    ", " +
                    lastPoints +
                    (lastPoints == 1 ? "Pt" : "Pts"); // 1Pt or xPts.

            finalPositions.add(positionString);
        }
        return finalPositions;
    }

    private List<Map.Entry<String, Integer>> getTable() {
        return leagueTable.entrySet().stream()
                .sorted((entry1, entry2) -> {
                    final var valueSort = entry2.getValue().compareTo(entry1.getValue());
                    if (valueSort == 0) {
                        return entry1.getKey().compareTo(entry2.getKey());
                    }
                    return valueSort;
                }).toList();


    }
}
