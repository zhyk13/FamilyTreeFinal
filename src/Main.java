import model.service.FileHandler;
import model.service.Rewritable;
import presenter.Presenter;
import view.ConsoleUI;
import view.View;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Rewritable fileHandler = new FileHandler();
        View view = new ConsoleUI(fileHandler);
        Presenter presenter = new Presenter(view, fileHandler);
        view.start();
    }
}