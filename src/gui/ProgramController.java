package gui;
import Controller.Controller;
import Model.ADTs.HeapInterface;
import Model.ADTs.ListInterface;
import Model.ADTs.MyHeap;
import Model.ADTs.MyList;
import Model.Exceptions.MyException;
import Model.Statements.StatementInterface;
import Model.Values.StringValue;
import Model.Values.ValueInterface;
import com.sun.jdi.Value;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Model.*;

import java.util.*;
import java.util.stream.Collectors;
class Pair<T1, T2> {
    T1 first;
    T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}
public class ProgramController {
    private Controller controller;

    @FXML
    private TableView<Pair<Integer, ValueInterface>> heapTable;

    @FXML
    private TableColumn<Pair<Integer, ValueInterface>, Integer> addressColumn;

    @FXML
    private TableColumn<Pair<Integer, ValueInterface>, String> valueColumn;

    @FXML
    private ListView<String> outputList;

    @FXML
    private ListView<String> fileList;

    @FXML
    private ListView<Integer> programStateList;

    @FXML
    private ListView<String> executionStackList;

    @FXML
    private TableView<Pair<String, ValueInterface>> symbolTable;

    @FXML
    private TableColumn<Pair<String, ValueInterface>, String> symVariableColumn;

    @FXML
    private TableColumn<Pair<String, ValueInterface>, String> symValueColumn;

    @FXML
    private TextField numberOfProgramStates;

    @FXML
    private Button oneStep;

    @FXML
    public void initialize() {
        addressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().first).asObject());
        valueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        symVariableColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().first));
        symValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        oneStep.setOnAction(actionEvent -> {
            if(controller == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            boolean programStateLeft = Objects.requireNonNull(getCurrentProgramState()).getStack().isEmpty();
            if(programStateLeft){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            try {
                controller.oneStepAllPrograms(controller.getProgramStates());

                populate();
                controller.getProgramStates().removeIf(prgState -> prgState.getStack().isEmpty());
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        });
        programStateList.setOnMouseClicked(mouseEvent -> populate());
    }

    private PrgState getCurrentProgramState(){
        if (controller.getProgramStates().isEmpty())
            return null;
        int currentId = programStateList.getSelectionModel().getSelectedIndex();
        if (currentId == -1)
            return controller.getProgramStates().get(0);
        return controller.getProgramStates().get(currentId);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        populate();
    }

    private void populate() {
        populateHeap();
        populateProgramStateIdentifiers();
        populateFileTable();
        populateOutput();
        populateSymbolTable();
        populateExecutionStack();
    }

    private void populateHeap() {
        HeapInterface heap;
        if (!controller.getProgramStates().isEmpty())
            heap = controller.getProgramStates().get(0).getHeap();
        else heap = new MyHeap();
        List<Pair<Integer, ValueInterface>> heapTableList = new ArrayList<>();
        for (Map.Entry<Integer, ValueInterface> entry : heap.getContent().entrySet())
            heapTableList.add(new Pair<>(entry.getKey(), entry.getValue()));
        heapTable.setItems(FXCollections.observableList(heapTableList));
        heapTable.refresh();
    }

    private void populateProgramStateIdentifiers() {
        List<PrgState> programStates = controller.getProgramStates();
        var idList = programStates.stream().map(ps -> ps.stateID).collect(Collectors.toList());
        programStateList.setItems(FXCollections.observableList(idList));
        numberOfProgramStates.setText("" + programStates.size());
    }

    private void populateFileTable() {
        List<String> files = new ArrayList<>();
        if (!controller.getProgramStates().isEmpty()) {
            for (StringValue stringValue : controller.getProgramStates().get(0).getFileTable().keySet()) {
                files.add(stringValue.toString());
            }
        }
        fileList.setItems(FXCollections.observableArrayList(files));
    }
    private void populateOutput() {
        List<PrgState> programStates = this.controller.getProgramStates();

        if (programStates.isEmpty()) {
            this.outputList.getItems().clear();
            return;
        }

        List<ValueInterface> output = programStates.getFirst().getOutputList().getList();
        List<String> outputList = new ArrayList<>();

        output.forEach(e -> outputList.add(e.toString()));

        this.outputList.setItems(FXCollections.observableList(outputList));
        this.outputList.refresh();
    }

    private void populateSymbolTable() {
        PrgState state = getCurrentProgramState();
        List<Pair<String, ValueInterface>> symbolTableList = new ArrayList<>();
        if (state != null)
            for (Map.Entry<String, ValueInterface> entry : state.getSymTable().getContent().entrySet())
                symbolTableList.add(new Pair<>(entry.getKey(), entry.getValue()));
        symbolTable.setItems(FXCollections.observableList(symbolTableList));
        symbolTable.refresh();
    }

    private void populateExecutionStack() {
        PrgState state = getCurrentProgramState();
        List<String> executionStackListAsString = new ArrayList<>();
        if (state != null)
            for(StatementInterface s : state.getStack().getStack()){
                executionStackListAsString.add(s.toString());
            }
        executionStackList.setItems(FXCollections.observableList(executionStackListAsString));
        executionStackList.refresh();
    }
}

