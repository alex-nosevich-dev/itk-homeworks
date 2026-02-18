import java.util.HashMap;
import java.util.Map;

public class MapSwitcher {
    public <T> Map<T, Integer> switcher(T[] array) {
        //int count = 0;
        Map<T, Integer> result = new HashMap<>();
        for (T element:array) {
            if (!result.containsKey(element)) {
                result.put(element, 1);
            } else {
                Integer oldCount = result.get(element);
                result.put(element, oldCount + 1);
            }
        }
        return result;
    }
}
