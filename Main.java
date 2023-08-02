public class Main {

    public static void main(String[] args) {
        MainView view = new MainView();
        TestingView test = new TestingView();
        VendingMachineModel model = new VendingMachineModel(view, test);
        MainController controller = new MainController(view, model, test);

        view.setVisible(true);
    }
}
