import java.util.List;

public interface Methods<K, V> extends Iterable<V> {
    boolean insert(K key, V value);
    V get(K key);
    boolean remove(K key);
    boolean removeAll(K key);
    List<K> sort(K key);
    int size();
}
