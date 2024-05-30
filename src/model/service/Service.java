package model.service;

import model.familyTree.FamilyTree;
import model.human.Gender;
import model.human.Human;

import java.time.LocalDate;
import java.util.List;


public class Service {
    private Integer humansId = 1;
    private FamilyTree<Human> familyTree;

    public Service() {
        familyTree = new FamilyTree<>();
    }

    public void addHuman(String name, LocalDate birthDate, LocalDate deathDate, List<Human> children, Human father,
                         Human mother, Gender gender, Human spouse) {
        Human human = new Human(humansId++, name, birthDate, deathDate, children, father, mother, gender, spouse);
        familyTree.setItem(human);
        System.out.println(human.getName());
    }

    public String getHumanList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Список членов дерева:\n");
        for (Human human: familyTree){
            System.out.println(human.getName());
            stringBuilder.append(human.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void getFamilytreeInfo() {
        System.out.println(familyTree);
    }
}

