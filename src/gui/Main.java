package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import gui.ProgramController;
import gui.ListController;
public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader listLoader = new FXMLLoader();
        listLoader.setLocation(getClass().getResource("list.fxml"));
        Parent root = listLoader.load();
        ListController listController = listLoader.getController();
        primaryStage.setTitle("Select");
        primaryStage.setScene(new Scene(root, 500, 550));
        primaryStage.show();


        FXMLLoader programLoader = new FXMLLoader();
        programLoader.setLocation(getClass().getResource("program.fxml"));
        Parent programRoot = programLoader.load();
        ProgramController programController = programLoader.getController();
        listController.setProgramController(programController);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Interpreter");
        secondaryStage.setScene(new Scene(programRoot, 1200, 790));
        secondaryStage.show();
    }
}
