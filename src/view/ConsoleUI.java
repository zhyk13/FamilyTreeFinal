package view;

import model.human.Gender;
import model.human.Human;
import presenter.Presenter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleUI implements View {
    private Scanner scaner;
    private Presenter presenter;
    private boolean work;
    private MainMenu menu;

    public ConsoleUI() {
        scaner = new Scanner(System.in);
        presenter = new Presenter(this);
        work = true;
        menu = new MainMenu(this);
    }

    public void addHuman() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate birthDate = null;
        LocalDate deathDate = null;
        Gender gender;
        List<Human> children = new ArrayList<>();
        Human father = new Human(null, null, null, null, new ArrayList<>(), null,
                null, null, null);
        Human mother = new Human(null, null, null, null, new ArrayList<>(), null,
                null, null, null);
        Human spouse = new Human(null, null, null, null, new ArrayList<>(), null,
                null, null, null);
        System.out.println("Введите ФИО");
        String name = scaner.nextLine();
        System.out.println("Введите 1 если пол М и 2, если пол Ж");
        if (scaner.nextLine() == "1") {
            gender = Gender.Male;
        }
        else {
            gender = Gender.Female;
        }
        System.out.println("Введите известные данные. Если информация отсутствует нажмите ввод.");
        System.out.println("Введите дату рождения в формате yyyy-mm-dd:");
        String input = scaner.nextLine();
        if (input != "") {
            birthDate = LocalDate.parse(input, formatter);
        }
        System.out.println("Введите дату смерти в формате yyyy-mm-dd:");
        input = scaner.nextLine();
        if (input != "") {
            deathDate = LocalDate.parse(input, formatter);
        }
        System.out.println("Введмите имя отца: ");
        input = scaner.nextLine();
        if (input != "") {
            father.setName(input);
        }
        System.out.println("Введмите имя матери: ");
        input = scaner.nextLine();
        if (input != "") {
            mother.setName(input);
        }
        System.out.println("Введмите имя супруги(а): ");
        input = scaner.nextLine();
        if (input != "") {
            spouse.setName(input);
        }
        System.out.println("Ввести детей? Да - нажмите 1, Нет - нажмите 2");
        input = scaner.nextLine();
        while (Objects.equals(input, "1")) {
            if (Objects.equals(input, "1")) {
                Human kid = new Human(null, null, null, null, null, null,
                        null, null, null);
                System.out.println("Введмите имя ребенка: ");
                kid.setName(scaner.nextLine());
                children.add(kid);
                System.out.println("Ввести еще детей? Да - нажмите 1, Нет - нажмите 2");
                input = scaner.nextLine();
            }
        }
        presenter.addHuman(name, birthDate, deathDate, children, father, mother, gender, spouse);
    }

    public void getHumansList() {
        presenter.getHumansList();
    }

    public void getFamilyTreeInfo() {
        presenter.getFamilytreeInfo();
    }

    public void finish() {
        System.out.println("До свидания! Заходите еще!");
        work = false;
    }

    @Override
    public void start() {
        System.out.println("Добрый день!");
        while (work) {
            printMenu();
            execute();
        }
    }

    private void execute() {
        String line = scaner.nextLine();
        if (checkTextForInt(line)) {
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)) {
                menu.execute(numCommand);
            }
        }
    }

    private boolean checkTextForInt(String text) {
        if (text.matches("[0-9]+")){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private boolean checkCommand(int numCommand){
        if (numCommand <= menu.getSize()){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    @Override
    public void printAnswer(String answer) {
        System.out.println(answer);
    }

    private void printMenu(){
        System.out.println(menu.menu());
    }

    private void inputError(){
        System.out.println("Введено не корректное значение");
    }
}
