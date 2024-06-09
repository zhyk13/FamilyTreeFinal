package view;

import model.human.Gender;
import model.human.Human;
import model.service.Rewritable;
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
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public ConsoleUI(Rewritable fileHandler) {
        scaner = new Scanner(System.in);
        presenter = new Presenter(this, fileHandler);
        work = true;
        menu = new MainMenu(this);
    }

    public void addHuman() {
        Human human;
        LocalDate birthDate = null;
        LocalDate deathDate = null;
        Gender gender;
        List<String> children = new ArrayList<>();
        String name;
        String father = null;
        String mother = null;
        String spouse = null;
        String kidName;
        name = inputName("Введите ФИО");
        System.out.println("Введите 1 если пол М и 2, если пол Ж");
        if (scaner.nextLine().equals("1")) {
            gender = Gender.Male;
        }
        else {
            gender = Gender.Female;
        }
        System.out.println("Введите известные данные. Если информация отсутствует нажмите ввод.");
        birthDate = inputDate("Введите дату рождения в формате yyyy-mm-dd:");
        deathDate = inputDate("Введите дату смерти в формате yyyy-mm-dd:");
        father = inputInfo("Введмите имя отца: ");
        mother = inputInfo("Введмите имя матери: ");
        spouse = inputInfo("Введмите имя супруги(а): ");
        System.out.println("Ввести детей? Да - нажмите 1, Нет - нажмите 2");
        String input = scaner.nextLine();
        while (Objects.equals(input, "1")) {
            if (Objects.equals(input, "1")) {
                System.out.println("Введите 1 если пол ребенка М и 2, если пол ребенка Ж");
                if (scaner.nextLine().equals("1")) {
                    kidName = "1";
                }
                else {
                    kidName = "2";
                }
                System.out.println("Введмите имя ребенка: ");
                kidName += scaner.nextLine();
                children.add(kidName);
                System.out.println("Ввести еще детей? Да - нажмите 1, Нет - нажмите 2");
                input = scaner.nextLine();
            }
        }
        human = presenter.addHuman(name, birthDate, deathDate, children, father, mother, gender, spouse);
        System.out.println(human.getName() + " добавлен в семейное дерево!");
    }

    public String inputName(String message) {
        System.out.println(message);
        String input = scaner.nextLine();
        return input;
    }

    public String inputInfo(String message) {
        System.out.println(message);
        String input = scaner.nextLine();
        if (input != "") {
            return input;
        }
        else {
            return null;
        }
    }

    public LocalDate inputDate(String message) {
        System.out.println(message);
        String input = scaner.nextLine();
        if (input != "") {
            return LocalDate.parse(input, formatter);
        }
        else {
            return null;
        }
    }

    public void getHumansList() {
        System.out.println(presenter.getHumansList());
    }

    public void getFamilyTreeInfo() {
        System.out.println(presenter.getFamilytreeInfo());
    }

    public void AddInformation() {
        String name;
        LocalDate birthDate = null;
        LocalDate deathDate = null;
        String father = null;
        String mother = null;
        String spouse = null;
        List<String> children = new ArrayList<>();
        String kidName;
        System.out.println("Введите имя человека о котором нужно добавить информацию: ");
        name = scaner.nextLine();
        System.out.println("Введите известные данные. Если информация отсутствует нажмите ввод.");
        birthDate = inputDate("Введите дату рождения в формате yyyy-mm-dd:");
        deathDate = inputDate("Введите дату смерти в формате yyyy-mm-dd:");
        father = inputInfo("Введмите имя отца: ");
        mother = inputInfo("Введмите имя матери: ");
        spouse = inputInfo("Введмите имя супруги(а): ");
        System.out.println("Ввести детей? Да - нажмите 1, Нет - нажмите 2");
        String input = scaner.nextLine();
        while (Objects.equals(input, "1")) {
            if (Objects.equals(input, "1")) {
                System.out.println("Введите 1 если пол ребенка М и 2, если пол ребенка Ж");
                if (scaner.nextLine().equals("1")) {
                    kidName = "1";
                }
                else {
                    kidName = "2";
                }
                System.out.println("Введмите имя ребенка: ");
                kidName += scaner.nextLine();
                children.add(kidName);
                System.out.println("Ввести еще детей? Да - нажмите 1, Нет - нажмите 2");
                input = scaner.nextLine();
            }
        }
        presenter.addInformation(name, birthDate, deathDate, father, mother,
                spouse, children);
    }

    public void saveToFileFamilyTree() {
        boolean rezult;
        System.out.println("Введите имя файла в который нужно записать проект:");
        String fileName = scaner.nextLine();
        rezult = presenter.saveToFileFamilyTree(fileName);
        if (rezult) {
            System.out.println("Записано в файл успешно");
        }
        else {
            System.out.println("Ошибка записи");
        }
    }

    public void loadFromFileFamilyTree() {
        System.out.println("Введите имя файла из которого нужно загрузить проект:");
        String fileName = scaner.nextLine();
        presenter.loadFromFileFamilyTree(fileName);
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
