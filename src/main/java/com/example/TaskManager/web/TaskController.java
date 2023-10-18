package com.example.TaskManager.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.TaskManager.model.PriorityRepository;
import com.example.TaskManager.model.StatusRepository;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.model.TaskRepository;

@Controller
public class TaskController {

	/* >>> Link with repositories: <<< */
	@Autowired
	private TaskRepository repository;

	@Autowired
	private PriorityRepository prepository;

	@Autowired
	private StatusRepository srepository;

	// ->>>>>>>>>>>>>>>>>>>ADD: Date repository??!! <<<<<<<-

	/* >>> Handle end points: <<< */

	/// Show Login Page ///
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	/// List All Tasks ///
	// THYMELEAF:
	@RequestMapping(value = { "/", "/tasklist" })
	public String listAllTasksThymeleaf(Model model) {
		model.addAttribute("tasks", repository.findAll());
		return "tasklist";
	}

	// RESTFUL:
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public @ResponseBody List<Task> listAllTasksRest() {
		return (List<Task>) repository.findAll();
	}

	/// Get 1 task by ID ///
	// RESTFUL:
	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Task> findTaskRest(@PathVariable("id") Long taskId) {
		return repository.findById(taskId);
	}

	/// Modify Tasks ///
	// THYMELEAF:
	// ADD new tasks - display add form:
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
	public String addTasksForm(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("priorities", prepository.findAll());
		model.addAttribute("statuses", srepository.findAll());
		return "addtask";
	}

	// SAVE new task - save functionality for add form (end point where the form(s)
	// will be submitted):
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveNewTask(Task task) {
		repository.save(task);
		return "redirect:tasklist";
	}

	// DELETE existing task - button functionality on front page:
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTask(@PathVariable("id") Long taskId, Model model) {
		repository.deleteById(taskId);
		return "redirect:../booklist";
	}

	// EDIT by ADMIN - form to edit/update tasks, all fields:
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editFormAdmin(@PathVariable("id") Long taskId, Model model) {
		model.addAttribute("task", repository.findById(taskId));
		model.addAttribute("priorities", prepository.findAll());
		model.addAttribute("statuses", srepository.findAll());
		return "edittask";
	}

	// EDIT by USER - form to edit/update only the status field:
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/editStatus/{id}", method = RequestMethod.GET)
	public String editFormUser(@PathVariable("id") Long taskId, Model model) {
		//model.addAttribute("task", repository.findById(taskId));
		//model.addAttribute("priorities", prepository.findAll());
		model.addAttribute("statuses", srepository.findAll());
		return "editstatus";
	}

}
