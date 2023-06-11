package exercise.controller;
import exercise.model.QUser;
import exercise.model.User;
import exercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// Зависимости для самостоятельной работы
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    // BEGIN
    @GetMapping
    public Iterable<User> getUsers(@RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName) {
        if (firstName != null && lastName != null) {
            return userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName)
                    .and(QUser.user.lastName.containsIgnoreCase(lastName)));
        } else if (firstName != null) {
            return userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName));
        } else if (lastName != null) {
            return userRepository.findAll(QUser.user.lastName.containsIgnoreCase(lastName));
        }
        return userRepository.findAll();
    }

    @GetMapping("/dt")
    public Iterable<User> getUsersDT(@QuerydslPredicate(root = User.class) Predicate predicate) {
        return userRepository.findAll(predicate);
    }
    // END
}

