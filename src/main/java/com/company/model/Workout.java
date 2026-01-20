package com.company.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a complete workout containing a type, cycle number, and a list of workout sets.
 */
public class Workout {
    
    /**
     * Enum representing the different types of workouts.
     */
    public enum WorkoutType {
        STRENGTH_UPPER("Upper Body Strength"),
        STRENGTH_LOWER("Lower Body Strength"),
        EMOM("EMOM"),
        TABATA("Tabata");

        private final String displayName;

        WorkoutType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private final WorkoutType type;
    private final int cycle;
    private final List<WorkoutSet> sets;

    /**
     * Creates a new Workout.
     *
     * @param type  the type of workout
     * @param cycle the training cycle number (1-4, or 0 for workouts without cycles like EMOM)
     * @param sets  the list of workout sets
     * @throws IllegalArgumentException if type is null or cycle is negative
     */
    public Workout(WorkoutType type, int cycle, List<WorkoutSet> sets) {
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        if (cycle < 0) {
            throw new IllegalArgumentException("cycle cannot be negative, got: " + cycle);
        }
        this.type = type;
        this.cycle = cycle;
        this.sets = sets != null ? new ArrayList<>(sets) : new ArrayList<>();
    }

    /**
     * Creates a new Workout with an empty sets list.
     *
     * @param type  the type of workout
     * @param cycle the training cycle number (1-4, or 0 for workouts without cycles like EMOM)
     */
    public Workout(WorkoutType type, int cycle) {
        this(type, cycle, new ArrayList<>());
    }

    /**
     * Creates a new Workout without a cycle (cycle defaults to 0).
     * Useful for EMOM and Tabata workouts that don't use cycles.
     *
     * @param type the type of workout
     */
    public Workout(WorkoutType type) {
        this(type, 0, new ArrayList<>());
    }

    public WorkoutType getType() {
        return type;
    }

    public int getCycle() {
        return cycle;
    }

    /**
     * Returns an unmodifiable view of the workout sets.
     *
     * @return unmodifiable list of workout sets
     */
    public List<WorkoutSet> getSets() {
        return Collections.unmodifiableList(sets);
    }

    /**
     * Adds a workout set to this workout.
     *
     * @param set the workout set to add
     */
    public void addSet(WorkoutSet set) {
        if (set != null) {
            sets.add(set);
        }
    }

    /**
     * Returns the total number of sets in this workout.
     *
     * @return the number of sets
     */
    public int getSetCount() {
        return sets.size();
    }

    /**
     * Checks if this workout uses training cycles.
     *
     * @return true if this is a strength workout with cycles, false otherwise
     */
    public boolean hasCycle() {
        return type == WorkoutType.STRENGTH_UPPER || type == WorkoutType.STRENGTH_LOWER;
    }

    @Override
    public String toString() {
        return String.format("Workout{type=%s, cycle=%d, sets=%d}",
                type.getDisplayName(), cycle, sets.size());
    }
}
