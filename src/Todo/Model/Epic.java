package Todo.Model;

import java.util.ArrayList;
import java.util.List;


public class Epic extends Task {
    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtaskList(Subtask subtask) {
        subtasks.add(subtask);
    }

    private final List<Subtask> subtasks;

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public long setId(long id) {
        super.setId(id);
        return id;
    }

    @Override
    public Status getStatus() {
        return super.getStatus();
    }

    @Override
    public void setStatus(Status status) {
        super.setStatus(status);
    }


    @Override
    public String toString() {
        return "Epic{" + super.toString() +
                "} ";
    }

    public Epic(String name, String description, Status status) {
        super(name, description, status);
        this.subtasks = new ArrayList<>();

    }


}

