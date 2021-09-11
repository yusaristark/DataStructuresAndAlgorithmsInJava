package Chapter11HashTables;

// hashChain.java
// Реализация хеш-таблицы с использованием метода цепочек
// Запуск программы: C:>java HashChainApp

import java.io.*;

////////////////////////////////////////////////////////////////
class Link { // (Здесь могут быть другие поля)
    private int iData; // Данные
    public Link next; // Следующий элемент списка

    // -------------------------------------------------------------
    public Link(int it) // Конструктор
    {
        iData = it;
    }

    // -------------------------------------------------------------
    public int getKey() {
        return iData;
    }

    // -------------------------------------------------------------
    public void displayLink() // Вывод содержимого элемента
    {
        System.out.print(iData + " ");
    }
} // Конец класса Link

////////////////////////////////////////////////////////////////
class SortedList {
    private Link first; // Ссылка на первый элемент списка

    // -------------------------------------------------------------
    public void SortedList() // Конструктор
    {
        first = null;
    }

    // -------------------------------------------------------------
    public void insert(Link theLink) // Вставка в порядке сортировки
    {
        int key = theLink.getKey();
        Link previous = null; // Начиная с первого элемента
        Link current = first;
        // До конца списка
        while (current != null && key > current.getKey()) { // Или пока current <= key
            previous = current;
            current = current.next; // Переход к следующему элементу
        }
        if (previous == null) // В начале списка
            first = theLink; // first --> новый элемент
        else // Не в начале
            previous.next = theLink; // prev --> новый элемент
        theLink.next = current; // новый элемент --> current
    }

    // -------------------------------------------------------------
    public void delete(int key) // Удаление элемента
    { // (предполагается, что список не пуст)
        Link previous = null; // Начиная с первого элемента
        Link current = first;
        // До конца списка
        while (current != null && key != current.getKey()) { // или пока key != current
            previous = current;
            current = current.next; // Переход к следующему элементу
        }
        // Отсоединение
        if (previous == null) // Если первый элемент,
            first = first.next; // изменить first
        else // В противном случае
            previous.next = current.next; // удалить текущий элемент
    } // end delete()

    // -------------------------------------------------------------
    public Link find(int key) // Поиск элемента с заданным ключом
    {
        Link current = first; // Начиная от начала списка
        // До конца списка
        while (current != null && current.getKey() <= key) { // или пока ключ не превысит current,
            if (current.getKey() == key) // Искомый элемент?
                return current; // Совпадение обнаружено
            current = current.next; // Переход к следующему элементу
        }
        return null; // Элемент не найден
    }

    // -------------------------------------------------------------
    public void displayList() {
        System.out.print("List (first-->last): ");
        Link current = first; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод данных
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }
} // Конец класса SortedList

////////////////////////////////////////////////////////////////
class HashTable3 {
    private SortedList[] hashArray; // Массив списков
    private int arraySize;

    // -------------------------------------------------------------
    public HashTable3(int size) // Конструктор
    {
        arraySize = size;
        hashArray = new SortedList[arraySize]; // Создание массива
        for (int j = 0; j < arraySize; j++) // Заполнение массива
            hashArray[j] = new SortedList(); // списками
    }

    // -------------------------------------------------------------
    public void displayTable() {
        for (int j = 0; j < arraySize; j++) // Для каждой ячейки
        {
            System.out.print(j + ". "); // Вывод номера ячейки
            hashArray[j].displayList(); // Вывод списка
        }
    }

    // -------------------------------------------------------------
    public int hashFunc(int key) // Хеш-функция
    {
        return key % arraySize;
    }

    // -------------------------------------------------------------
    public void insert(Link theLink) // Вставка элемента
    {
        int key = theLink.getKey();
        int hashVal = hashFunc(key); // Хеширование ключа
        hashArray[hashVal].insert(theLink); // Вставка в позиции hashVal
    }

    // -------------------------------------------------------------
    public void delete(int key) // Удаление элемента
    {
        int hashVal = hashFunc(key); // Хеширование ключа
        hashArray[hashVal].delete(key); // Удаление
    }

    // -------------------------------------------------------------
    public Link find(int key) // Поиск элемента
    {
        int hashVal = hashFunc(key); // Хеширование ключа
        Link theLink = hashArray[hashVal].find(key); // Поиск
        return theLink; // Метод возвращает найденный элемент
    }
// -------------------------------------------------------------
} // Конец класса HashTable3

////////////////////////////////////////////////////////////////
class HashChainApp {
    public static void main(String[] args) throws IOException {
        int aKey;
        Link aDataItem;
        int size, n, keysPerCell = 100;
        // Ввод размеров
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        // Создание таблицы
        HashTable3 theHashTable3 = new HashTable3(size);
        for (int j = 0; j < n; j++) // Вставка данных
        {
            aKey = (int) (java.lang.Math.random() *
                    keysPerCell * size);
            aDataItem = new Link(aKey);
            theHashTable3.insert(aDataItem);
        }
        while (true) // Взаимодействие с пользователем
        {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theHashTable3.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new Link(aKey);
                    theHashTable3.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable3.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable3.find(aKey);
                    if (aDataItem != null)
                        System.out.println("Found " + aKey);
                    else
                        System.out.println("Could not find " + aKey);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }

    //--------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    //-------------------------------------------------------------
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
//--------------------------------------------------------------
} // Конец класса HashChainApp
////////////////////////////////////////////////////////////////