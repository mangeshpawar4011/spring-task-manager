package springboot;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import springboot.entity.Task;
import springboot.repository.TaskRepository;

@SpringBootApplication


public class SpringDataJpa6Application {

	public static void main(String[] args)  {
		ConfigurableApplicationContext context =SpringApplication.run(SpringDataJpa6Application.class, args);
		TaskRepository taskrepository=context.getBean(TaskRepository.class);
		
		Scanner sc=new Scanner(System.in);
		System.out.println("if enter data enter-y otherwire enter any ");
		char ch =sc.next().charAt(0);
		sc.nextLine();
		
       
		 if('y'==ch) {
		Task task=new Task();
		System.out.println("Enter title = ");
		String str=sc.nextLine();
		task.setTitle(str);
		
    System.out.println("Enter description = ");
		String str1=sc.nextLine();
		task.setDescription(str1);
		
		taskrepository.save(task);
	}else {
		
		System.out.println("enter id for data retrive =");
		int id=sc.nextInt();
		Optional<Task> optionalTask = taskrepository.findById(id);

		if (optionalTask.isPresent()) {
		    Task task1 = optionalTask.get();
		    System.out.println("Task Title: " + task1.getTitle());
		    System.out.println("Task Description: " + task1.getDescription());
		    System.out.println("Created Date: " + task1.getCreatedDate());
		} else {
		    System.out.println("Task not found!");
		}

	}
	}

}
