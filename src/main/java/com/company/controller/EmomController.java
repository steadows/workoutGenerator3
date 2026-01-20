package com.company.controller;

import com.company.service.WorkoutService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the EMOM (Every Minute On the Minute) workout view.
 * Displays 5 random exercises with the ability to swap individual exercises.
 */
public class EmomController {

    @FXML
    private Button generateButton;

    @FXML
    private Button newWorkoutButton;

    @FXML
    private ScrollPane workoutScrollPane;

    @FXML
    private VBox exerciseContainer;

    private final WorkoutService workoutService;
    private List<String> currentExercises;

    public EmomController() {
        this.workoutService = new WorkoutService();
        this.currentExercises = new ArrayList<>();
    }

    /**
     * Initializes the controller after FXML injection.
     */
    @FXML
    public void initialize() {
        // Initial state is set in FXML
    }

    /**
     * Handles the Generate Workout / New Workout button click.
     * Generates a new EMOM workout with 5 random exercises.
     */
    @FXML
    public void handleGenerateClick(ActionEvent event) {
        currentExercises = workoutService.generateEmomWorkout();
        displayExercises();

        // Show the workout area and toggle buttons
        workoutScrollPane.setVisible(true);
        workoutScrollPane.setManaged(true);
        newWorkoutButton.setVisible(true);
        newWorkoutButton.setManaged(true);
    }

    /**
     * Displays the current exercises in the exercise container.
     */
    private void displayExercises() {
        exerciseContainer.getChildren().clear();

        // Workout header
        Label headerLabel = new Label("Your EMOM Exercises");
        headerLabel.getStyleClass().add("title-label");
        headerLabel.setStyle("-fx-font-size: 24px;");
        exerciseContainer.getChildren().add(headerLabel);

        Label instructionLabel = new Label("Perform each exercise for 1 minute, rest during remaining time");
        instructionLabel.getStyleClass().add("subtitle-label");
        instructionLabel.setStyle("-fx-font-size: 14px;");
        exerciseContainer.getChildren().add(instructionLabel);

        // Display each exercise as a card
        for (int i = 0; i < currentExercises.size(); i++) {
            VBox exerciseCard = createExerciseCard(i, currentExercises.get(i));
            exerciseContainer.getChildren().add(exerciseCard);
        }
    }

    /**
     * Creates a styled card for an exercise with a swap button.
     *
     * @param index        the index of the exercise (0-based)
     * @param exerciseName the name of the exercise
     * @return a VBox styled as a card
     */
    private VBox createExerciseCard(int index, String exerciseName) {
        VBox card = new VBox(10);
        card.getStyleClass().add("card");
        card.setPrefWidth(500);
        card.setMaxWidth(600);

        HBox content = new HBox(15);
        content.setAlignment(Pos.CENTER_LEFT);

        // Exercise number badge
        Label numberLabel = new Label(String.valueOf(index + 1));
        numberLabel.getStyleClass().add("card-title");
        numberLabel.setStyle(
            "-fx-background-color: #89b4fa;" +
            "-fx-text-fill: #1e1e2e;" +
            "-fx-min-width: 40;" +
            "-fx-min-height: 40;" +
            "-fx-alignment: center;" +
            "-fx-background-radius: 20;" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );

        // Exercise name
        Label nameLabel = new Label(exerciseName);
        nameLabel.getStyleClass().add("card-text");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        HBox.setHgrow(nameLabel, Priority.ALWAYS);

        // Swap button
        Button swapButton = new Button("Swap");
        swapButton.getStyleClass().addAll("action-button");
        swapButton.setStyle("-fx-background-color: #45475a;");
        final int exerciseIndex = index;
        swapButton.setOnAction(e -> showSwapOptions(exerciseIndex, card));

        content.getChildren().addAll(numberLabel, nameLabel, swapButton);
        card.getChildren().add(content);

        return card;
    }

    /**
     * Shows a dropdown with available exercises to swap.
     *
     * @param index the index of the exercise to swap
     * @param card  the card VBox to add the swap UI to
     */
    private void showSwapOptions(int index, VBox card) {
        // Remove any existing swap UI
        if (card.getChildren().size() > 1) {
            card.getChildren().remove(1);
        }

        List<String> available = workoutService.getAvailableEmomExercises(currentExercises);

        if (available.isEmpty()) {
            Label noOptions = new Label("No other exercises available");
            noOptions.getStyleClass().add("subtitle-label");
            card.getChildren().add(noOptions);
            return;
        }

        HBox swapUI = new HBox(10);
        swapUI.setAlignment(Pos.CENTER_LEFT);
        swapUI.setPadding(new Insets(10, 0, 0, 55));

        // ComboBox with available exercises
        ComboBox<String> exerciseCombo = new ComboBox<>();
        exerciseCombo.getItems().addAll(available);
        exerciseCombo.setPromptText("Select replacement...");
        exerciseCombo.setPrefWidth(300);
        exerciseCombo.setStyle(
            "-fx-background-color: #45475a;" +
            "-fx-text-fill: #cdd6f4;"
        );

        // Confirm button
        Button confirmButton = new Button("Confirm");
        confirmButton.getStyleClass().addAll("action-button", "generate-button");
        confirmButton.setOnAction(e -> {
            String selected = exerciseCombo.getValue();
            if (selected != null) {
                performSwap(index, selected);
            }
        });

        // Cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().addAll("action-button", "back-button");
        cancelButton.setOnAction(e -> {
            if (card.getChildren().size() > 1) {
                card.getChildren().remove(1);
            }
        });

        swapUI.getChildren().addAll(exerciseCombo, confirmButton, cancelButton);
        card.getChildren().add(swapUI);
    }

    /**
     * Performs the exercise swap and refreshes the display.
     *
     * @param index       the index of the exercise to swap
     * @param replacement the replacement exercise name
     */
    private void performSwap(int index, String replacement) {
        currentExercises = workoutService.swapEmomExercise(currentExercises, index, replacement);
        displayExercises();
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
