package Repository;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Statements.StatementInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Repository implements RepositoryInterface {
    private String logFilePath;
//    private List<PrgState> programStateList;
    List<PrgState> programStateList = new ArrayList<>();



//    public Repository(PrgState programStateList, String logFilePath) {
//        this.programStateList.add(programStateList);
//        this.logFilePath = logFilePath;
//    }
//    public Repository(PrgState prgState, String fileName) throws IOException, MyException {
////        this.originalProgram = prgState.getOriginalProgram();
//        this.logFilePath = fileName;
//        File yourFile = new File(fileName);
//        yourFile.createNewFile();
//        try (FileWriter fileWriter = new FileWriter(yourFile)) {
//            fileWriter.write("");
//        }
//        catch (IOException e) {
//            throw new MyException(e.getMessage());
//        }
//        programStateList = new LinkedList<>();
//    }
    public Repository(PrgState ps, String filePath) {
        programStateList.add(ps);
        this.logFilePath = filePath;
    }

    @Override
    public void logPrgStateExec(PrgState state) throws IOException {
        try {
            PrintWriter logfile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logfile.write(state.toString());
            logfile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PrgState getCrtPrgState() {
        return programStateList.get(programStateList.size() - 1);
    }

    @Override
    public void setProgramStateList(List<PrgState> programStateList) {
        this.programStateList = programStateList;

    }

    @Override
    public List<PrgState> getProgramStateList() {
        return programStateList;

    }
}
