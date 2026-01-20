package com.company.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the main menu view.
 * Handles navigation to Strength, EMOM, and Tabata workout views.
 */
public class MainController {

    /**
     * Handles click on the Strength Training button.
     * Navigates to the Strength workout view.
     *
     * @param event the action event
     */
    @FXML
    public void handleStrengthClick(ActionEvent event) {
        navigateToView(event, "StrengthView.fxml");
    }

    /**
     * Handles click on the EMOM button.
     * Navigates to the EMOM workout view.
     *
     * @param event the action event
     */
    @FXML
    public void handleEmomClick(ActionEvent event) {
        navigateToView(event, "EmomView.fxml");
    }

    /**
     * Handles click on the Tabata button.
     * Navigates to the Tabata workout view.
     *
     * @param event the action event
     */
    @FXML
    public void handleTabataClick(ActionEvent event) {
        navigateToView(event, "TabataView.fxml");
    }

    /**
     * Navigates to a specified view by loading its FXML file.
     *
     * @param event    the action event (used to get the current stage)
     * @param fxmlFile the name of the FXML file to load
     */
    private void navigateToView(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/company/view/" + fxmlFile));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.err.println("Error loading view: " + fxmlFile);
            e.printStackTrace();
        }
    }

    /**
     * Utility method to navigate back to the main menu.
     * Can be called from other controllers.
     *
     * @param event the action event
     */
    public static void navigateToMainMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/com/company/view/MainView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.err.println("Error loading main menu");
            e.printStackTrace();
        }
    }
}
