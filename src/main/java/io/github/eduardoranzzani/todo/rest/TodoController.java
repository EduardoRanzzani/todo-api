package io.github.eduardoranzzani.todo.rest;

import io.github.eduardoranzzani.todo.model.Todo;
import io.github.eduardoranzzani.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo salvar(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo getById(@PathVariable("id") Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
    }

    @PatchMapping("{id}/done")
    public Todo markAsDone(@PathVariable("id") Long id) {
        return todoRepository.findById(id).map(todo -> {
            todo.setDone(true);
            todo.setDoneDate(LocalDateTime.now());
            todoRepository.save(todo);
            return todo;
        }).orElse(null);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Todo update(@PathVariable("id") Long id, @RequestBody Todo todo) {
        todo.setDone(todo.getDone() ? false : true);
        todo.setDoneDate(todo.getDone() ? null : LocalDateTime.now());
        return todoRepository.save(todo);
    }

}
