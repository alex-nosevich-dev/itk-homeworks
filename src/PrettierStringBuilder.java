import java.util.ArrayList;
import java.util.List;

public class PrettierStringBuilder {
    StringBuilder sb = new StringBuilder();

    List<Snapshot> snapshotList = new ArrayList<>();

    //Создаем свой метод append с функцией записи состояния
    public void append(String name) {
        snapshotList.add(new Snapshot(sb.toString()));
        sb.append(name);
    }
    //Свой метод удаления с возможностью записи состояния
    public void delete(int start, int end) {
        snapshotList.add(new Snapshot(sb.toString()));
        sb.delete(start, end);
    }
    //Свой метод вставки, также с возможностью записи состояния
    public void insert(int offset, String str) {
        snapshotList.add(new Snapshot(sb.toString()));
        sb.insert(offset, str);
    }

    //Метод отката назад, возвращаемый предыдушее состояние
    public void undo() {
        if (!snapshotList.isEmpty()) {
            Snapshot previousChanges = snapshotList.remove(snapshotList.size()-1);
            sb.setLength(0);
            sb.append(previousChanges.getName());
        }
    }


    //Из-за проблем с выводом пришлось переопределить toString
    @Override
    public String toString() {
        return sb.toString();
    }
}
