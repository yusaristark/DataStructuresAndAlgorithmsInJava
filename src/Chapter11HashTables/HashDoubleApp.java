package Chapter11HashTables;

// hashDouble.java
// Реализация хеш-таблицы с двойным хешированием
// Запуск программы: C:>java HashDoubleApp

import java.io.*;

////////////////////////////////////////////////////////////////
class DataItem2 { // (Данных может быть и больше)
    private int iData; // Данные (ключ)

    //--------------------------------------------------------------
    public DataItem2(int ii) // Конструктор
    {
        iData = ii;
    }

    //--------------------------------------------------------------
    public int getKey() {
        return iData;
    }
//--------------------------------------------------------------
} // Конец класса DataItem2

////////////////////////////////////////////////////////////////
class HashTable2 {
    private DataItem2[] hashArray; // Массив ячеек хеш-таблицы
    private int arraySize;
    private DataItem2 nonItem; // Для удаленных элементов

    // -------------------------------------------------------------
    HashTable2(int size) // Конструктор
    {
        arraySize = size;
        hashArray = new DataItem2[arraySize];
        nonItem = new DataItem2(-1);
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
    public int hashFunc1(int key) {
        return key % arraySize;
    }

    // -------------------------------------------------------------
    public int hashFunc2(int key) {
        // Возвращаемое значение отлично от нуля, меньше размера массива,
        // функция отлична от хеш-функции 1
        // Размер массива должен быть простым по отношению к 5, 4, 3 и 2
        return 5 - key % 5;
    }

    // -------------------------------------------------------------
    // Вставка элемента данных
    public void insert(int key, DataItem2 item)
    // (Метод предполагает, что таблица не заполнена)
    {
        int hashVal = hashFunc1(key); // Хеширование ключа
        int stepSize = hashFunc2(key); // Вычисление смещения
        // Пока не будет найдена
        while (hashArray[hashVal] != null && // пустая ячейка или -1
                hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize; // Прибавление смещения
            hashVal %= arraySize; // Возврат к началу
        }
        hashArray[hashVal] = item; // Вставка элемента
    }

    // -------------------------------------------------------------
    public DataItem2 delete(int key) // Удаление элемента данных
    {
        int hashVal = hashFunc1(key); // Хеширование ключа
        int stepSize = hashFunc2(key); // Вычисление смещения
        while (hashArray[hashVal] != null) // Пока не найдена пустая ячейка
        { // Ключ найден?
            if (hashArray[hashVal].getKey() == key) {
                DataItem2 temp = hashArray[hashVal]; // Временное сохранение
                hashArray[hashVal] = nonItem; // Удаление элемента
                return temp; // Метод возвращает элемент
            }
            hashVal += stepSize; // Прибавление смещения
            hashVal %= arraySize; // Возврат к началу
        }
        return null; // Элемент не найден
    }

    // -------------------------------------------------------------
    public DataItem2 find(int key) // Поиск элемента с заданным ключом
    // (Метод предполагает, что таблица не заполнена)
    {
        int hashVal = hashFunc1(key); // Хеширование ключа
        int stepSize = hashFunc2(key); // Вычисление смещения
        while (hashArray[hashVal] != null) // Пока не найдена пустая ячейка
        { // Ключ найден?
            if (hashArray[hashVal].getKey() == key)
                return hashArray[hashVal]; // Да, метод возвращает элемент
            hashVal += stepSize; // Прибавление смещения
            hashVal %= arraySize; // Возврат к началу
        }
        return null; // Элемент не найден
    }
// -------------------------------------------------------------
} // Конец класса HashTable2

////////////////////////////////////////////////////////////////
class HashDoubleApp {
    public static void main(String[] args) throws IOException {
        int aKey;
        DataItem2 aDataItem2;
        int size, n;
        // Ввод размеров
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        // Создание таблицы
        HashTable2 theHashTable2 = new HashTable2(size);
        for (int j = 0; j < n; j++) // Вставка данных
        {
            aKey = (int) (java.lang.Math.random() * 2 * size);
            aDataItem2 = new DataItem2(aKey);
            theHashTable2.insert(aKey, aDataItem2);
        }
        while (true) // Взаимодействие с пользователем
        {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theHashTable2.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem2 = new DataItem2(aKey);
                    theHashTable2.insert(aKey, aDataItem2);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable2.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem2 = theHashTable2.find(aKey);
                    if (aDataItem2 != null)
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
        return br.readLine();
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
} // Конец класса HashDoubleApp
////////////////////////////////////////////////////////////////

