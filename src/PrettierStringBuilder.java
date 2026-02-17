import java.util.Deque;
import java.util.ArrayDeque;


public class PrettierStringBuilder {
    private StringBuilder sb = new StringBuilder();
    private final static int UNDO_LIMIT_SIZE = 10;
    private Deque<Snapshot> snapshots = new ArrayDeque<>();

    @Override
    public String toString() {
        return sb.toString();
    }

    public void append(String state) {
        Snapshot operation = new Snapshot(sb.toString());
        snapshots.push(operation);
        if (snapshots.size() > UNDO_LIMIT_SIZE) {
            snapshots.removeLast();
        }
        sb.append(state);
    }

    public void undo() {
        if (!snapshots.isEmpty()) {
            Snapshot lastOperation = snapshots.pop();
            sb.setLength(0);
            sb.append(lastOperation.getState());
        }
    }

    public void delete(int start, int end) {
        Snapshot operation = new Snapshot(sb.toString());
        snapshots.push(operation);
        if (snapshots.size() > UNDO_LIMIT_SIZE) {
            snapshots.removeLast();
        }
        sb.delete(start, end);
    }
}