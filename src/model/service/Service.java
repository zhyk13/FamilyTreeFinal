package model.service;

import model.familyTree.FamilyTree;
import model.human.Gender;
import model.human.Human;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Service {
    private Integer humansId = 1;
    private FamilyTree<Human> familyTree;

    public Service() {
        familyTree = new FamilyTree<>();
    }

    public Human addHuman(String name, LocalDate birthDate, LocalDate deathDate, List<String> children,
                          String fatherName, String motherName, Gender gender, String spouseName) {
        Human father = new Human(null, fatherName, null, null, new ArrayList<>(), null,
                null, null, null);
        Human mother = new Human(null, motherName, null, null, new ArrayList<>(), null,
                null, null, null);
        Human spouse = new Human(null, spouseName, null, null, new ArrayList<>(), null,
                null, null, null);
        List<Human> kids = new ArrayList<>();
        for (String kidName: children) {
            Human kid = new Human(null, kidName, null, null, null, null, null,
                    null, null);
            kids.add(kid);
        }
        Human human = new Human(humansId++, name, birthDate, deathDate, kids, father, mother, gender, spouse);
        familyTree.setItem(human);
        return human;
    }

    public String getHumanList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Список членов дерева:\n");
        for (Human human: familyTree){
            stringBuilder.append(human.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getFamilytreeInfo() {
        return familyTree.toString();
    }

    public boolean saveToFileFamilyTree(String fileName) {
        FileHandler fileHandler = new FileHandler();
        return fileHandler.saveToFile(familyTree, fileName);
    }

    public void loadFromFileFamilyTree(String fileName) {
        FileHandler fileHandler = new FileHandler();
        familyTree = fileHandler.loadFromFile(fileName);
    }
}

