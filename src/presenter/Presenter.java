package presenter;
import model.human.Gender;
import model.human.Human;
import model.service.Rewritable;
import model.service.Service;
import view.View;
import java.time.LocalDate;
import java.util.List;

public class Presenter {
    private View view;
    private Service service;

    public Presenter(View view, Rewritable fileHandler) {
        this.view = view;
        this.service = new Service(fileHandler);
    }

    public Human addHuman(String name, LocalDate birthDate, LocalDate deathDate, List<String> children, String father,
                         String mother, Gender gender, String spouse) {
        return service.addHuman(name, birthDate, deathDate, children, father, mother, gender, spouse);
    }

    public void addInformation(String name, LocalDate birthDate, LocalDate deathDate, String father, String mother,
                               String spouse, List<String> children) {
        service.addInformation(name, birthDate, deathDate, father, mother, spouse, children);
    }

    public String getHumansList() {
        return service.getHumanList();
    }

    public String getFamilytreeInfo() {
        return service.getFamilytreeInfo();
    }

    public void loadFromFileFamilyTree(String fileName) {
        service.loadFromFileFamilyTree(fileName);
    }

    public boolean saveToFileFamilyTree(String fileName) {
        return service.saveToFileFamilyTree(fileName);
    }


}
