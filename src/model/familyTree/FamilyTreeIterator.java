package model.familyTree;
import java.util.Iterator;
import java.util.List;

public class FamilyTreeIterator<T> implements Iterator<T> {

    private int currentId = 0;
    private List<T> familyTree;
    public FamilyTreeIterator(List<T> familyTree) {
        this.familyTree = familyTree;
    }


    @Override
    public boolean hasNext() {
        return familyTree.size() > currentId;
    }

    @Override
    public T next() {
        return familyTree.get(currentId++);
    }
}
