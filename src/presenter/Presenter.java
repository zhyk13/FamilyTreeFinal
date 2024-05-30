package presenter;
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

    public void addHuman(String name, LocalDate birthDate, LocalDate deathDate, List<Human> children, Human father,
                         Human mother, Gender gender, Human spouse) {
        service.addHuman(name, birthDate, deathDate, children, father, mother, gender, spouse);
    }

    public void getHumansList() {
        String info = service.getHumanList();
        view.printAnswer(info);
    }

    public void getFamilytreeInfo() {
        service.getFamilytreeInfo();
    }


}
