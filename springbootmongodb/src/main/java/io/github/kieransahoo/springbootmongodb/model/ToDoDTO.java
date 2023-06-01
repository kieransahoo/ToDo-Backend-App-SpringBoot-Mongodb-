package io.github.kieransahoo.springbootmongodb.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "todos")
public class ToDoDTO {

    @Id
    private String id;
    @NotNull(message = "todo cannot be null!")
    private String todo;
    @NotNull(message = "description cannot be null!")
    private String description;
    @NotNull(message = "completed status cannot be null!")
    private Boolean completed;
    private LocalDate createAt;
    private LocalDate updatedAt;
}
