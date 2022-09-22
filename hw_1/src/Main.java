public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        System.out.println("add:");
        myHashMap.insert(null, "Nothing");
        myHashMap.insert(1, "Liaisan");
        myHashMap.insert(2, "Safarova");
        System.out.println("remove second node");
        myHashMap.remove(2);
    }
}
