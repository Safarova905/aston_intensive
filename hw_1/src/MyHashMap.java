import java.util.*;
public class MyHashMap<K, V> implements Methods<K, V> {
    private Node<K, V>[] hashtable;
    private int size = 0;

    public MyHashMap() {
        hashtable = new Node[16];
    }

/*Вставка новой ноды с условиями: если хэш-таблица пустая, то добавляем первое значение
* если хэш-таблица не пустая, то проверка условий на уже существующий ключ и на коллизию*/
    @Override
    public boolean insert(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        int index = hash(key);
        if (hashtable[index] == null) {
            return simpleAdd(index, newNode);
        }
        List<Node<K, V>> nodeList = hashtable[index].getNodes();

        return false;
    }

    private int hash(K key) {
        return hashCode() % hashtable.length;
    }

    /*добавляем новую ноду и увеличиваем размер списка на единицу*/
    private boolean simpleAdd(int index, Node<K, V> newNode) {
        hashtable[index] = new Node<>(null, null);
        hashtable[index].getNodes().add(newNode);
        size++;
        return true;
    }

/*Если хэш-коды одинкаовые, но ключи и значения не равны, то добавляем новую ноду*/
    private boolean collision(Node<K, V> nodeFromList, Node<K, V> newNode, List<Node<K, V>> listNodes) {
        if (newNode.hashCode() == nodeFromList.hashCode() && !Objects.equals(newNode.key, nodeFromList.key) && !Objects.equals(newNode.value, nodeFromList.value)) {
            listNodes.add(newNode);
            size++;
            return true;
        }
        return false;
    }

/*Получение значения по ключу, проходясь по всему списку нод,
получаем значение, если индекс не больше длины хэш-таблицы или индекс не равен нулл,
тогда возврат нулл*/
    @Override
    public V get(K key) {
         int index = hash(key);
         if (index < hashtable.length && hashtable[index] != null) {
             List<Node<K, V>> nodeList = hashtable[index].getNodes();
             for (Node<K, V> node : nodeList) {
                 if (key.equals(node.getKey())) {
                     return node.getValue();
                 }
             }
         }
        return null;
    }

/*удаление ноды по ключу, условия: возврат false если нод нет,
если размер таблицы - 1, то присвоение ячейке нулл, возврат true,
если таблица не пустая и размер не 1, то проходимся по всему листу нод,
найдя по ключу совпавшее, удаляем его*/
    @Override
    public boolean remove(K key) {
        int index = hash(key);
        if (hashtable[index] == null) {
            return false;
        }
        if (hashtable[index].getNodes().size() == 1) {
            hashtable[index] = null;
            return true;
        }
        List<Node<K, V>> nodeList = hashtable[index].getNodes();
        for (Node<K, V> node : nodeList) {
            if (key.equals(node.getKey())) {
                nodeList.remove(node);
                return true;
            }
        }
        return false;
    }

/*Удаление всего списка нод, условия: если таблица пустая, то возврат false,
* если в таблице есть ноды, то проходя по всему списку, удаляем все ноды*/
    @Override
    public boolean removeAll(K key) {
        int index = hash(key);
        if (hashtable[index] == null) {
            return false;
        }
        List<Node<K, V>> nodeList = hashtable[index].getNodes();
        for (Node<K, V> node : nodeList) {
            nodeList.remove(node);
            return true;
        }
        return false;
    }

    @Override
    public List<K> sort(K key) {
        int index = hash(key);
        List<K> keys = new ArrayList<>();
        List<Node<K, V>> nodeList = hashtable[index].getNodes();
        for (Node<K, V> node : nodeList) {
            keys.add(node.getKey());
        }
        Arrays.sort(new List[]{keys});
        return keys;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }
/*Класс, который описывает ноду: ячейку хэшмапа, который имеет ключ-значение*/
    private class Node<K, V> {
        private List<Node<K, V>> nodes;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = this.key;
            this.value = this.value;
            nodes = new LinkedList<Node<K, V>>();
        }

        private List<Node<K, V>> getNodes() {
            return nodes;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(V value) {
            this.value = value;
        }

        public int hashCode() {
            int hash = 31;
            hash = hash * 17 + key.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object)
                return true;
            return false;
        }
    }
}
