package Chapter12Heap;

// heapSort.java
// Пирамидальная сортировка
// Запуск программы: C>java Heap2SortApp
import java.io.*;
////////////////////////////////////////////////////////////////
class Node2
{
    private int iData; // Данные (ключ)
    // -------------------------------------------------------------
    public Node2(int key) // Конструктор
    { iData = key; }
// -------------------------------------------------------------
public int getKey()
{ return iData; }
// -------------------------------------------------------------
} // Конец класса Node2
////////////////////////////////////////////////////////////////
class Heap2
{
    private Node2[] heapArray;
    private int maxSize; // Размер массива
    private int currentSize;
    // -------------------------------------------------------------
    public Heap2(int mx)
    {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node2[maxSize];
    }
    // -------------------------------------------------------------
    public Node2 remove()
    {
        Node2 root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }
    // -------------------------------------------------------------
    public void trickleDown(int index)
    {
        int largerChild;
        Node2 top = heapArray[index]; // Сохранение корня
        while(index < currentSize/2) // до нижнего уровня
        {
            int leftChild = 2*index+1;
            int rightChild = leftChild+1;
            // Определение большего потомка
            if(rightChild < currentSize && // (Правый потомок существует?)
                    heapArray[leftChild].getKey() <
                            heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;
            // top >= largerChild?
            if(top.getKey() >= heapArray[largerChild].getKey())
                break;
            // Потомок сдвигается вверх
            heapArray[index] = heapArray[largerChild];
            index = largerChild; // Переход вниз
        }
        heapArray[index] = top; // index <- корень
    }
    // -------------------------------------------------------------
    public void displayHeap2()
    {
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0; // Текущий элемент
        String dots = "...............................";
        System.out.println(dots+dots); // Верхний пунктир
        while(currentSize > 0) // Для каждого элемента пирамиды
        {
            if(column == 0) // Первый элемент в строке?
                for(int k=0; k<nBlanks; k++) // Предшествующие пробелы
                    System.out.print(' ');
            // Вывод элемента
            System.out.print(heapArray[j].getKey());
            if(++j == currentSize) // Вывод завершен?
                break;
            if(++column==itemsPerRow) // Конец строки?
            {
                nBlanks /= 2; // Половина пробелов
                itemsPerRow *= 2; // Вдвое больше элементов
                column = 0; // Начать заново
                System.out.println(); // Переход на новую строку
            }
            else // Следующий элемент в строке
                for(int k=0; k<nBlanks*2-2; k++)
                    System.out.print(' '); // Внутренние пробелы
        }
        System.out.println("\n"+dots+dots); // Нижний пунктир
    }
    // -------------------------------------------------------------
    public void displayArray()
    {
        for(int j=0; j<maxSize; j++)
            System.out.print(heapArray[j].getKey() + " ");
        System.out.println("");
    }
    // -------------------------------------------------------------
    public void insertAt(int index, Node2 newNode2)
    { heapArray[index] = newNode2; }
    // -------------------------------------------------------------
    public void incrementSize()
    { currentSize++; }
// -------------------------------------------------------------
} // Конец класса Heap2
////////////////////////////////////////////////////////////////
class HeapSortApp
{
    public static void main(String[] args) throws IOException
    {
        int size, j;
        System.out.print("Enter number of items: ");
        size = getInt();
        Heap2 theHeap2 = new Heap2(size);
        for(j=0; j<size; j++) // Заполнение массива
        { // случайными данными
            int random = (int)(java.lang.Math.random()*100);
            Node2 newNode2 = new Node2(random);
            theHeap2.insertAt(j, newNode2);
            theHeap2.incrementSize();
        }
        System.out.print("Random: ");
        theHeap2.displayArray(); // Вывод случайного массива
        for(j=size/2-1; j>=0; j--) // Преобразование массива в пирамиду
            theHeap2.trickleDown(j);
        System.out.print("Heap2: ");
        theHeap2.displayArray(); // Вывод массива
        theHeap2.displayHeap2(); // Вывод пирамиды
        for(j=size-1; j>=0; j--) // Извлечение из пирамиды
        { // с сохранением в конце массива
            Node2 biggestNode2 = theHeap2.remove();
            theHeap2.insertAt(j, biggestNode2);
        }
        System.out.print("Sorted: ");
        theHeap2.displayArray(); // Вывод отсортированного массива
    }
    // -------------------------------------------------------------
    public static String getString() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    //-------------------------------------------------------------
    public static int getInt() throws IOException
    {
        String s = getString();
        return Integer.parseInt(s);
    }
// -------------------------------------------------------------
} // Конец класса Heap2SortApp
