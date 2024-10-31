package Repository;
import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Statements.StatementInterface;

import javax.swing.event.InternalFrameEvent;
import java.io.IOException;
import java.util.List;

public interface RepositoryInterface {
    PrgState getCrtPrgState();
    List<PrgState> getProgramStateList();
    void setProgramStateList(List<PrgState> programStateList);
    void logPrgStateExec(PrgState state) throws IOException;
}
