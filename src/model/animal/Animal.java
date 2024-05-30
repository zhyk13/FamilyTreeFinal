package model.animal;

import model.familyTree.FamilyTreeItem;
import model.human.Gender;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Animal implements Serializable, Comparable<Animal>, FamilyTreeItem<Animal> {
    private Integer id;
    private String name;
    private LocalDate birthDate, deathDate;
    private List<Animal> children = new ArrayList<>();
    private Animal father, mother;
    private Gender gender;
    private String breed;

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

    public void setChildren(Animal kid){
        this.children.add(kid);
    }

    public void setFather(Animal father){
        this.father = father;
    }

    public void setMother(Animal mother){
        this.mother = mother;
    }

    public void setGender(Gender gender){
        this.gender = gender;
    }

    public void setBreed(String breed){
        this.breed = breed;
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

    public Animal getFather(){
        return this.father;
    }

    public Animal getMother(){
        return this.mother;
    }

    public Gender getGender(){
        return this.gender;
    }

    @Override
    public Animal getSpouse() {
        return null;
    }

    @Override
    public void setSpouse(Animal animal) {

    }

    public String getBreed(){
        return this.breed;
    }

    public Integer getAge(){
        if (this.deathDate == null){
            return getPeriod(birthDate, LocalDate.now());
        }
        else{
            return getPeriod(birthDate, deathDate);
        }
    }

    public Integer getPeriod(LocalDate birthDate, LocalDate deathDate){
        Period age = Period.between(birthDate, deathDate);
        return age.getYears();
    }

    public List<Animal> getChildren(){
        return new ArrayList<>(children);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Имя: "+ this.name + "\n" + "Дата рождения: " + this.birthDate + "\n");
        stringBuilder.append("Дата смерти: " + this.deathDate + "\n");
        stringBuilder.append("Возраст: " + this.getAge() + " лет \n");
        stringBuilder.append("Порода: " + this.getBreed() + "\n");
        if (this.father != null){
            stringBuilder.append("Отец: " + this.father.name + "\n");
        }
        if (this.mother != null){
            stringBuilder.append("Мать: " + mother.name + "\n");
        }
        for (Animal tmp: children) {
            stringBuilder.append("Дети: " + tmp.name + "\n");
        }
        return  stringBuilder.toString();
    }

    @Override
    public int compareTo(Animal o) {
        return this.name.compareTo(o.name);
    }

}
