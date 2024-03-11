package coalescedHash;


class Node {
    int data;
    int index;
    Node next;

    public Node(int index, int data) {
        this.data = data;
        this.index = index;
        this.next = null;
    }
}

class CoalescedHashing {
    Node[] hashTable;
    Node[] pointers;
    int size;

    public CoalescedHashing(int size) {
        this.size = size;
        hashTable = new Node[size];
        pointers = new Node[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = null;
        }
        for (int i = 0; i < size; i++) {
            pointers[i] = null;
        }
    }

    public int hashFunction(int key) {
        return key % 10;
    }

    public void insert(int data) {
        int index = hashFunction(data);
        Node newNode = new Node(index, data);
        if (hashTable[index] == null) {
            hashTable[index] = newNode;
        } else {
            int newIndex = findEmptyIndex(index);
            newNode = new Node(newIndex, data);
            hashTable[newIndex] = newNode;
            addPointer(index, newIndex);
            Node temp = hashTable[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }
    
    public int findEmptyIndex(int startIndex) {
        int index = size - 1;
        while (hashTable[index] != null) {
            index -= 1;
        }
        return index;
    }
    
    public void addPointer(int index, int newIndex) {
            while(pointers[index] != null) {
                index = pointers[index].data;
            }
            Node newPointer = new Node(index, newIndex);
            pointers[index] = newPointer;
    }
    
    public void display() {
            System.out.println("Value\tData\tNext");
            for (int i = 0; i < size; i++) {
                Node temp = hashTable[i];
                if(temp == null) {
                    System.out.println(i);
                    continue;
                }
                Node pointer = pointers[i];
                System.out.print(temp.index + "\t" + temp.data + "\t");
                if (pointer != null) {
                    System.out.print(pointer.data);
                }
                else System.out.print("Null");
                System.out.println();
            }
    }

}

 public class coalescedHash {
    public static void main(String[] args) {
        CoalescedHashing ch = new CoalescedHashing(10);
        int[] input = {20, 35, 16, 40, 45, 25, 32, 37, 22, 55};
        
        for (int i : input) {
            ch.insert(i);
        }
        ch.display();
    }
}
