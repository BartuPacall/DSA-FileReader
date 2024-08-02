
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LinkedList<T extends Comparable>{
    private Node<T> head;
    
    public Node<T> createNode(T val) {
        return new Node<T>(val);
    }
    
    public int count() {
        int count = 0; 
        Node<T> iterator = head; 
        while (iterator != null) { 
            count++;
            iterator = iterator.next; 
        }
        return count;
    }
    
    public void display() {
        Node<T> iterator = head;
        while (iterator != null) {
            System.out.println(iterator); 
            iterator = iterator.next; 
        }
    }
    
    public void insertToEnd(T val) {
            Node<T> myNode = createNode(val);
            if (head == null) {
                head = myNode;
            } 
            else {
                Node<T> iterator = head;
                while (iterator.next != null) {
                    iterator = iterator.next;
                }
                iterator.next = myNode;
            }
    }
    
    public void moveToFront(T val) {
        if (head == null || head.next == null || head.value.compareTo(val)==0) {
            return;
        }

        Node<T> previous = null;
        Node<T> iterator = head;

        while (iterator != null && !(iterator.value.compareTo(val)==0)) {
            previous = iterator; 
            iterator = iterator.next;
        }

        if (iterator != null) {
            previous.next = iterator.next; 
            iterator.next = head; 
            head = iterator; 
        }
        else{
            return;
        }
    }

    public int searchAll(String filename) {
        int totalMemoryAccesses = 0;
        int totalSearches = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                T val = (T) Integer.valueOf(line); // Assuming values are integers
                totalSearches++;
                Node<T> iterator = head;
                int memoryAccesses = 0;
                while (iterator != null) {
                    memoryAccesses++;
                    if (iterator.value.compareTo(val) == 0) {
                        totalMemoryAccesses += memoryAccesses;
                        break;
                    }
                    iterator = iterator.next;
                }
            }
        } catch (IOException e) {
            System.out.println("File could not be read !!!");
        }
        return totalMemoryAccesses / totalSearches;
    }
    
    
    
    



    
}