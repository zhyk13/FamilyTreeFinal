package model.human;

import java.util.Comparator;

public class HumanComparatorByBirthDate<T extends Human> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return o1.getBirthDate().compareTo(o2.getBirthDate());
    }
}
