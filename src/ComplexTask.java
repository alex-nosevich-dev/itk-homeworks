import java.util.UUID;

public class ComplexTask {
    private final UUID id;

    public ComplexTask(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String execute() {
        System.out.println(Thread.currentThread().getName() + "номер задачи " + id);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Выполнена задача...";
    }
}


