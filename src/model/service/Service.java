package model.service;

import model.familyTree.FamilyTree;
import model.human.Gender;
import model.human.Human;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private FamilyTree<Human> familyTree;
    private Rewritable fileHandler;

    public Service(Rewritable fileHandler) {
        this.familyTree = new FamilyTree<>();
        this.fileHandler = fileHandler;
    }

    public Human addHuman(String name, LocalDate birthDate, LocalDate deathDate, List<String> children,
                          String fatherName, String motherName, Gender gender, String spouseName) {
        Human father = null;
        Human mother = null;
        Human spouse = null;
        List<Human> kids = null;
        if (fatherName != null) {
            father = new Human(null, fatherName, null, null, new ArrayList<>(), null,
                    null, Gender.Male, null);
        }
        if (motherName != null) {
            mother = new Human(null, motherName, null, null, new ArrayList<>(), null,
                    null, Gender.Female, null);
        }
        if (spouseName != null) {
            spouse = new Human(null, spouseName, null, null, new ArrayList<>(), null,
                    null, null, null);
            if (gender == Gender.Male) {
                spouse.setGender(Gender.Female);
            }
            else {
                spouse.setGender(Gender.Male);
            }
        }
        if (!children.isEmpty()) {
            kids = new ArrayList<>();
            for (String kidName: children) {
                Human kid = new Human(null, kidName, null, null, null, null,
                        null, null, null);
                if (kidName.charAt(0) == '1') {
                    kid.setGender(Gender.Male);
                }
                else {
                    kid.setGender(Gender.Female);
                }
                kid.setName(kidName.substring(1));
                kids.add(kid);
            }
        }
        Human human = new Human(null, name, birthDate, deathDate, kids, father, mother, gender, spouse);
        familyTree.setItem(human);
        return human;
    }

    public void addInformation(String name, LocalDate birthDate, LocalDate deathDate, String fatherName,
                               String motherName, String spouseName, List<String> kidsNames) {
        Human father = null;
        Human mother = null;
        Human spouse = null;
        char genderChar;
        Human human = familyTree.getHumanByName(name);
        if (birthDate != null) {
            human.setBirthDate(birthDate);
        }
        if (deathDate != null) {
            human.setDeathDate(deathDate);
        }
        if (fatherName != null) {
            if (familyTree.containsName(fatherName)) {
                human.setFather(familyTree.getHumanByName(fatherName));
                familyTree.getHumanByName(motherName).setChildren(human);
            }
            else {
                father = new Human(null, fatherName, null, null, new ArrayList<>(), null,
                        null, Gender.Male, null);
                human.setFather(father);
                father.setChildren(human);
                familyTree.setItem(father);
            }
        }
        if (motherName != null) {
            if (familyTree.containsName(motherName)) {
                human.setMother(familyTree.getHumanByName(fatherName));
                familyTree.getHumanByName(fatherName).setChildren(human);
            } else {
                mother = new Human(null, motherName, null, null, new ArrayList<>(), null,
                        null, Gender.Female, null);
                human.setMother(mother);
                mother.setChildren(human);
                familyTree.setItem(mother);
            }
        }
        if (spouseName != null) {
            if (familyTree.containsName(spouseName)) {
                human.setSpouse(familyTree.getHumanByName(spouseName));
                familyTree.getHumanByName(spouseName).setSpouse(human);
            }
            else {
                spouse = new Human(null, spouseName, null, null, new ArrayList<>(), null,
                        null, null, null);
                if (human.getGender() == Gender.Male) {
                    spouse.setGender(Gender.Female);
                }
                else {
                    spouse.setGender(Gender.Male);
                }
                human.setSpouse(spouse);
                familyTree.setItem(spouse);
            }
        }
        if (!kidsNames.isEmpty()) {
            for (String kidName: kidsNames) {
                genderChar = kidName.charAt(0);
                kidName = kidName.substring(1);
                if (familyTree.containsName(kidName)) {
                    human.setChildren(familyTree.getHumanByName(kidName));
                    if (human.getGender() == Gender.Male) {
                        familyTree.getHumanByName(kidName).setFather(human);
                    }
                    else {
                        familyTree.getHumanByName(kidName).setMother(human);
                    }
                }
                else {
                    Human kid = new Human(null, kidName, null, null, null, null,
                            null, null, null);
                    if (genderChar == '1') {
                        kid.setGender(Gender.Male);
                    }
                    else {
                        kid.setGender(Gender.Female);
                    }
                    familyTree.setItem(kid);
                }
            }
        }
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
        return fileHandler.saveToFile(familyTree, fileName);
    }

    public void loadFromFileFamilyTree(String fileName) {
        familyTree = (FamilyTree<Human>) fileHandler.loadFromFile(fileName);
    }
}

