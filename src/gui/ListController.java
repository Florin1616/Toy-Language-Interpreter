package gui;

//package view.gui.list;

import Controller.Controller;

import Model.Exceptions.MyException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import Model.PrgState;
import Model.Statements.StatementInterface;
import Repository.RepositoryInterface;
import Repository.Repository;
import gui.Examples;
import gui.ProgramController;

public class ListController {
    private ProgramController programController;

    public void setProgramController(ProgramController programController) {
        this.programController = programController;
    }

    @FXML
    private ListView<StatementInterface> statements;

    @FXML
    private Button displayButton;

    @FXML
    public void initialize() {
        statements.setItems(FXCollections.observableArrayList(Examples.exampleList()));
        displayButton.setOnAction(actionEvent -> {
            int index = statements.getSelectionModel().getSelectedIndex();
            if (index < 0)
                return;
            PrgState state = null;
            try {
                state = new PrgState(Examples.exampleList()[index]);
            } catch (MyException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            }
            RepositoryInterface repository =  new Repository(state, "log.txt");
            Controller controller = new Controller(repository);

            programController.setController(controller);
//              System.out.println("Program selected: " + state.toString());

        });
    }
}