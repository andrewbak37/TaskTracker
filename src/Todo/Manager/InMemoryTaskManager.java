package Todo.Manager;

import Todo.Model.Epic;
import Todo.Model.Status;
import Todo.Model.Subtask;
import Todo.Model.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Long, Task> taskMap = new HashMap<>();
    private final Map<Long, Epic> epicMap = new HashMap<>();
    private final Map<Long, Subtask> subTaskMap = new HashMap<>();
    private final List<Task> memory = new ArrayList<>();
    long newId = 0;

    public List<Task> getMemory() {
        return memory;
    }

    @Override
    public long getNewId() {
        return ++newId;
    }

    @Override
    public void addTask(Task task) {
        task.setId(getNewId());
        taskMap.put(task.getId(), task);

    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(getNewId());
        epicMap.put(epic.getId(), epic);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        subtask.setId(getNewId());
        subTaskMap.put(subtask.getId(), subtask);
        epicMap.get(subtask.getIdEpic()).addSubtaskList(subtask);
        changeEpicStatus(getEpicById(subtask.getIdEpic()));
    }

    @Override
    public Collection<Task> getAllTasks() {
        return taskMap.values();
    }

    @Override
    public Collection<Epic> getAllEpics() {
        return epicMap.values();
    }

    @Override
    public Collection<Subtask> getAllSubtasks() {
        return subTaskMap.values();
    }

    @Override
    public Set<Long> getAllTasksKey() {
        return taskMap.keySet();
    }

    @Override
    public Set<Long> getAllEpicsKey() {
        return epicMap.keySet();
    }

    @Override
    public Set<Long> getAllSubtasksKey() {
        return subTaskMap.keySet();

    }

    @Override
    public Task getTaskById(long taskId) {
        if (taskMap.containsKey(taskId)) {
            getHistory(taskMap.get(taskId));
            return taskMap.get(taskId);
        } else {
            System.out.println("Такой задачи нет");
        }
        return null;
    }

    @Override
    public Epic getEpicById(long epicId) {
        if (epicMap.containsKey(epicId)) {
            getHistory(epicMap.get(epicId));
            return epicMap.get(epicId);

        } else {
            System.out.println("Такого Эпика нет");
        }
        return null;
    }

    @Override
    public Subtask getSubtaskById(long subtaskId) {
        if (subTaskMap.containsKey(subtaskId)) {
            getHistory(subTaskMap.get(subtaskId));
            return subTaskMap.get(subtaskId);
        } else {
            System.out.println("Такой подзадачи нет");
        }
        return null;
    }

    @Override
    public List<Subtask> getSubtasksByEpicId(long epicId) {
        if (epicMap.containsKey(epicId)) {
            return epicMap.get(epicId).getSubtasks();
        }
        System.out.println("Такого эпика нет");
        return null;
    }

    @Override
    public void updateTaskById(Task task, long taskId) {
        if (taskMap.containsKey(taskId)) {
            taskMap.put(taskId, task);
        } else {
            System.out.println("Такой таски нет ");
        }
    }

    @Override
    public void updateEpicById(Epic epic, long epicId) {
        if (epicMap.containsKey(epicId)) {
            epicMap.put(epicId, epic);
        } else {
            System.out.println("Такого Эпика нет");
        }
    }

    @Override
    public void updateSubtaskById(Subtask subtask, Epic epic) {
        subTaskMap.put(subtask.getIdEpic(), subtask);
        changeEpicStatus(epic);
    }

    @Override
    public void changeEpicStatus(Epic epic) {
        boolean allNew = true;
        for (Subtask subtask : epic.getSubtasks()) {
            if (!(subtask.getStatus() == Status.NEW)) {
                allNew = false;
                break;
            }
        }
        if (allNew) {
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        for (Subtask subtask : epic.getSubtasks()) {
            if (!(subtask.getStatus() == Status.DONE)) {
                allDone = false;
                break;
            }
        }
        if (allDone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);

        }
    }

    @Override
    public void deleteAll() {
        taskMap.clear();
        epicMap.clear();
        subTaskMap.clear();
    }

    @Override
    public void deleteTaskId(long numbTaskId) {
        taskMap.remove(numbTaskId);

    }

    @Override
    public void deleteEpicId(long numbEpicId) {
        epicMap.remove(numbEpicId);
    }

    @Override
    public void deleteSubtaskId(Epic epic, long numbSubtaskId) {
        subTaskMap.remove(numbSubtaskId);
        changeEpicStatus(epic);
    }

    @Override
    public List<Task> history() {
        return memory;
    }

    public void getHistory(Task task){
        if (memory.size() < 10) {
            memory.add(task);
        } else {
            memory.remove(0);
            memory.add(task);
        }

    }

}

