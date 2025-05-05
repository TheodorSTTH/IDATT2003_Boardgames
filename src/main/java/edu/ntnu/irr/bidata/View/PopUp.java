package edu.ntnu.irr.bidata.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

public class PopUp {

    /**
     * Displays an alert dialog with the given type, title, and message.
     *
     * @param alertType The type of alert (WARNING, INFORMATION, ERROR, CONFIRMATION).
     * @param title     The title of the alert window.
     * @param message   The message to display inside the alert.
     */
    public static void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Shows a warning alert.
     *
     * @param title   The title of the warning.
     * @param message The warning message.
     */
    public static void showWarning(String title, String message) {
        showAlert(AlertType.WARNING, title, message);
    }

    /**
     * Shows an error alert.
     *
     * @param title   The title of the error message.
     * @param message The error message.
     */
    public static void showError(String title, String message) {
        showAlert(AlertType.ERROR, title, message);
    }

    /**
     * Shows an information alert.
     *
     * @param title   The title of the information message.
     * @param message The information message.
     */
    public static void showInfo(String title, String message) {
        showAlert(AlertType.INFORMATION, title, message);
    }


/** !!!This method is partly created by ChatGPT!!!
     * Prompts the user to select a number between 1 and the given max number from a dropdown menu. 
     *
     * @param max The maximum number the user can select.
     * @return The number selected by the user.
     */
public static int promptForNumberInRange(String title, int max) {
    List<Integer> choices = new ArrayList<>();
    for (int i = 1; i <= max; i++) {
        choices.add(i);
    }

    ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1, choices);
    dialog.setTitle(title);
    dialog.setHeaderText(null);
    dialog.setContentText("Choose a number between 1 and " + max + ":");

    Optional<Integer> result = dialog.showAndWait();

    return result.orElse(1); // Defaults to 1 if user cancels or closes
}
    

/**
 * Shows a popup with a quiz question and checks if the answer is correct.
 *
 * @param question The question to display.
 * @param correctAnswer The correct answer to compare the user input with.
 * @return true if the user answers correctly, false otherwise.
 */
public static boolean askQuestion(String question, String correctAnswer) {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Quiz Question");
    dialog.setHeaderText(null);
    dialog.setContentText(question);

    Optional<String> result = dialog.showAndWait();

    if (result.isPresent()) {
        String userAnswer = result.get().trim();
        return userAnswer.equalsIgnoreCase(correctAnswer.trim());
    } else {
        return false; // User canceled or closed the dialog
    }
}

/**!!!This method is partly created by ChatGPT!!!*/
public static void showScrollablePopup(String title, String content) {
    // Create a non-editable TextArea to hold the content
    TextArea textArea = new TextArea(content);
    textArea.setWrapText(true);
    textArea.setEditable(false);
    textArea.setPrefWidth(400);
    textArea.setPrefHeight(300);

    // Place the TextArea in a ScrollPane
    ScrollPane scrollPane = new ScrollPane(textArea);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    // Use a Dialog for more flexibility than Alert
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle(title);
    dialog.getDialogPane().setContent(scrollPane);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.setResizable(true);

    dialog.showAndWait();
}
}
