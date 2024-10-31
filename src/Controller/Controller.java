package Controller;
import Model.*;
import Model.ADTs.StackInterface;
import Model.Exceptions.MyException;
import Model.Statements.StatementInterface;
import Model.Values.RefValue;
import Model.Values.ValueInterface;
import Repository.RepositoryInterface;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.stream.Stream;
import java.util.concurrent.ExecutorService;

public class Controller {
    private RepositoryInterface repository;
    private ExecutorService executor;


    public Controller(RepositoryInterface repository) {
        this.repository = repository;
    }

    public List<PrgState> getProgramStates() {
        return repository.getProgramStateList();

    }

    PrgState oneStep(PrgState state) throws MyException {
        //PrgState state = repository.getCrtPrgState();
        StackInterface<StatementInterface> stack = state.getStack();
        if (stack.isEmpty())
            throw new MyException("Stack is empty!");
        StatementInterface currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void oneStepAllPrograms(List<PrgState> programList) throws Exception {


        this.executor = Executors.newFixedThreadPool(2);

//        programList.forEach(this.repository::logPrgStateExec);


        List<Callable<PrgState>> callList = programList.stream()
                .map(program -> (Callable<PrgState>)(program::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newProgramsList = this.executor.invokeAll(callList).stream()
                .map(future -> {
                    try
                    {
                        return future.get();
                    }
                    catch(InterruptedException | ExecutionException e)
                    {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        programList.addAll(newProgramsList);

        this.repository.setProgramStateList(programList);

//        programs.forEach(this.repository::logProgramStateExecution);

//        programList.forEach(p -> {
//            try {
//                repository.logPrgStateExec(p);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        List<Callable<PrgState>> callList = programList.stream()
//                .map(p -> (Callable<PrgState>) (p::oneStep))
//                .toList();
//
//        List<PrgState> newProgramsList = executor.invokeAll(callList).stream()
//                .map(future -> {
//                    try {
//                        return future.get();
//                    } catch (InterruptedException | ExecutionException e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .filter(Objects::nonNull)
//                .toList();
//
//        programList.addAll(newProgramsList);
//        programList.forEach(p -> {
//            try {
//                repository.logPrgStateExec(p);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        repository.setProgramStateList(programList);
    }

    public void allStep() throws Exception {
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> programStateList = removeCompletedPrograms(repository.getProgramStateList());
        while(!programStateList.isEmpty()){
            programStateList
                    .forEach(program -> this.safeGarbageCollector(
                            this.getAddrFromSymTable(program.getSymTable().getContent().values()),
                            program.getHeap().getContent()));

            oneStepAllPrograms(programStateList);
            programStateList = removeCompletedPrograms(repository.getProgramStateList());
        }
        executor.shutdownNow();

        repository.setProgramStateList(programStateList);
    }


    Map<Integer, ValueInterface> safeGarbageCollector(List<Integer> addresses, Map<Integer, ValueInterface> heap) {
        return heap.entrySet().stream()
                .filter(elem -> addresses.contains(elem.getKey()) || heap.containsKey(elem.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<ValueInterface> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());

    }

    List<PrgState> removeCompletedPrograms(List<PrgState> prgList){
        return prgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }



}
