package edu.ntnu.irr.bidata.Wiew;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Message {

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
}