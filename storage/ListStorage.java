package storage;

import model.Transport;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ListStorage extends Storage {
    private List<Transport> list = new ArrayList<>();

    @Override
    public void add(Transport transport) {
        list.add(transport);
    }

    @Override
    public void update(Transport transport) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == transport.getId()) {
                list.set(i, transport);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        list.removeIf(t -> t.getId() == id);
    }

    @Override
    public void printAll() {
        Iterator<Transport> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Override
    public List<Transport> getAllAsList() {
        return list;
    }

    @Override
    public void sort(Comparator<Transport> comparator) {
        list.sort(comparator);
    }
}
