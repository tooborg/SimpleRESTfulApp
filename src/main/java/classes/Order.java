package classes;

public class Order implements Identifiable {

    private Long id;
    private String description;
    private long costInCents;
    private boolean isComplete;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCostInCents(long costInCents) {
        this.costInCents = costInCents;
    }

    public long getCostInCents() {
        return costInCents;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
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
