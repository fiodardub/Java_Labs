package storage;

import model.Transport;
import java.util.Comparator;
import java.util.List;

public abstract class Storage {
    public abstract void add(Transport transport);
    public abstract void update(Transport transport);
    public abstract void delete(int id);
    public abstract void printAll();
    public abstract List<Transport> getAllAsList();
    public abstract void sort(Comparator<Transport> comparator);
}
