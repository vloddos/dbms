package com.dbms.gui;

import com.dbms.logic.ExpressionExecutor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainController {
    @FXML
    private Button executeButton;

    @FXML
    private TextArea inputArea;

    @FXML
    private TextArea outputArea;

    @FXML
    void initialize() {
        executeButton.setOnAction(event -> {
            //new ExpressionExecutor().execute(inputArea.getText());
        });
    }
}
