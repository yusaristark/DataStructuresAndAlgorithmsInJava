package Chapter04StacksAndQueues;

import java.util.Arrays;

class PriorityQFL {
    // Элементы массива сортируются по значению ключа,
    // от максимумa (0) до минимума (maxSize-1)
    private int maxSize;
    private long[] queArray;
    private int nItems;

    //-------------------------------------------------------------
    public PriorityQFL(int s) // Конструктор
    {
        maxSize = s;
        queArray = new long[maxSize];
        nItems = 0;
    }

    //-------------------------------------------------------------
    public void insert(long item) // Вставка элемента
    {
        int j;
        if (!isFull()) {
            queArray[nItems++] = item;
        }
    } //

    //-------------------------------------------------------------
    public long remove() // Извлечение минимального элемента
    {
        if (!isEmpty()) {
            long value = queArray[0];
            int index = 0;
            int i;
            for (i = 1; i < nItems; i++) {
                if (queArray[i] < value) {
                    value = queArray[i];
                    index = i;
                }
            }
            for (int j = index; j < nItems - 1; j++) {
                queArray[j] = queArray[j + 1];
            }
            --nItems;
            return value;
        }
        return queArray[--nItems];
    }

    public void display() {
        if (!isEmpty()) {
            System.out.print("[ ");
            for (int i = 0; i < nItems; i++) {
                System.out.print(queArray[i] + " ");
            }
            System.out.println("]");
        }
    }

    //-------------------------------------------------------------
    public long peekMin() // Чтение минимального элемента
    {
        return queArray[nItems - 1];
    }

    //-------------------------------------------------------------
    public boolean isEmpty() // true, если очередь пуста
    {
        return (nItems == 0);
    }

    //-------------------------------------------------------------
    public boolean isFull() // true, если очередь заполнена
    {
        return (nItems == maxSize);
    }
//-------------------------------------------------------------
} // Конец класса PriorityQ

public class PriorityQueueFastInsertionLongPop {
    public static void main(String[] args) {
        PriorityQFL thePQ = new PriorityQFL(5);
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(20);
        thePQ.display();
        while (!thePQ.isEmpty()) {
            long item = thePQ.remove();
            System.out.print(item + " "); // 10, 20, 30, 40, 50
        }
        System.out.println("");
    }
}
