package classes;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public abstract class InMemoryRepository<T extends Identifiable> {

    static private User juha = new User()
            .setName("Juha Christensen")
            .setAge(38)
            .setComplete(true);

    static private User mike = new User()
            .setName("Michael Shraybman")
            .setAge(35)
            .setComplete(true);

    @Autowired
    private IdGenerator idGenerator;

    private List<T> elements = Collections.synchronizedList(new ArrayList<T>());

    public T create(T element) {
        elements.add(element);
        element.setId(idGenerator.getNextId());
        return element;
    }

    public boolean delete(final Long id) {
        return elements.removeIf(element -> element.getId().equals(id));
    }

    public List<T> findAll() {
        return elements;
    }

    public Optional<T> findById(Long id) {
        return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public int getCount() {
        return elements.size();
    }

    public void clear() {
        elements.clear();
    }

    public boolean update(Long id, T updated) {
        if (updated == null) {
            return false;
        } else {
            Optional<T> element = findById(id);
            element.ifPresent(original -> updateIfExist(original, updated));
            return element.isPresent();
        }
    }

    protected abstract void updateIfExist(T original, T updated);

    public void setElements(List<T> elements) {
        this.elements = Collections.synchronizedList(elements);
    }
}
