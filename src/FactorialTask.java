import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {

    private final int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    public synchronized Integer compute() {
        if (n <= 1) {
            return 1;
        }
        FactorialTask bypassTask = new FactorialTask(n-1);
        bypassTask.fork();

        int bypassResult = bypassTask.join();

        return n*bypassResult;
    }


}
