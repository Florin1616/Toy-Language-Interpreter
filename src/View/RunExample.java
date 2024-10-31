package View;

public class RunExample extends Command{
    private Controller.Controller controller;

    public RunExample(String key, String desc, Controller.Controller controller) {
        super(key, desc);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.allStep();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
