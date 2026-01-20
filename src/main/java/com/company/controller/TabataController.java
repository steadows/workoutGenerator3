package com.company.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller for the Tabata workout view.
 * Currently a placeholder - Tabata workouts are coming soon.
 */
public class TabataController {

    /**
     * Initializes the controller after FXML injection.
     */
    @FXML
    public void initialize() {
        // Placeholder - no initialization needed yet
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
