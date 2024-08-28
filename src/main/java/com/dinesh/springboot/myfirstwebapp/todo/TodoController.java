package com.dinesh.springboot.myfirstwebapp.todo;

import com.dinesh.springboot.myfirstwebapp.login.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@SessionAttributes("user")
@Controller
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping("/list-todo")
    public String listAllTodos(Model model) {
        String UserName = getUserName(model);
        System.out.println("User Name is " + UserName);
        model.addAttribute("alltodos", todoService.findByUsername(UserName));
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
        todoService.addTodo(todo1);
        return "redirect:list-todo";
    }

    @GetMapping("/deleteto")
    public String deleteTodo(@RequestParam int id) {
        todoService.delete(id);
        return "redirect:list-todo";
    }

    @GetMapping("/updatetodo")
    public String showToDoPage(@RequestParam int id, Model model) {
        Todo todo = todoService.findById(id);
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
