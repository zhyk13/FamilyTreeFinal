package model.human;
import model.familyTree.FamilyTreeItem;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Human implements Serializable, Comparable<Human>, FamilyTreeItem<Human> {
    private Integer id;
    private String name;
    private LocalDate birthDate, deathDate;
    private List<Human> children = new ArrayList<>();
    private Human father, mother;
    private Gender gender;
    private Human spouse;

    public Human(Integer id, String name, LocalDate birthDate, LocalDate deathDate, List<Human> children, Human father,
                 Human mother, Gender gender, Human spouse) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.children = children;
        this.father = father;
        this.mother = mother;
        this.gender = gender;
        this.spouse = spouse;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate, LocalDate deathDate){
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public void setChildren(Human kid){
        this.children.add(kid);
    }

    public void setFather(Human father){
        this.father = father;
    }

    public void setMother(Human mother){
        this.mother = mother;
    }

    public void setGender(Gender gender){
        this.gender = gender;
    }

    public void setSpouse(Human spouse){
        this.spouse = spouse;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public LocalDate getBirthDate(){
        return this.birthDate;
    }

    public LocalDate getDeathDate(){
        return this.deathDate;
    }

    public Human getFather(){
        return this.father;
    }

    public Human getMother(){
        return this.mother;
    }

    public Human getSpouse(){
        return this.spouse;
    }

    public Gender getGender(){
        return this.gender;
    }

    public Integer getAge(){
        if ((this.deathDate == null) && (this.birthDate != null)) {
            return getPeriod(birthDate, LocalDate.now());
        }
        else{
            if ((this.deathDate != null) && (this.birthDate != null)) {
                return getPeriod(birthDate, deathDate);
            }
            else {
                return null;
            }
        }
    }

    public Integer getPeriod(LocalDate birthDate, LocalDate deathDate){
        Period age = Period.between(birthDate, deathDate);
        return age.getYears();
    }

    public List<Human> getChildren(){
        return new ArrayList<>(children);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Имя: "+ this.name + "\n" + "Дата рождения: " + this.birthDate + "\n");
        stringBuilder.append("Дата смерти: " + this.deathDate + "\n");
        stringBuilder.append("Возраст: " + this.getAge() + " лет \n");
        if (this.father != null){
            stringBuilder.append("Отец: " + this.father.name + "\n");
        }
        if (this.mother != null){
            stringBuilder.append("Мать: " + mother.name + "\n");
        }
        if (this.spouse != null){
            stringBuilder.append("Супруг(а): " + spouse.name + "\n");
        }
        for (Human tmp: children) {
            stringBuilder.append("Дети: " + tmp.name + "\n");
        }
        return  stringBuilder.toString();
    }

//    public void saveToFile(String filename) throws IOException, ClassNotFoundException {
//        ObjectOutputStream outputStrim = new ObjectOutputStream(new FileOutputStream(filename));
//        outputStrim.writeObject(this);
//    }
//
//    public Human loadFromFile(String filename) throws IOException, ClassNotFoundException{
//        ObjectInputStream inputStrim = new ObjectInputStream(new FileInputStream(filename));
//        return (Human) inputStrim.readObject();
//    }

    @Override
    public int compareTo(Human o) {
        return this.name.compareTo(o.name);
    }
}
