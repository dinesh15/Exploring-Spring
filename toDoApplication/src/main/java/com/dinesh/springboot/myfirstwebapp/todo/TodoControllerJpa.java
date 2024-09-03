package com.dinesh.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("user")
@Controller
public class TodoControllerJpa {

    private TodoService todoService;
    private TodoRepository todoRepository;

    public TodoControllerJpa(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }


    @GetMapping("/list-todo")
    public String listAllTodos(Model model) {
        String UserName = getUserName(model);
        System.out.println("User Name is " + UserName);
        model.addAttribute("alltodos", todoRepository.findByuserName(UserName));
        return "listtodo";
    }

    public String getUserName(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/add-todo")
    public String addTodo(Model themodel) {
        System.out.println("i am hre" + themodel);
        String UserName = getUserName(themodel);
        Todo todo = new Todo();
        System.out.println("User Name: " + UserName);
        todo.setDescription("This is a new todo");
        todo.setUserName(UserName);
        themodel.addAttribute("todo", todo);
        return "/todo";
    }

    @PostMapping("/add-todo")
    public String addNewTodo(@Valid Todo todo1, BindingResult result) {
        if(result.hasErrors()){
            return "/todo";
        }
        System.out.println(todo1);
//        todoService.addTodo(todo1);
        todoRepository.save(todo1);
        return "redirect:list-todo";
    }

    @GetMapping("/deleteto")
    public String deleteTodo(@RequestParam int id) {
//        todoService.delete(id);
        todoRepository.deleteById(id);
        return "redirect:list-todo";
    }

    @GetMapping("/updatetodo")
    public String showToDoPage(@RequestParam int id, Model model) {
        Todo todo = todoRepository.findById(id).get();
        System.out.println(todo);
        model.addAttribute("todo", todo);
        return "/updatetodopage";
    }



    @PostMapping("/updatetodo")
    public String updateTodo(@Valid Todo todo, BindingResult result) {
        if(result.hasErrors()){
            return "/updatetodopage";
        }
        todoService.updateTodo(todo);
        return "redirect:list-todo";
    }
}
