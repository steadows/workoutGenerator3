package com.company.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a set within a workout, containing multiple exercises with their rep counts.
 */
public class WorkoutSet {
    private final int setNumber;
    private final int roundCount;
    private final List<ExerciseEntry> exercises;

    /**
     * Creates a new WorkoutSet.
     *
     * @param setNumber  the set number (1, 2, 3, etc.)
     * @param roundCount the number of rounds/times to perform this set
     * @param exercises  list of exercise entries (exercise + rep count pairs)
     * @throws IllegalArgumentException if setNumber or roundCount is less than 1
     */
    public WorkoutSet(int setNumber, int roundCount, List<ExerciseEntry> exercises) {
        if (setNumber < 1) {
            throw new IllegalArgumentException("setNumber must be at least 1, got: " + setNumber);
        }
        if (roundCount < 1) {
            throw new IllegalArgumentException("roundCount must be at least 1, got: " + roundCount);
        }
        this.setNumber = setNumber;
        this.roundCount = roundCount;
        this.exercises = exercises != null ? new ArrayList<>(exercises) : new ArrayList<>();
    }

    /**
     * Creates a new WorkoutSet with an empty exercise list.
     *
     * @param setNumber  the set number (1, 2, 3, etc.)
     * @param roundCount the number of rounds/times to perform this set
     */
    public WorkoutSet(int setNumber, int roundCount) {
        this(setNumber, roundCount, new ArrayList<>());
    }

    public int getSetNumber() {
        return setNumber;
    }

    public int getRoundCount() {
        return roundCount;
    }

    /**
     * Returns an unmodifiable view of the exercises in this set.
     *
     * @return unmodifiable list of exercise entries
     */
    public List<ExerciseEntry> getExercises() {
        return Collections.unmodifiableList(exercises);
    }

    /**
     * Adds an exercise entry to this set.
     *
     * @param entry the exercise entry to add
     */
    public void addExercise(ExerciseEntry entry) {
        if (entry != null) {
            exercises.add(entry);
        }
    }

    /**
     * Adds an exercise with its rep count to this set.
     *
     * @param exercise the exercise to add
     * @param reps     the number of reps for this exercise
     */
    public void addExercise(Exercise exercise, int reps) {
        exercises.add(new ExerciseEntry(exercise, reps));
    }

    @Override
    public String toString() {
        return String.format("WorkoutSet{setNumber=%d, roundCount=%d, exercises=%s}",
                setNumber, roundCount, exercises);
    }

    /**
     * Represents a pairing of an Exercise with its rep count for a specific set.
     */
    public static class ExerciseEntry {
        private final Exercise exercise;
        private final int reps;

        /**
         * Creates a new ExerciseEntry.
         *
         * @param exercise the exercise
         * @param reps     the number of reps for this exercise
         * @throws IllegalArgumentException if exercise is null or reps is less than 1
         */
        public ExerciseEntry(Exercise exercise, int reps) {
            if (exercise == null) {
                throw new IllegalArgumentException("exercise cannot be null");
            }
            if (reps < 1) {
                throw new IllegalArgumentException("reps must be at least 1, got: " + reps);
            }
            this.exercise = exercise;
            this.reps = reps;
        }

        public Exercise getExercise() {
            return exercise;
        }

        public int getReps() {
            return reps;
        }

        @Override
        public String toString() {
            return String.format("ExerciseEntry{exercise='%s', reps=%d}",
                    exercise.getName(), reps);
        }
    }
}
