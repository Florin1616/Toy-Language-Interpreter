//package View;
//import Model.ADTs.DictionaryInterface;
////import a.MyIDictionary;
//import Controller.Controller;
//import Model.Exceptions.MyException;
//import Model.PrgState;
//import Model.Statements.StatementInterface;
//
//import java.io.IOException;
//import java.util.InputMismatchException;
//import java.util.List;
//import java.util.Scanner;
//
//public class UI {
//    private List<PrgState> programs;
//    private Controller controller;
//    public UI(Controller ctrl,List<PrgState> programs)
//    {
//        this.controller=ctrl;
//        this.programs=programs;
//    }
//
//    private void printmenu()
//    {
//        System.out.println("Program list:");
//        for(int i=1;i<=programs.size();i++)
//        {
//            System.out.println((i)+": "+programs.get(i-1).toString());
//
//        }
//        System.out.println("0: Exit\n");
//    }
//
//    public void runmenu()
//    {
//        Scanner scanner=new Scanner(System.in);
//        while(true)
//        {
//            printmenu();
//            System.out.println("Enter an option from 0 to " + programs.size() + ".");
//
//            System.out.println("Choose a program: ");
//            int option=-1;
//            try{
//                option = scanner.nextInt();
//                if(option-1<programs.size() && option>-1) {
//                    this.controller.setCurrentProgram(option-1);
//                    this.controller.allStep();
//                }
//                if(option == 0)
//                    System.exit(0);
//            }
//            catch (InputMismatchException e)
//            {
//                System.out.println("Invalid input. Please provide a valid integer.");
//            } catch (MyException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
