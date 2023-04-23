package com.rean.service;

import com.rean.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto createOrUpdate(TodoDto todoDto);
    void delete(Long id);
    TodoDto findById(Long id);
    List<TodoDto> findAll();
}
