package com.example.TaskManager.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.TaskManager.model.PriorityRepository;
import com.example.TaskManager.model.StatusRepository;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.model.TaskDate;
import com.example.TaskManager.model.TaskDateRepository;
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

	@Autowired
	private TaskDateRepository drepository;

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

		/// Change above into this:///
		// List<Task> tasks =
		/// repository.findByOrderByTaskDateLocalDateAscPriorityValueAsc();
		// model.addAttribute("tasks", tasks);
		
		
		List<Task> tasks = repository.findByOrderByTaskDateDeadlineAscTaskPriorityPriorityValueAsc();
		model.addAttribute("tasks", tasks);

		return "tasklist";
	}

	// RESTFUL:
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public @ResponseBody List<Task> listAllTasksRest() {
		return (List<Task>) repository.findAll();
		/// Change above into this:////
		// return
		/// (List<Task>)repository.findByOrderByTaskDateLocalDateAscPriorityValueAsc();
		// or
		// return repository.findByOrderByTaskDateLocalDateAscPriorityValueAsc();
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
		
		// model.addAttribute("deadline", LocalDate.now());
		// model.addAttribute("deadlines", drepository.findAll());
		// model.addAttribute("deadline", new TaskDate());
		// model.addAttribute("deadlines", drepository.findAll());
		
		model.addAttribute("priorities", prepository.findAll());
		model.addAttribute("statuses", srepository.findAll());
		return "addtask";
	}

	
	// SAVE new or edited task
	// save functionality for add form (end point where the form(s) will be
	// submitted):
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveNewTask(Task task, @RequestParam("deadline") String deadline) {
		
		TaskDate taskDate = new TaskDate(deadline); // Create a new TaskDate based on the deadline value
		drepository.save(taskDate);  // Save task date
		task.setTaskDate(taskDate);  // Set the associated TaskDate in the Task
	    
		repository.save(task);
		return "redirect:tasklist";
	}
	
	//oldSave:
	/*
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveNewTask(Task task) {
		repository.save(task);
		return "redirect:tasklist";
	}
	/*

	// Test
	// SAVE new status only
	// USER role
	/*
	@RequestMapping(value = "/saveStatus", method = RequestMethod.POST)
	public String saveUserStatusEdit(Task task) {
		repository.save(task);
		return "redirect:tasklist";
	}
	*/

	// DELETE existing task
	// button functionality on front page:
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTask(@PathVariable("id") Long taskId, Model model) {
		repository.deleteById(taskId);
		return "redirect:../tasklist";
	}

	
	// EDIT by ADMIN
	// form to edit/update tasks, all fields:
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editFormAdmin(@PathVariable("id") Long taskId, Model model) {
		model.addAttribute("task", repository.findById(taskId));
		
		//model.addAttribute("deadlines", drepository.findAll());
		//model.addAttribute("deadlines", drepository.findAll());
		//model.addAttribute("deadline", drepository.findById(Long taskDateId));
		//Optional<TaskDate> findById (Long taskDateId);
		
		model.addAttribute("priorities", prepository.findAll());
		model.addAttribute("statuses", srepository.findAll());
		
		//Get the task-associated date:
		Optional<Task> taskOptional = repository.findById(taskId);
		Task taskById = taskOptional.get();
		TaskDate taskDate = taskById.getTaskDate();
		
		model.addAttribute("deadline", taskDate.getDeadline());
		
		
		return "edittask";
	}

	// EDIT by USER
	// form to edit/update only the status field:
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/editStatus/{id}", method = RequestMethod.GET)
	public String editStatusFormUser(@PathVariable("id") Long taskId, Model model) {
		model.addAttribute("task", repository.findById(taskId));
		
		//model.addAttribute("deadlines", drepository.findAll());
		
		//model.addAttribute("deadline", drepository.findById(taskDateId));	
		//model.addAttribute("deadlines", drepository.findAll());
		
		model.addAttribute("priorities", prepository.findAll()); 
		model.addAttribute("statuses", srepository.findAll());
		
		model.addAttribute("priorities", prepository.findAll());
		model.addAttribute("statuses", srepository.findAll());
		
		//Get the task-associated date:
		Optional<Task> taskOptional = repository.findById(taskId);
		Task taskById = taskOptional.get();
		TaskDate taskDate = taskById.getTaskDate();
		
		model.addAttribute("deadline", taskDate.getDeadline());
		
		return "editstatus";
	}

}
