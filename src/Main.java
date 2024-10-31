import Controller.Controller;
import Model.Exceptions.MyException;
import Model.Expressions.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.ValueInterface;
import Repository.RepositoryInterface;
import Repository.Repository;
import View.TextMenu;

import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import View.*;

public class Main {
    public static void main(String[] args) throws MyException, IOException {
        TextMenu menu = new TextMenu();



        StatementInterface ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(2))), new PrintStmt(new VarExpression("v"))));

        try {
            PrgState prg1 = new PrgState(ex1);
            RepositoryInterface repo1 = new Repository(prg1, "log1.txt");
            Controller controller1 = new Controller(repo1);
            menu.addCommand(new RunExample("1", ex1.toString(), controller1));

        }catch (MyException e){
            System.out.println(e);
        }


        StatementInterface ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new
                                ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithmeticExpression('+', new VarExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStmt(new VarExpression("b"))))));

        try {
            PrgState prg2 = new PrgState(ex2);
            RepositoryInterface repo2 = new Repository(prg2, "log2.txt");
            Controller controller2 = new Controller(repo2);
            menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        }catch(MyException e){
            System.out.println(e);
        }


        StatementInterface ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExpression(new BoolValue(false))),
                                new CompStmt(new IfStmt(new VarExpression("a"), new AssignStmt("v", new ValueExpression(new
                                        IntValue(2))), new AssignStmt("v", new ValueExpression(new IntValue(3)))), new PrintStmt(new
                                        VarExpression("v"))))));
        try {
            PrgState prg3 = new PrgState(ex3);
            RepositoryInterface repo3 = new Repository(prg3, "log3.txt");
            Controller controller3 = new Controller(repo3);
            menu.addCommand(new RunExample("3", ex3.toString(), controller3));

        }catch (MyException e){
            System.out.println(e);
        }


        StatementInterface ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExpression(new StringValue("C:\\SEMESTRUL 3 UBB\\MAP\\LABS\\HWLAB3\\test.in"))),
                        new CompStmt(new OpenRFileStmt(new VarExpression("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()),
                                new CompStmt( new ReadFileStmt(new VarExpression("varf"), "varc"),
                                        new CompStmt(new PrintStmt(new VarExpression("varc")),
                                                new CompStmt(new ReadFileStmt(new VarExpression("varf"), "varc"),
                                                        new CompStmt(new PrintStmt(new VarExpression("varc")), new CloseRFileStmt(new VarExpression("varf"))))))))));

        try {
            PrgState prg4 = new PrgState(ex4);
            RepositoryInterface repo4 = new Repository(prg4, "log4.txt");
            Controller controller4 = new Controller(repo4);
            menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        }catch (MyException e){
            System.out.println(e);
        }

        StatementInterface ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v", new ValueExpression(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewHeapStmt("a", new VarExpression("v")),
                                        new CompStmt(new PrintStmt(new VarExpression("v")),
                                                new PrintStmt(new VarExpression("a"))
                                        )
                                )
                        )
                )
        );

        try {
            PrgState prg5 = new PrgState(ex5);
            RepositoryInterface repo5 = new Repository(prg5, "log5.txt");
            Controller controller5 = new Controller(repo5);
            menu.addCommand(new RunExample("5", ex5.toString(), controller5));
        }catch (MyException e) {
            System.out.println(e);
        }


        StatementInterface ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v", new ValueExpression(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewHeapStmt("a", new VarExpression("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExpression(new VarExpression("v"))),
                                                new PrintStmt(new ArithmeticExpression('+',
                                                        new ReadHeapExpression(new ReadHeapExpression(new VarExpression("a"))),
                                                        new ValueExpression(new IntValue(5))
                                                )
                                                )
                                        )
                                )
                        )
                )
        );

        try {
            PrgState prg6 = new PrgState(ex6);
            RepositoryInterface repo6 = new Repository(prg6, "log6.txt");
            Controller controller6 = new Controller(repo6);
            menu.addCommand(new RunExample("6", ex6.toString(), controller6));
        }catch (MyException e){
            System.out.println(e);
        }


        StatementInterface ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v", new ValueExpression(new IntValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeapExpression(new VarExpression("v"))),
                                new CompStmt(new WriteHeapStmt("v", new ValueExpression(new IntValue(30))),
                                        new PrintStmt(new ArithmeticExpression('+',
                                                new ReadHeapExpression(new VarExpression("v")),
                                                new ValueExpression(new IntValue(5))
                                        )
                                        )
                                )
                        )
                )
        );

        try {
            PrgState prg7 = new PrgState(ex7);
            RepositoryInterface repo7 = new Repository(prg7, "log7.txt");
            Controller controller7 = new Controller(repo7);
            menu.addCommand(new RunExample("7", ex7.toString(), controller7));
        }catch (MyException e){
            System.out.println(e);
        }


        StatementInterface ex8 = new CompStmt(new VarDeclStmt("var", new IntType()),
                new CompStmt(new AssignStmt("var", new ValueExpression(new IntValue(5))),
                        new WhileStmt(new RelationalExpression(new VarExpression("var"), new ValueExpression(new IntValue(0)),
                                4), new CompStmt(new PrintStmt(new VarExpression("var")), new AssignStmt("var",
                                new ArithmeticExpression('-', new VarExpression("var"), new ValueExpression(new IntValue(1))))))));
        try {
            PrgState prg8 = new PrgState(ex8);
            RepositoryInterface repo8 = new Repository(prg8, "log8.txt");
            Controller controller8 = new Controller(repo8);
            menu.addCommand(new RunExample("8", ex8.toString(), controller8));
        }catch (MyException e){
            System.out.println(e);
        }


        StatementInterface ex9 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewHeapStmt("v", new ValueExpression(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewHeapStmt("a", new VarExpression("v")),
                                        new CompStmt(
                                                new NewHeapStmt("v", new ValueExpression(new IntValue(30))),
                                                new PrintStmt(
                                                        new ReadHeapExpression(
                                                                new ReadHeapExpression(new VarExpression("a"))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        try {
            PrgState prg9 = new PrgState(ex9);
            RepositoryInterface repo9 = new Repository(prg9, "log9.txt");
            Controller controller9 = new Controller(repo9);
            menu.addCommand(new RunExample("9", ex9.toString(), controller9));
        }catch (MyException e){
            System.out.println(e);
        }

        StatementInterface ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(10))),
                                new CompStmt(new NewHeapStmt("a", new ValueExpression(new IntValue(22))),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new WriteHeapStmt("a", new ValueExpression(new IntValue(30))),
                                                        new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExpression("v")),
                                                                        new PrintStmt(new ReadHeapExpression(new VarExpression("a")))
                                                                )
                                                        )
                                                )
                                        ),
                                                new CompStmt(new PrintStmt(new VarExpression("v")),
                                                        new PrintStmt(new ReadHeapExpression(new VarExpression("a")))
                                                )
                                        )
                                )
                        )
                )
        );

        try {
            PrgState prg10 = new PrgState(ex10);
            RepositoryInterface repo10 = new Repository(prg10, "log10.txt");
            Controller controller10 = new Controller(repo10);
            menu.addCommand(new RunExample("10", ex10.toString(), controller10));
        }catch (MyException e){
            System.out.println(e);
        }

        menu.addCommand(new ExitCommand("0", "exit"));
        menu.show();

    }
}
