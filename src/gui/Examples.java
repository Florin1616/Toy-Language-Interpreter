package gui;

import Model.*;
import Model.Expressions.*;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

public class Examples {
    public static StatementInterface buildExample(StatementInterface... statements) {
        if (statements.length == 0)
            return new NopStmt();
        if (statements.length == 1)
            return statements[0];
        StatementInterface finalStatement = new CompStmt(statements[0], statements[1]);
        for (int i = 2; i < statements.length; ++i)
            finalStatement = new CompStmt(finalStatement, statements[i]);
        return finalStatement;
    }

    public static StatementInterface[] exampleList() {
        StatementInterface example1 = buildExample(
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(2))), new PrintStmt(new VarExpression("v")))));
        StatementInterface example2 = buildExample(
                new CompStmt(new VarDeclStmt("a", new IntType()),
                        new CompStmt(new VarDeclStmt("b", new IntType()),
                                new CompStmt(new AssignStmt("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new
                                        ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                        new CompStmt(new AssignStmt("b", new ArithmeticExpression('+', new VarExpression("a"), new ValueExpression(new
                                                IntValue(1)))), new PrintStmt(new VarExpression("b"))))))
        );
        StatementInterface example3 = buildExample(
                new CompStmt(new VarDeclStmt("a", new BoolType()),
                        new CompStmt(new VarDeclStmt("v", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExpression(new BoolValue(false))),
                                        new CompStmt(new IfStmt(new VarExpression("a"), new AssignStmt("v", new ValueExpression(new
                                                IntValue(2))), new AssignStmt("v", new ValueExpression(new IntValue(3)))), new PrintStmt(new
                                                VarExpression("v"))))))
        );
        StatementInterface example4 = buildExample(
                new CompStmt(new VarDeclStmt("varf", new StringType()),
                        new CompStmt(new AssignStmt("varf", new ValueExpression(new StringValue("C:\\SEMESTRUL 3 UBB\\MAP\\LABS\\HWLAB3\\test.in"))),
                                new CompStmt(new OpenRFileStmt(new VarExpression("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt( new ReadFileStmt(new VarExpression("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExpression("varc")),
                                                        new CompStmt(new ReadFileStmt(new VarExpression("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExpression("varc")), new CloseRFileStmt(new VarExpression("varf"))))))))))
        );
        StatementInterface example5 = buildExample(
                new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStmt(new NewHeapStmt("v", new ValueExpression(new IntValue(20))),
                                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompStmt(new NewHeapStmt("a", new VarExpression("v")),
                                                new CompStmt(new PrintStmt(new VarExpression("v")),
                                                        new PrintStmt(new VarExpression("a"))
                                                )
                                        )
                                )
                        )
                )
        );
        StatementInterface example6 = buildExample(
                new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
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
                )

        );
        StatementInterface example7 = buildExample(
                new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
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
                )
        );
        StatementInterface example8 = buildExample(
                new CompStmt(new VarDeclStmt("var", new IntType()),
                        new CompStmt(new AssignStmt("var", new ValueExpression(new IntValue(5))),
                                new WhileStmt(new RelationalExpression(new VarExpression("var"), new ValueExpression(new IntValue(0)),
                                        4), new CompStmt(new PrintStmt(new VarExpression("var")), new AssignStmt("var",
                                        new ArithmeticExpression('-', new VarExpression("var"), new ValueExpression(new IntValue(1))))))))
        );
        StatementInterface example9 = buildExample(
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
        StatementInterface example10 = buildExample(
                new CompStmt(new VarDeclStmt("v", new IntType()),
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
                )
        );

        return new StatementInterface[]{example1, example2, example3, example4, example5, example6, example7, example8, example9, example10};
    }
}
