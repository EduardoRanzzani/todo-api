package io.github.eduardoranzzani.todo.repository;

import io.github.eduardoranzzani.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
