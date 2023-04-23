package com.rean.service.impl;

import com.rean.dto.TodoDto;
import com.rean.model.Todo;
import com.rean.repository.TodoRepository;
import com.rean.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDto createOrUpdate(TodoDto todoDto) {
        if(todoDto.getId() != null && todoDto.getId() > 0) {
            Optional<Todo> todo = todoRepository.findById(todoDto.getId());
            if(todo.isPresent()) {
                todo.get().setTitle(todoDto.getTitle());
                todo.get().setDescription(todoDto.getDescription());
                todoRepository.save(todo.get());
                return this.todoMapper(todo.get());
            }
        }
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCreatedAt(LocalDateTime.now());
        todoRepository.saveAndFlush(todo);
        return this.todoMapper(todo);
    }

    @Override
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto findById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.map(this::todoMapper).orElse(null);
    }

    @Override
    public List<TodoDto> findAll() {
        List<Todo> todos = todoRepository.findAll();
        if(todos.isEmpty()){
            return new ArrayList<>();
        }
        List<TodoDto> todoDtos = new ArrayList<>();
        for(Todo todo : todos) {
            todoDtos.add(this.todoMapper(todo));
        }
        return todoDtos;
    }

    protected TodoDto todoMapper(Todo todo) {
        return new TodoDto(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getCreatedAt());
    }

}
