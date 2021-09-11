package Chapter11HashTables;

// hash.java
// Реализация хеш-таблицы с линейным пробированием
// Запуск программы: C:>java HashTableApp

import java.io.*;

////////////////////////////////////////////////////////////////
class DataItem { // (Данных может быть и больше)
    private int iData; // Данные (ключ)

    //--------------------------------------------------------------
    public DataItem(int ii) // Конструктор
    {
        iData = ii;
    }

    //--------------------------------------------------------------
    public int getKey() {
        return iData;
    }
//--------------------------------------------------------------
} // Конец класса DataItem

////////////////////////////////////////////////////////////////
class HashTable {
    private DataItem[] hashArray; // Массив ячеек хеш-таблицы
    private int arraySize;
    private DataItem nonItem;

    // -------------------------------------------------------------
    public HashTable(int size) // Конструктор
    {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1); // Ключ удаленного элемента -1
    }

    // -------------------------------------------------------------
    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null)
                System.out.print(hashArray[j].getKey() + " ");
            else
                System.out.print("** ");
        }
        System.out.println("");
    }

    // -------------------------------------------------------------
    public int hashFunc(int key) {
        return key % arraySize; // Хеш-функция
    }

    // -------------------------------------------------------------
    public void insert(DataItem item) // Вставка элемента данных
    // (Метод предполагает, что таблица не заполнена)
    {
        int key = item.getKey(); // Получение ключа
        int hashVal = hashFunc(key); // Хеширование ключа
        // Пока не будет найдена
        while (hashArray[hashVal] != null && // пустая ячейка или -1,
                hashArray[hashVal].getKey() != -1) {
            ++hashVal; // Переход к следующей ячейке
            hashVal %= arraySize; // При достижении конца таблицы
        } // происходит возврат к началу
        hashArray[hashVal] = item; // Вставка элемента
    }

    // -------------------------------------------------------------
    public DataItem delete(int key) // Удаление элемента данных
    {
        int hashVal = hashFunc(key); // Хеширование ключа
        while (hashArray[hashVal] != null) // Пока не будет найдена
        // пустая ячейка
        { // Ключ найден?
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal]; // Временное сохранение
                hashArray[hashVal] = nonItem; // Удаление элемента
                return temp; // Метод возвращает элемент
            }
            ++hashVal; // Переход к следующей ячейке
            hashVal %= arraySize; // При достижении конца таблицы
        } // происходит возврат к началу
        return null; // Элемент не найден
    }

    // -------------------------------------------------------------
    public DataItem find(int key) // Поиск элемента с заданным ключом
// (Метод предполагает, что таблица не заполнена)
    {
        int hashVal = hashFunc(key); // Хеширование ключа
        while (hashArray[hashVal] != null) // Пока не будет найдена
        // пустая ячейка
        { // Ключ найден?
            if (hashArray[hashVal].getKey() == key)
                return hashArray[hashVal]; // Да, вернуть элемент
            ++hashVal; // Переход к следующей ячейке
            hashVal %= arraySize; // При достижении конца таблицы
        } // происходит возврат к началу
        return null; // Элемент не найден
    }
// -------------------------------------------------------------
} // Конец класса HashTable

////////////////////////////////////////////////////////////////
class HashTableApp {
    public static void main(String[] args) throws IOException {
        DataItem aDataItem;
        int aKey, size, n, keysPerCell;
        // Ввод размеров
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        keysPerCell = 10;
        // Создание таблицы
        HashTable theHashTable = new HashTable(size);
        for (int j = 0; j < n; j++) // Вставка данных
        {
            aKey = (int) (java.lang.Math.random() *
                    keysPerCell * size);
            aDataItem = new DataItem(aKey);
            theHashTable.insert(aDataItem);
        }
        while (true) // Взаимодействие с пользователем
        {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new DataItem(aKey);
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if (aDataItem != null) {
                        System.out.println("Found " + aKey);
                    } else
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

    //--------------------------------------------------------------
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
} // Конец класса HashTableApp
////////////////////////////////////////////////////////////////
