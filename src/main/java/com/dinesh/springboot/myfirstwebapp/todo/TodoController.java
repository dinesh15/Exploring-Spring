package com.dinesh.springboot.myfirstwebapp.todo;

import com.dinesh.springboot.myfirstwebapp.login.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping("/list-todo")
    public String listAllTodos(Model model) {
//        System.out.println(model);
//        model.addAttribute("name", user.getUserName());
//        System.out.println(model);
//        System.out.println(user.getUserName());
        model.addAttribute("alltodos", todoService.findByUsername());
        return "listtodo";
    }

    @GetMapping("/add-todo")
    public String addTodo(Model themodel) {
        System.out.println("i am hre" + themodel);
        Todo todo = new Todo();
        todo.setDescription("This is a new todo");
        themodel.addAttribute("todo", todo);
        return "/todo";
    }

    @PostMapping("/add-todo")
    public String addNewTodo(@Valid Todo todo1, BindingResult result, @SessionAttribute("user") User user) {
        if(result.hasErrors()){
            return "/todo";
        }
        System.out.println(todo1);
        todoService.addTodo(todo1);
        return "redirect:list-todo";
    }

    @GetMapping("/deleteto")
    public String deleteTodo(@RequestParam int id) {
//        System.out.println("i am in deleteTodo mapping");
//        System.out.println(id);
//        System.out.println("i am in deleteTodo mapping");
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
