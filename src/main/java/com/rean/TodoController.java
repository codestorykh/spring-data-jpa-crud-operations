package com.rean;

import com.rean.dto.TodoDto;
import com.rean.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/todos")
@Slf4j
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody TodoDto todoDto) {
        log.info("Request create new todo {}", todoDto);
        return new ResponseEntity<>(todoService.createOrUpdate(todoDto), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody TodoDto todoDto) {
        log.info("Request update todo {}", todoDto);
        return new ResponseEntity<>(todoService.createOrUpdate(todoDto), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        log.info("Request check todo by id  {}", id);
        return ResponseEntity.ok(todoService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        log.info("Request get all todo");
        return ResponseEntity.ok(todoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
