package Todo.Model;

public class Subtask extends Task {
    private long idEpic;

    public long getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(long idEpic) {
        this.idEpic = idEpic;
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
        return "Subtask{" +
                "idEpic=" + idEpic +
                "} " + super.toString();
    }

    public Subtask(String name, String description, Status status, long idEpic) {
        super(name, description, status);
        setIdEpic(idEpic);
    }


}

