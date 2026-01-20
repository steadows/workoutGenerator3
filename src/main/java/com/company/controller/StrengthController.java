package com.company.controller;

import com.company.model.Workout;
import com.company.model.WorkoutSet;
import com.company.model.WorkoutSet.ExerciseEntry;
import com.company.service.WorkoutService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller for the Strength Training view.
 * Handles workout configuration and generation for upper/lower body strength workouts.
 */
public class StrengthController {

    @FXML
    private ToggleGroup bodyTypeGroup;

    @FXML
    private ToggleGroup cycleGroup;

    @FXML
    private ToggleButton upperBodyToggle;

    @FXML
    private ToggleButton lowerBodyToggle;

    @FXML
    private ToggleButton cycle1Toggle;

    @FXML
    private ToggleButton cycle2Toggle;

    @FXML
    private ToggleButton cycle3Toggle;

    @FXML
    private ToggleButton cycle4Toggle;

    @FXML
    private Label cycleDescriptionLabel;

    @FXML
    private Button newWorkoutButton;

    @FXML
    private ScrollPane workoutScrollPane;

    @FXML
    private VBox workoutContainer;

    private final WorkoutService workoutService;

    public StrengthController() {
        this.workoutService = new WorkoutService();
    }

    /**
     * Initializes the controller after FXML injection.
     * Sets up listeners for toggle groups.
     */
    @FXML
    public void initialize() {
        // Ensure a toggle is always selected in body type group
        bodyTypeGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                oldVal.setSelected(true);
            }
        });

        // Ensure a toggle is always selected in cycle group and update description
        cycleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                oldVal.setSelected(true);
            } else {
                updateCycleDescription();
            }
        });
    }

    /**
     * Updates the cycle description label based on selected cycle.
     */
    private void updateCycleDescription() {
        int cycle = getSelectedCycle();
        String description;
        switch (cycle) {
            case 1:
                description = "3 sets × 3 rounds";
                break;
            case 2:
                description = "4 sets × 4 rounds";
                break;
            case 3:
                description = "4 sets × 5 rounds";
                break;
            case 4:
                description = "3 sets × 3 rounds (Deload)";
                break;
            default:
                description = "";
        }
        cycleDescriptionLabel.setText(description);
    }

    /**
     * Returns the currently selected cycle number (1-4).
     */
    private int getSelectedCycle() {
        if (cycle1Toggle.isSelected()) return 1;
        if (cycle2Toggle.isSelected()) return 2;
        if (cycle3Toggle.isSelected()) return 3;
        if (cycle4Toggle.isSelected()) return 4;
        return 1; // default
    }

    /**
     * Returns true if upper body is selected, false for lower body.
     */
    private boolean isUpperBodySelected() {
        return upperBodyToggle.isSelected();
    }

    /**
     * Handles the Generate Workout button click.
     * Generates a workout based on current selections and displays it.
     */
    @FXML
    public void handleGenerateClick(ActionEvent event) {
        int cycle = getSelectedCycle();
        Workout workout;

        if (isUpperBodySelected()) {
            workout = workoutService.generateUpperBodyWorkout(cycle);
        } else {
            workout = workoutService.generateLowerBodyWorkout(cycle);
        }

        displayWorkout(workout);

        // Show the workout area and new workout button
        workoutScrollPane.setVisible(true);
        workoutScrollPane.setManaged(true);
        newWorkoutButton.setVisible(true);
        newWorkoutButton.setManaged(true);
    }

    /**
     * Displays the generated workout in the workout container.
     *
     * @param workout the workout to display
     */
    private void displayWorkout(Workout workout) {
        workoutContainer.getChildren().clear();

        // Workout header
        Label headerLabel = new Label(workout.getType().getDisplayName() + " - Cycle " + workout.getCycle());
        headerLabel.getStyleClass().add("title-label");
        headerLabel.setStyle("-fx-font-size: 24px;");
        workoutContainer.getChildren().add(headerLabel);

        // Display each set as a card
        for (WorkoutSet set : workout.getSets()) {
            VBox setCard = createSetCard(set);
            workoutContainer.getChildren().add(setCard);
        }
    }

    /**
     * Creates a styled card for a workout set.
     *
     * @param set the workout set
     * @return a VBox styled as a card
     */
    private VBox createSetCard(WorkoutSet set) {
        VBox card = new VBox(10);
        card.getStyleClass().add("card");
        card.setPrefWidth(500);
        card.setMaxWidth(600);

        // Set header
        HBox header = new HBox(10);
        Label setLabel = new Label("Set " + set.getSetNumber());
        setLabel.getStyleClass().add("card-title");
        
        Label roundsLabel = new Label("× " + set.getRoundCount() + " rounds");
        roundsLabel.getStyleClass().add("subtitle-label");
        roundsLabel.setStyle("-fx-font-size: 14px;");
        
        header.getChildren().addAll(setLabel, roundsLabel);
        card.getChildren().add(header);

        // Exercise list
        VBox exerciseList = new VBox(8);
        exerciseList.setPadding(new Insets(10, 0, 0, 10));

        for (ExerciseEntry entry : set.getExercises()) {
            HBox exerciseRow = new HBox(10);
            
            Label exerciseName = new Label("• " + entry.getExercise().getName());
            exerciseName.getStyleClass().add("card-text");
            exerciseName.setStyle("-fx-font-size: 16px;");
            
            Label repsLabel = new Label(entry.getReps() + " reps");
            repsLabel.getStyleClass().add("card-text");
            repsLabel.setStyle("-fx-text-fill: #89b4fa; -fx-font-weight: bold;");
            
            exerciseRow.getChildren().addAll(exerciseName, repsLabel);
            exerciseList.getChildren().add(exerciseRow);
        }

        card.getChildren().add(exerciseList);
        return card;
    }

    /**
     * Handles the Back button click.
     * Navigates back to the main menu.
     *
     * @param event the action event
     */
    @FXML
    public void handleBackClick(ActionEvent event) {
        MainController.navigateToMainMenu(event);
    }
}
