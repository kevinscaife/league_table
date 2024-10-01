package com.kevinscaife.league;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LeagueApplication {
    public static void main(String[] args) {
        // Check if the filename is provided as an argument
        if (args.length < 1) {
            System.err.println("Usage: java -jar build/lib/league-1.0.0 <filename>");
            System.exit(1);
        }

        // Create a Path object
        Path filePath = Paths.get(args[0]);

        final var league = new League();

        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> league.addResult(Result.from(line)));

        } catch (IOException e) {
            System.err.println("Error reading file " + filePath.getFileName() + ": " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing file " + filePath.getFileName() + ": " + e.getMessage());
        }

        league.calculateFinalPositions().stream().forEach(System.out::println);
    }
}
