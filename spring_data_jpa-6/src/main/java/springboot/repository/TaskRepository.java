package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByTitleContainingIgnoreCase(String keyword);

}
