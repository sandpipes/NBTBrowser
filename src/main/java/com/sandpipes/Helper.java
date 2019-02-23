package com.sandpipes;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Helper {

    public static void showInfo(String info) {
        Alert alert = new Alert(AlertType.INFORMATION, info);
        alert.setHeaderText("Alert");
        alert.setTitle("Alert");
        alert.showAndWait();
    }
}
