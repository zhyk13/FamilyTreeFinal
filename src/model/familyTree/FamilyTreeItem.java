package model.familyTree;
import model.human.Gender;
import java.time.LocalDate;
import java.util.List;

public interface FamilyTreeItem<T> extends Comparable<T>{
    String toString();
    Integer getId();
    void setId(Integer id);
    T getFather();
    T getMother();
    List<T>getChildren();
    void setChildren(T t);
    Gender getGender();
    T getSpouse();
    void setSpouse(T t);
    String getName();
    void setFather(T t);
    void setMother(T t);
    LocalDate getDeathDate();
    Integer getAge();
    LocalDate getBirthDate();

}
