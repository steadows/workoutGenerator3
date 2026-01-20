package com.company.model;

/**
 * Represents an exercise with name and rep counts for each training cycle (1-4).
 */
public class Exercise {
    private final String name;
    private final int[] cycleReps;

    /**
     * Creates a new Exercise.
     *
     * @param name      the name of the exercise
     * @param cycleReps array of rep counts for cycles 1-4 (must have 4 elements)
     * @throws IllegalArgumentException if cycleReps does not have exactly 4 elements
     */
    public Exercise(String name, int[] cycleReps) {
        if (cycleReps == null || cycleReps.length != 4) {
            throw new IllegalArgumentException("cycleReps must have exactly 4 elements");
        }
        this.name = name;
        this.cycleReps = cycleReps.clone(); // Defensive copy
    }

    /**
     * Convenience constructor for specifying reps individually.
     *
     * @param name       the name of the exercise
     * @param cycle1Reps reps for cycle 1
     * @param cycle2Reps reps for cycle 2
     * @param cycle3Reps reps for cycle 3
     * @param cycle4Reps reps for cycle 4
     */
    public Exercise(String name, int cycle1Reps, int cycle2Reps, int cycle3Reps, int cycle4Reps) {
        this(name, new int[]{cycle1Reps, cycle2Reps, cycle3Reps, cycle4Reps});
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the cycle reps array.
     *
     * @return array of rep counts for cycles 1-4
     */
    public int[] getCycleReps() {
        return cycleReps.clone();
    }

    /**
     * Gets the rep count for a specific cycle.
     *
     * @param cycle the cycle number (1-4)
     * @return the rep count for that cycle
     * @throws IllegalArgumentException if cycle is not between 1 and 4
     */
    public int getRepsForCycle(int cycle) {
        if (cycle < 1 || cycle > 4) {
            throw new IllegalArgumentException("Cycle must be between 1 and 4, got: " + cycle);
        }
        return cycleReps[cycle - 1];
    }

    @Override
    public String toString() {
        return String.format("Exercise{name='%s', cycleReps=[%d, %d, %d, %d]}",
                name, cycleReps[0], cycleReps[1], cycleReps[2], cycleReps[3]);
    }
}
