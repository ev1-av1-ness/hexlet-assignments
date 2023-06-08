package exercise.dto;

import exercise.model.Category;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
public class ArticleDto {

    long id;
    String name;
    String body;
    Category category;
}
// END
