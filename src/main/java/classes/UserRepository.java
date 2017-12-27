package classes;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository extends InMemoryRepository<User> {

    static private User juha = new User()
            .setName("Juha Christensen")
            .setAge(38)
            .setComplete(true);

    static private User mike = new User()
            .setName("Michael Shraybman")
            .setAge(35)
            .setComplete(true);

    public List<User> users = Arrays.asList(juha, mike);

    public void initUsers() {
        this.setElements(users);
    }

    protected void updateIfExist(User original, User updated) {
        original.setName(updated.getName());
        original.setAge(updated.getAge());
        original.setComplete(updated.isComplete());
    }
}
