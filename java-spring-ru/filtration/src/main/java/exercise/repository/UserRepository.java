package exercise.repository;

import exercise.model.QUser;
import exercise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
// Зависимости для дополнительного задания
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import org.springframework.stereotype.Repository;

@Repository
// Репозиторий для основной задачи
public interface UserRepository extends JpaRepository<User, Long>,
        QuerydslPredicateExecutor<User>,
        QuerydslBinderCustomizer<QUser> {


    // Дополнительная задача
// Если решите сделать дополнительную задачу, измените существующий репозиторий для работы с Querydsl предикатами
// BEGIN
    @Override
    default void customize(QuerydslBindings bindings, QUser user) {
        bindings.bind(user.firstName)
                .first(StringExpression::containsIgnoreCase);
        bindings.bind(user.lastName)
                .first(StringExpression::containsIgnoreCase);
        bindings.bind(user.email)
                .first(StringExpression::containsIgnoreCase);
        bindings.bind(user.profession)
                .first(StringExpression::containsIgnoreCase);
        bindings.bind(user.gender)
                .first(SimpleExpression::eq);
    }
// END
}