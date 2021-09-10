package Chapter07NonTrivialSort;

// quickSort1.java
// Простая версия быстрой сортировки
// Запуск программы: C>java QuickSort1App
////////////////////////////////////////////////////////////////
class ArrayIns {
    private long[] theArray; // Ссылка на массив
    private int nElems; // Количество элементов

    //--------------------------------------------------------------
    public ArrayIns(int max) // Конструктор
    {
        theArray = new long[max]; // Создание массива
        nElems = 0; // Пока нет ни одного элемента
    }

    //--------------------------------------------------------------
    public void insert(long value) // Вставка элемента в массив
    {
        theArray[nElems] = value; // Собственно вставка
        nElems++; // Увеличение размера
    }

    //--------------------------------------------------------------
    public void display() // Вывод содержимого массива
    {
        System.out.print("A=");
        for (int j = 0; j < nElems; j++) // Для каждого элемента
            System.out.print(theArray[j] + " "); // Вывод
        System.out.println("");
    }

    //--------------------------------------------------------------
    public void quickSort() {
        recQuickSort(0, nElems - 1);
    }

    //--------------------------------------------------------------
    public void recQuickSort(int left, int right) {
        if (right - left <= 0) // Если размер <= 1,
            return; // массив отсортирован
        else // Для размера 2 и более
        {
            long pivot = theArray[right]; // Крайний правый элемент
            // Разбиение диапазона
            int partition = partitionIt(left, right, pivot);
            recQuickSort(left, partition - 1); // Сортировка левой части
            recQuickSort(partition + 1, right); // Сортировка правой части
        }
    }

    //--------------------------------------------------------------
    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1; // Левая граница (после ++)
        int rightPtr = right; // Правая граница-1 (after --)
        while (true) { // Поиск большего элемента
            while (theArray[++leftPtr] < pivot)
                ; // (nop)
            // Поиск меньшего элемента
            while (rightPtr > 0 && theArray[--rightPtr] > pivot)
                ; // (nop)
            if (leftPtr >= rightPtr) // Если указатели сошлись,
                break; // разбиение закончено.
            else // В противном случае
                swap(leftPtr, rightPtr); // поменять элементы местами.
        }
        swap(leftPtr, right); // Перестановка опорного элемента
        return leftPtr; // Возврат позиции опорного элемента
    }

    //--------------------------------------------------------------
    public void swap(int dex1, int dex2) // Перестановка двух элементов
    {
        long temp;
        temp = theArray[dex1]; // A копируется в temp
        theArray[dex1] = theArray[dex2]; // B копируется в A
        theArray[dex2] = temp; // temp копируется в B
    }
//--------------------------------------------------------------
} // Конец класса ArrayIns

////////////////////////////////////////////////////////////////
class QuickSort1App {
    public static void main(String[] args) {
        int maxSize = 16; // Размер массива
        ArrayIns arr;
        arr = new ArrayIns(maxSize); // Создание массива
        for (int j = 0; j < maxSize; j++) // Заполнение массива
        { // случайными числами.
            long n = (int) (java.lang.Math.random() * 99);
            arr.insert(n);
        }
        arr.display(); // Вывод элементов
        arr.quickSort(); // Быстрая сортировка
        arr.display(); // Повторный вывод элементов
    }
} // Конец класса QuickSort1App
