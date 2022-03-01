package Todo.Manager;

import Todo.Model.Epic;

import Todo.Model.Subtask;
import Todo.Model.Task;

import java.util.*;

public interface TaskManager {

    long getNewId();

    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubtask(Subtask subtask);

    Collection<Task> getAllTasks();

    Collection<Epic> getAllEpics();

    Collection<Subtask> getAllSubtasks();

    Set<Long> getAllTasksKey();

    Set<Long> getAllEpicsKey();

    Set<Long> getAllSubtasksKey();

    Task getTaskById(long taskId);

    Epic getEpicById(long epicId);

    Subtask getSubtaskById(long subtaskId);

    List<Subtask> getSubtasksByEpicId(long epicId);

    void updateTaskById(Task task, long taskId);

    void updateEpicById(Epic epic, long epicId);

    void updateSubtaskById(Subtask subtask, Epic epic);

    void changeEpicStatus(Epic epic);

    void deleteAll();

    void deleteTaskId(long numbTaskId);

    void deleteEpicId(long numbEpicId);

    void deleteSubtaskId(Epic epic, long numbSubtaskId);

    List<Task> history();
}


