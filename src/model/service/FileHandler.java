package model.service;

import model.familyTree.FamilyTree;
import java.io.*;

public class FileHandler implements Rewritable {

    @Override
    public boolean saveToFile(Serializable tree, String filename) {
        try (ObjectOutputStream outputStrim = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStrim.writeObject(tree);
            return true;
        }
        catch (Exception message) {
            message.printStackTrace();
            return false;
        }
    }

    @Override
    public FamilyTree loadFromFile(String filename){
        try(ObjectInputStream inputStrim = new ObjectInputStream(new FileInputStream(filename))) {
            return (FamilyTree) inputStrim.readObject();
        }
        catch (Exception message) {
            message.printStackTrace();
            return null;
        }
    }
}
