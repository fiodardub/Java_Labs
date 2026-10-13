package storage;

import model.Transport;
import java.util.*;

public class MapStorage extends Storage {
    private Map<Integer, Transport> map = new TreeMap<>();

    @Override
    public void add(Transport transport) {
        map.put(transport.getId(), transport);
    }

    @Override
    public void update(Transport transport) {
        if (map.containsKey(transport.getId())) {
            map.put(transport.getId(), transport);
        }
    }

    @Override
    public void delete(int id) {
        map.remove(id);
    }

    @Override
    public void printAll() {
        // Использование итератора по значениям Map (п. 10)
        Iterator<Transport> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Override
    public List<Transport> getAllAsList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void sort(Comparator<Transport> comparator) {
        List<Transport> sortedList = new ArrayList<>(map.values());
        sortedList.sort(comparator);
        map.clear();
        for (Transport t : sortedList) {
            map.put(t.getId(), t);
        }
    }
}
