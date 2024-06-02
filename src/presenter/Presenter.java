package presenter;
import model.familyTree.FamilyTree;
import model.human.Gender;
import model.human.Human;
import model.service.Service;
import view.View;
import java.time.LocalDate;
import java.util.List;

public class Presenter {
    private View view;
    private Service service;

    public Presenter(View view) {
        this.view = view;
        service = new Service();
    }

    public Human addHuman(String name, LocalDate birthDate, LocalDate deathDate, List<String> children, String father,
                         String mother, Gender gender, String spouse) {
        return service.addHuman(name, birthDate, deathDate, children, father, mother, gender, spouse);
    }

    public void getHumansList() {
        String info = service.getHumanList();
        view.printAnswer(info);
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
