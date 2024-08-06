package com.dinesh.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int count = 0;

    static{
        todos.add(new Todo(++count, "Dinesh", "complete spring", LocalDate.now(), false));
        todos.add(new Todo(++count, "Dinesh", "complete aws", LocalDate.now().plusDays(4), false));
        todos.add(new Todo(++count, "Dinesh", "complete spring", LocalDate.now().plusMonths(1), false));
    }

    public List<Todo> findByUsername() {
        return todos;
    }

    public void addTodo(Todo newTodo) {
//        newTodo.setUsername(username);
        newTodo.setId(++count);
        System.out.println("in the service layer"+newTodo );
        todos.add(newTodo);
    }

    public void delete(int id) {
        Predicate<Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().orElse(null);
    }

    public void updateTodo(Todo todo) {
        delete(todo.getId());
        todos.add(todo);
    }
}