package Chapter04StacksAndQueues;

class Deque {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    //--------------------------------------------------------------
    public Deque(int s) // Конструктор
    {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    //--------------------------------------------------------------
    public void insertRight(long j) // Вставка элемента в конец очереди
    {
        if (rear == maxSize - 1) // Циклический перенос
            rear = -1;
        queArray[++rear] = j; // Увеличение rear и вставка
        nItems++; // Увеличение количества элементов
    }

    public void insertLeft(long j) {
        if (front == 0) {
            front = maxSize;
        }
        queArray[--front] = j;
        nItems++;
    }
    //--------------------------------------------------------------
    public long removeLeft() // Извлечение элемента в начале очереди
    {
        long temp = queArray[front++]; // Выборка и увеличение front
        if (front == maxSize) // Циклический перенос
            front = 0;
        nItems--; // Уменьшение количества элементов
        return temp;
    }

    public long removeRight() {
        long temp = queArray[rear--];
        if (rear == -1) {
            rear = maxSize;
        }
        nItems--;
        return temp;
    }

    //--------------------------------------------------------------
    public long peekFront() // Чтение элемента в начале очереди
    {
        return queArray[front];
    }

    //--------------------------------------------------------------
    public boolean isEmpty() // true, если очередь пуста
    {
        return (nItems == 0);
    }

    //--------------------------------------------------------------
    public boolean isFull() // true, если очередь заполнена
    {
        return (nItems == maxSize);
    }

    //--------------------------------------------------------------
    public int size() // Количество элементов в очереди
    {
        return nItems;
    }

    public void display() {
        if (!isEmpty()) {
            if (nItems == 1) {
                System.out.println(queArray[rear]);
                return;
            }
            if (front < rear) {
                for (int i = front; i < rear + 1; i++) {
                    System.out.print(queArray[i] + " ");
                }
            } else {
                for (int i = front; i < maxSize; i++) {
                    System.out.print(queArray[i] + " ");
                }
                for (int i = 0; i < rear + 1; i++) {
                    System.out.print(queArray[i] + " ");
                }
            }
            System.out.println();
        } else {
            System.out.println("[]");
        }
    }
//--------------------------------------------------------------
}

public class DequeApp {
    public static void main(String[] args) {
        Deque deque = new Deque(5); // Очередь из 5 ячеек
        deque.display();
        deque.insertRight(10); // Вставка 4 элементов
        deque.display();
        deque.insertRight(20);
        deque.insertRight(30);
        deque.insertRight(40);
        deque.display();
        deque.removeLeft(); // Извлечение 3 элементов
        deque.removeLeft(); // (10, 20, 30)
        deque.removeLeft();
        deque.insertLeft(50); // Вставка еще 4 элементов
        deque.insertRight(60); // (с циклическим переносом)
        deque.insertRight(70);
        deque.insertLeft(80);
        deque.display();
        deque.removeLeft();
        deque.display();
    }

}
