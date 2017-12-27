package classes;

public class User implements Identifiable {

    private Long id;
    private String name;
    private long age;
    private boolean isComplete;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setAge(long age) {
        this.age = age;
        return this;
    }

    public long getAge() {
        return age;
    }

    public User setComplete(boolean isComplete) {
        this.isComplete = isComplete;
        return this;
    }

    public void markComplete() {
        setComplete(true);
    }

    public void markIncomplete() {
        setComplete(false);
    }

    public boolean isComplete() {
        return isComplete;
    }
}
