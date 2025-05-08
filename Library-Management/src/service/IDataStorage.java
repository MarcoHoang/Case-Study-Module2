package service;

import java.util.List;

public interface IDataStorage<T> {
    List<T> readFromFile(String path);
    void writeToFile(String path, List<T> data);
}
