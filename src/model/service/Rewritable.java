package model.service;

import java.io.Serializable;

public interface Rewritable {
    boolean saveToFile(Serializable serializable, String filename);
    Object loadFromFile(String filename);
}
