import java.util.Arrays;

public class CollectionService {
    public <T> T[] filter(T[] array, Filter<T> filter) {
        int length = array.length;
        T[] result = Arrays.copyOf( array, array.length);
        for (int i = 0; i < result.length; i++) {
            result[i] = filter.apply(array[i]);
        }
        return result;
    }
}