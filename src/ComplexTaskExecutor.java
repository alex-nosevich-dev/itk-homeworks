import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    public final int taskNumber;
//    public final ExecutorService executorService;
    public final CyclicBarrier cyclicBarrier;
    private final Map<UUID, String> results = new ConcurrentHashMap<>();

    public ComplexTaskExecutor(int taskNumber) {
        this.taskNumber = taskNumber;

        Runnable barrierAction = () -> {
            System.out.println(Thread.currentThread().getName() + "Barrierr action ...");
            results.forEach((id, result) -> System.out.println("Задача " +
                    id + ". " + result));
        };

        this.cyclicBarrier = new CyclicBarrier(taskNumber, barrierAction);
//        this.executorService = Executors.newFixedThreadPool(taskNumber);

    }

    public synchronized void executeTasks(int taskNumber) {
        if (taskNumber != this.taskNumber) {
            throw new RuntimeException("Несовпадени задач");
        }
        ExecutorService executorService = Executors.newFixedThreadPool(taskNumber);

        for (int i = 0; i < taskNumber; i++) {
            executorService.submit(() -> {
                UUID id = UUID.randomUUID();
                ComplexTask task = new ComplexTask(id);
                String result = task.execute();
                results.put(id, result);

                try {
                    System.out.println(Thread.currentThread().getName() + "Ожидает barrier");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (BrokenBarrierException e) {
                    System.out.println("barrier упал " + e.getMessage());
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}