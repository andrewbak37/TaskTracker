package Todo.Manager;

import Todo.Model.Task;

import java.util.List;

public interface HistoryManager {

    void add(Task task);

    void remove(Task task);

    List<Task> getHistory();
}
