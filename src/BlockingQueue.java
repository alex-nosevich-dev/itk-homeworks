import java.util.ArrayDeque;
import java.util.Queue;

public class BlockingQueue <T> {
    private final int LIMIT;
    private final Queue<T> queue;

    public BlockingQueue(int limit){
        this.queue = new ArrayDeque<>();
        this.LIMIT = limit;
    }

    public synchronized void unqueque(T element) throws InterruptedException {
        while(queue.size()==LIMIT){
            wait();
        }
        queue.add(element);
        notifyAll();
    }

    public synchronized T dequeque() throws InterruptedException {
        while (queue.isEmpty()){
            wait();
        }
        T unit = queue.remove();
        notifyAll();
        return unit;
    }

    public synchronized int size(){
        return queue.size();
    }
}