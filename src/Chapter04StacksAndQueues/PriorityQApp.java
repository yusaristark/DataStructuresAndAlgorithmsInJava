package Chapter04StacksAndQueues;

import java.io.IOException;

// priorityQ.java
// Работа с приоритетной очередью
// Запуск программы: C>java PriorityQApp
////////////////////////////////////////////////////////////////
class PriorityQ {
    // Элементы массива сортируются по значению ключа,
    // от максимумa (0) до минимума (maxSize-1)
    private int maxSize;
    private long[] queArray;
    private int nItems;

    //-------------------------------------------------------------
    public PriorityQ(int s) // Конструктор
    {
        maxSize = s;
        queArray = new long[maxSize];
        nItems = 0;
    }

    //-------------------------------------------------------------
    public void insert(long item) // Вставка элемента
    {
        int j;
        if (nItems == 0) // Если очередь пуста,
            queArray[nItems++] = item; // вставляем в ячейку 0
        else // Если очередь содержит элементы
        {
            for (j = nItems - 1; j >= 0; j--) // Перебор в обратном направлении
            {
                if (item > queArray[j]) // Если новый элемент больше,
                    queArray[j + 1] = queArray[j]; // сдвинуть вверх
                else // Если меньше,
                    break; // сдвиг прекращается
            }
            queArray[j + 1] = item; // Вставка элемента
            nItems++;
        }
    } //

    //-------------------------------------------------------------
    public long remove() // Извлечение минимального элемента
    {
        return queArray[--nItems];
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

////////////////////////////////////////////////////////////////
class PriorityQApp {
    public static void main(String[] args) throws IOException {
        PriorityQ thePQ = new PriorityQ(5);
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(20);
        while (!thePQ.isEmpty()) {
            long item = thePQ.remove();
            System.out.print(item + " "); // 10, 20, 30, 40, 50
        }
        System.out.println("");
    }
//-------------------------------------------------------------
} // Конец класса PriorityQApp
