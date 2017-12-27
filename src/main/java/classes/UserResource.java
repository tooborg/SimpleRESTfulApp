package classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

    private final long id;
    private final String name;
    private final long age;
    private final boolean isComplete;

    public UserResource(User user) {
        id = user.getId();
        name = user.getName();
        age = user.getAge();
        isComplete = user.isComplete();
    }

    @JsonProperty("id")
    public Long getResourceId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getAge() {
        return age;
    }

    @JsonProperty("cognianceEmployee")
    public boolean isComplete() {
        return isComplete;
    }
}
