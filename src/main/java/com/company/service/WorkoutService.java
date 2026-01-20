package com.company.service;

import com.company.model.Exercise;
import com.company.model.Workout;
import com.company.model.Workout.WorkoutType;
import com.company.model.WorkoutSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Service for generating randomized workouts.
 * Handles the business logic for creating strength, EMOM, and Tabata workouts.
 */
public class WorkoutService {

    private final Random random;

    public WorkoutService() {
        this.random = new Random();
    }

    /**
     * Constructor for testing with a seeded random.
     *
     * @param random the Random instance to use
     */
    public WorkoutService(Random random) {
        this.random = random;
    }

    // ============================================================
    // UPPER BODY WORKOUT GENERATION
    // ============================================================

    /**
     * Generates a randomized upper body workout for the given cycle.
     *
     * Cycle structure:
     * - Cycle 1: 3 sets × 3 rounds
     * - Cycle 2: 4 sets × 4 rounds (includes tertiary exercises)
     * - Cycle 3: 4 sets × 5 rounds (includes tertiary exercises)
     * - Cycle 4: 3 sets × 3 rounds (deload)
     *
     * @param cycle the training cycle (1-4)
     * @return a fully populated Workout object
     * @throws IllegalArgumentException if cycle is not between 1 and 4
     */
    public Workout generateUpperBodyWorkout(int cycle) {
        validateCycle(cycle);

        // Get mutable copies of exercise pools
        List<Exercise> compoundPush = ExerciseRepository.getUpperBodyCompoundPushMutable();
        List<Exercise> compoundPull = ExerciseRepository.getUpperBodyCompoundPullMutable();
        List<Exercise> secondary = ExerciseRepository.getUpperBodySecondaryMutable();
        List<Exercise> tertiary = ExerciseRepository.getUpperBodyTertiaryMutable();

        Workout workout = new Workout(WorkoutType.STRENGTH_UPPER, cycle);
        int roundCount = getRoundCount(cycle);

        // Set 1: Compound Push + 2 secondaries
        WorkoutSet set1 = new WorkoutSet(1, roundCount);
        addRandomExercise(set1, compoundPush, cycle);
        addRandomExercise(set1, secondary, cycle);
        addRandomExercise(set1, secondary, cycle);
        workout.addSet(set1);

        // Set 2: Compound Pull + 2 secondaries (+ tertiary in cycles 2&3)
        WorkoutSet set2 = new WorkoutSet(2, roundCount);
        addRandomExercise(set2, compoundPull, cycle);
        addRandomExercise(set2, secondary, cycle);
        addRandomExercise(set2, secondary, cycle);
        if (cycle == 2 || cycle == 3) {
            addRandomExercise(set2, tertiary, cycle);
        }
        workout.addSet(set2);

        // Set 3: Compound Push + 2 secondaries (+ tertiary in cycles 2&3)
        WorkoutSet set3 = new WorkoutSet(3, roundCount);
        addRandomExercise(set3, compoundPush, cycle);
        addRandomExercise(set3, secondary, cycle);
        addRandomExercise(set3, secondary, cycle);
        if (cycle == 2 || cycle == 3) {
            addRandomExercise(set3, tertiary, cycle);
        }
        workout.addSet(set3);

        // Set 4 (cycles 2&3 only): Compound Pull + 2 secondaries
        if (cycle == 2 || cycle == 3) {
            WorkoutSet set4 = new WorkoutSet(4, roundCount);
            addRandomExercise(set4, compoundPull, cycle);
            addRandomExercise(set4, secondary, cycle);
            addRandomExercise(set4, secondary, cycle);
            workout.addSet(set4);
        }

        return workout;
    }

    // ============================================================
    // LOWER BODY WORKOUT GENERATION
    // ============================================================

    /**
     * Generates a randomized lower body workout for the given cycle.
     *
     * Cycle structure:
     * - Cycle 1: 3 sets × 3 rounds
     * - Cycle 2: 4 sets × 4 rounds (includes tertiary exercises)
     * - Cycle 3: 4 sets × 5 rounds (includes tertiary exercises)
     * - Cycle 4: 3 sets × 3 rounds (deload)
     *
     * @param cycle the training cycle (1-4)
     * @return a fully populated Workout object
     * @throws IllegalArgumentException if cycle is not between 1 and 4
     */
    public Workout generateLowerBodyWorkout(int cycle) {
        validateCycle(cycle);

        // Get mutable copies of exercise pools
        List<Exercise> compoundAlpha = ExerciseRepository.getLowerBodyCompoundAlphaMutable();
        List<Exercise> compoundBeta = ExerciseRepository.getLowerBodyCompoundBetaMutable();
        List<Exercise> compoundGamma = ExerciseRepository.getLowerBodyCompoundGammaMutable();
        List<Exercise> secondary = ExerciseRepository.getLowerBodySecondaryMutable();
        List<Exercise> tertiary = ExerciseRepository.getLowerBodyTertiaryMutable();

        Workout workout = new Workout(WorkoutType.STRENGTH_LOWER, cycle);
        int roundCount = getRoundCount(cycle);

        // Set 1: Compound Alpha (squat) + 2 secondaries
        WorkoutSet set1 = new WorkoutSet(1, roundCount);
        addRandomExercise(set1, compoundAlpha, cycle);
        addRandomExercise(set1, secondary, cycle);
        addRandomExercise(set1, secondary, cycle);
        workout.addSet(set1);

        // Set 2: Compound Beta (deadlift) + 2 secondaries (+ tertiary in cycles 2&3)
        WorkoutSet set2 = new WorkoutSet(2, roundCount);
        addRandomExercise(set2, compoundBeta, cycle);
        addRandomExercise(set2, secondary, cycle);
        addRandomExercise(set2, secondary, cycle);
        if (cycle == 2 || cycle == 3) {
            addRandomExercise(set2, tertiary, cycle);
        }
        workout.addSet(set2);

        // Set 3: Compound Gamma (explosive) + 2 secondaries (+ tertiary in cycles 2&3)
        WorkoutSet set3 = new WorkoutSet(3, roundCount);
        addRandomExercise(set3, compoundGamma, cycle);
        addRandomExercise(set3, secondary, cycle);
        addRandomExercise(set3, secondary, cycle);
        if (cycle == 2 || cycle == 3) {
            addRandomExercise(set3, tertiary, cycle);
        }
        workout.addSet(set3);

        // Set 4 (cycles 2&3 only): Compound Gamma + 2 secondaries
        if (cycle == 2 || cycle == 3) {
            WorkoutSet set4 = new WorkoutSet(4, roundCount);
            addRandomExercise(set4, compoundGamma, cycle);
            addRandomExercise(set4, secondary, cycle);
            addRandomExercise(set4, secondary, cycle);
            workout.addSet(set4);
        }

        return workout;
    }

    // ============================================================
    // EMOM WORKOUT GENERATION
    // ============================================================

    /**
     * Generates a randomized EMOM workout with 5 unique exercises.
     *
     * @return a list of 5 exercise names
     */
    public List<String> generateEmomWorkout() {
        List<String> exercisePool = ExerciseRepository.getEmomExercisesMutable();
        Collections.shuffle(exercisePool, random);
        return new ArrayList<>(exercisePool.subList(0, 5));
    }

    /**
     * Returns the available exercises for swapping (excludes currently selected).
     *
     * @param currentExercises the currently selected EMOM exercises
     * @return list of available exercises for replacement
     */
    public List<String> getAvailableEmomExercises(List<String> currentExercises) {
        List<String> available = ExerciseRepository.getEmomExercisesMutable();
        available.removeAll(currentExercises);
        return available;
    }

    /**
     * Swaps an exercise in the current EMOM workout with a replacement.
     *
     * @param current     the current list of EMOM exercises
     * @param index       the index (0-based) of the exercise to swap out
     * @param replacement the replacement exercise name
     * @return a new list with the swapped exercise
     * @throws IllegalArgumentException if index is out of bounds
     * @throws IllegalArgumentException if replacement is already in the list
     */
    public List<String> swapEmomExercise(List<String> current, int index, String replacement) {
        if (index < 0 || index >= current.size()) {
            throw new IllegalArgumentException(
                    "Index must be between 0 and " + (current.size() - 1) + ", got: " + index);
        }
        if (current.contains(replacement)) {
            throw new IllegalArgumentException(
                    "Replacement exercise is already in the workout: " + replacement);
        }

        List<String> updated = new ArrayList<>(current);
        updated.set(index, replacement);
        return updated;
    }

    // ============================================================
    // PRIVATE HELPER METHODS
    // ============================================================

    /**
     * Validates that cycle is between 1 and 4.
     */
    private void validateCycle(int cycle) {
        if (cycle < 1 || cycle > 4) {
            throw new IllegalArgumentException("Cycle must be between 1 and 4, got: " + cycle);
        }
    }

    /**
     * Returns the number of rounds for each set based on cycle.
     * Cycle 1: 3 rounds, Cycle 2: 4 rounds, Cycle 3: 5 rounds, Cycle 4: 3 rounds (deload)
     */
    private int getRoundCount(int cycle) {
        switch (cycle) {
            case 1:
            case 4:
                return 3;
            case 2:
                return 4;
            case 3:
                return 5;
            default:
                return 3;
        }
    }

    /**
     * Picks a random exercise from the pool, adds it to the set with appropriate reps,
     * and removes it from the pool to prevent duplicates.
     */
    private void addRandomExercise(WorkoutSet set, List<Exercise> pool, int cycle) {
        if (pool.isEmpty()) {
            throw new IllegalStateException("Exercise pool is empty - cannot add more exercises");
        }
        int index = random.nextInt(pool.size());
        Exercise exercise = pool.remove(index);
        int reps = exercise.getRepsForCycle(cycle);
        set.addExercise(exercise, reps);
    }
}
