package com.company.service;

import com.company.model.Exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repository containing all exercise data for workout generation.
 * Exercises are organized by body part and category.
 */
public class ExerciseRepository {

    // ============================================================
    // UPPER BODY EXERCISES
    // ============================================================

    /**
     * Returns compound push exercises for upper body workouts.
     */
    public static List<Exercise> getUpperBodyCompoundPush() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Floor Press", 10, 12, 15, 8));
        exercises.add(new Exercise("Push or Strict Press", 8, 10, 12, 6));
        return Collections.unmodifiableList(exercises);
    }

    /**
     * Returns compound pull exercises for upper body workouts.
     */
    public static List<Exercise> getUpperBodyCompoundPull() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Kettlebell Pullover", 10, 12, 15, 8));
        exercises.add(new Exercise("Single Arm KB Row", 10, 12, 15, 8));
        exercises.add(new Exercise("Pull-Ups", 5, 7, 9, 5));
        return Collections.unmodifiableList(exercises);
    }

    /**
     * Returns secondary exercises for upper body workouts.
     */
    public static List<Exercise> getUpperBodySecondary() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Banded Lat Pulldowns", 15, 18, 20, 12));
        exercises.add(new Exercise("Banded Lateral Raises", 10, 12, 15, 8));
        exercises.add(new Exercise("Upright Rows", 8, 10, 12, 6));
        exercises.add(new Exercise("Dips", 6, 8, 10, 6));
        exercises.add(new Exercise("Mace 360s", 10, 12, 15, 8));
        exercises.add(new Exercise("Row to Suitcase DL to Shrug", 5, 7, 9, 5));
        exercises.add(new Exercise("KB Curls", 8, 10, 12, 8));
        exercises.add(new Exercise("Push-Ups", 10, 12, 15, 10));
        exercises.add(new Exercise("Front Raises", 8, 10, 12, 8));
        exercises.add(new Exercise("Kettlebell Skull Crushers", 8, 10, 12, 8));
        exercises.add(new Exercise("Face Pulls", 8, 10, 12, 8));
        return Collections.unmodifiableList(exercises);
    }

    /**
     * Returns tertiary exercises for upper body workouts.
     */
    public static List<Exercise> getUpperBodyTertiary() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Banded Face Pulls", 10, 12, 15, 8));
        exercises.add(new Exercise("Banded Curls", 10, 12, 15, 8));
        exercises.add(new Exercise("Russian Twist", 8, 10, 12, 8));
        exercises.add(new Exercise("Plank", 30, 30, 45, 30));
        exercises.add(new Exercise("Sit Throughs", 5, 7, 9, 5));
        exercises.add(new Exercise("Banded Delt Reverse Flyes", 10, 12, 15, 8));
        exercises.add(new Exercise("Toe Touches", 8, 10, 12, 8));
        return Collections.unmodifiableList(exercises);
    }

    // ============================================================
    // LOWER BODY EXERCISES
    // ============================================================

    /**
     * Returns compound alpha exercises (squat variations) for lower body workouts.
     */
    public static List<Exercise> getLowerBodyCompoundAlpha() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Double Kettlebell Front Squat", 8, 10, 12, 6));
        exercises.add(new Exercise("Goblet Squat", 8, 10, 12, 6));
        return Collections.unmodifiableList(exercises);
    }

    /**
     * Returns compound beta exercises (deadlift variations) for lower body workouts.
     */
    public static List<Exercise> getLowerBodyCompoundBeta() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Double Kettlebell Deadlift", 8, 10, 12, 6));
        exercises.add(new Exercise("Goblet RDL", 10, 12, 15, 8));
        return Collections.unmodifiableList(exercises);
    }

    /**
     * Returns compound gamma exercises (explosive/unilateral) for lower body workouts.
     */
    public static List<Exercise> getLowerBodyCompoundGamma() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("KB Clean", 6, 8, 10, 5));
        exercises.add(new Exercise("KB Snatch", 8, 10, 12, 6));
        exercises.add(new Exercise("Bulgarian Split Squat", 8, 10, 12, 8));
        return Collections.unmodifiableList(exercises);
    }

    /**
     * Returns secondary exercises for lower body workouts.
     */
    public static List<Exercise> getLowerBodySecondary() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Lateral Lunge", 5, 7, 9, 5));
        exercises.add(new Exercise("Hike Clean", 5, 7, 9, 5));
        exercises.add(new Exercise("KB Swings", 12, 15, 20, 10));
        exercises.add(new Exercise("Warrior Lunge", 10, 12, 12, 10));
        exercises.add(new Exercise("Warrior Squat", 10, 12, 12, 10));
        exercises.add(new Exercise("Kneeling Get-Ups", 5, 7, 9, 5));
        exercises.add(new Exercise("Deck Squat", 6, 8, 10, 6));
        exercises.add(new Exercise("KB Good Mornings", 10, 12, 15, 8));
        exercises.add(new Exercise("Steel Club Switch Squats", 5, 7, 9, 5));
        return Collections.unmodifiableList(exercises);
    }

    /**
     * Returns tertiary exercises for lower body workouts.
     */
    public static List<Exercise> getLowerBodyTertiary() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Slingshots", 10, 12, 15, 10));
        exercises.add(new Exercise("Plank", 30, 30, 45, 30));
        exercises.add(new Exercise("Sit-Throughs", 5, 7, 9, 5));
        return Collections.unmodifiableList(exercises);
    }

    // ============================================================
    // EMOM EXERCISES
    // ============================================================

    /**
     * Returns all available EMOM exercises.
     * Note: EMOM exercises are stored as simple strings without cycle reps.
     */
    public static List<String> getEmomExercises() {
        List<String> exercises = new ArrayList<>();
        exercises.add("12 cal Airbike");
        exercises.add("Payloads");
        exercises.add("Figure 8 to Cleans");
        exercises.add("Deadlifts");
        exercises.add("Double Clean + Press");
        exercises.add("Sandbag Cleans");
        exercises.add("KB Snatch");
        exercises.add("Thrusters");
        exercises.add("360's");
        exercises.add("Single Kettlebell Clean + Press + Overhead Lunge");
        exercises.add("Barbell Hang Cleans");
        exercises.add("Barbell Front Squats");
        exercises.add("Double Indian Club Clean to Squat");
        exercises.add("Double Kettlebell Dragonflies");
        exercises.add("Rope Slams");
        exercises.add("Sled Face Pulls -> Pushes");
        exercises.add("Sled Pushes");
        exercises.add("Rotational Bag Slams");
        exercises.add("Single Kettlebell Row + Dead Clean + Strict Press");
        exercises.add("Figure 8 to Tactical Snatch");
        exercises.add("Single Indian Club Swing Clean to Pullover");
        return Collections.unmodifiableList(exercises);
    }

    // ============================================================
    // UTILITY METHODS
    // ============================================================

    /**
     * Returns a mutable copy of the upper body compound push exercises.
     */
    public static List<Exercise> getUpperBodyCompoundPushMutable() {
        return new ArrayList<>(getUpperBodyCompoundPush());
    }

    /**
     * Returns a mutable copy of the upper body compound pull exercises.
     */
    public static List<Exercise> getUpperBodyCompoundPullMutable() {
        return new ArrayList<>(getUpperBodyCompoundPull());
    }

    /**
     * Returns a mutable copy of the upper body secondary exercises.
     */
    public static List<Exercise> getUpperBodySecondaryMutable() {
        return new ArrayList<>(getUpperBodySecondary());
    }

    /**
     * Returns a mutable copy of the upper body tertiary exercises.
     */
    public static List<Exercise> getUpperBodyTertiaryMutable() {
        return new ArrayList<>(getUpperBodyTertiary());
    }

    /**
     * Returns a mutable copy of the lower body compound alpha exercises.
     */
    public static List<Exercise> getLowerBodyCompoundAlphaMutable() {
        return new ArrayList<>(getLowerBodyCompoundAlpha());
    }

    /**
     * Returns a mutable copy of the lower body compound beta exercises.
     */
    public static List<Exercise> getLowerBodyCompoundBetaMutable() {
        return new ArrayList<>(getLowerBodyCompoundBeta());
    }

    /**
     * Returns a mutable copy of the lower body compound gamma exercises.
     */
    public static List<Exercise> getLowerBodyCompoundGammaMutable() {
        return new ArrayList<>(getLowerBodyCompoundGamma());
    }

    /**
     * Returns a mutable copy of the lower body secondary exercises.
     */
    public static List<Exercise> getLowerBodySecondaryMutable() {
        return new ArrayList<>(getLowerBodySecondary());
    }

    /**
     * Returns a mutable copy of the lower body tertiary exercises.
     */
    public static List<Exercise> getLowerBodyTertiaryMutable() {
        return new ArrayList<>(getLowerBodyTertiary());
    }

    /**
     * Returns a mutable copy of the EMOM exercises.
     */
    public static List<String> getEmomExercisesMutable() {
        return new ArrayList<>(getEmomExercises());
    }
}
