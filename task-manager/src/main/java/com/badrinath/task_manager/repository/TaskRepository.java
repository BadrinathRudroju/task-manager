package com.badrinath.task_manager.repository;

import com.badrianath.task_manager.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> findByCompleted(boolean completed);

    List<Task> findByTitleIgnoreCase(String title);

    @Query("SELECT t FROM Task t WHERE t.completed= :completed")
    List<Task> findTasksByCompletedStatus(@Param("completed") boolean completed);

}
