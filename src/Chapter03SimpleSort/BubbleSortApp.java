package Chapter03SimpleSort;

import java.util.Arrays;

// bubbleSort.java
// Пузырьковая сортировка
// Запуск программы: C>java BubbleSortApp
////////////////////////////////////////////////////////////////
class ArrayBub {
    private long[] a; // Ссылка на массив a
    private int nElems; // Количество элементов данных
    //--------------------------------------------------------------
    public ArrayBub(int max) // Конструктор
    {
        a = new long[max]; // Создание массива
        nElems = 0; // Пока нет ни одного элемента
    }
    //--------------------------------------------------------------
    public void insert(long value) // Вставка элемента в массив
    {
        a[nElems] = value; // Собственно вставка
        nElems++; // Увеличение размера
    }
    //--------------------------------------------------------------
    public void display() // Вывод содержимого массива
    {
        for(int j=0; j<nElems; j++) // Для каждого элемента
            System.out.print(a[j] + " "); // Вывод
        System.out.println("");
    }
    //--------------------------------------------------------------
    public void bubbleSort()
    {
        int out, in;
        for(out=nElems-1; out>1; out--) {// Внешний цикл (обратный)
            for (in = 0; in < out; in++) // Внутренний цикл (прямой)
                if (a[in] > a[in + 1]) // Порядок нарушен?
                    swap(in, in + 1); // Поменять местами
            for (in = in - 1; in > 0; in--) {
                if (a[in] < a[in - 1]) {
                    swap(in, in - 1);
                }
            }
        }
    }

    public void oddEvenSort() {
        for (int i = 0; i < nElems; i++) {
            int j;
            if (i % 2 == 0) {
                j = 0;
            } else {
                j = 1;
            }
            for (; j < nElems - 1; j += 2) {
                if (a[j] > a[j + 1]) {
                    swap(j, j + 1);
                }
            }

        }
    }
    //--------------------------------------------------------------
    private void swap(int one, int two)
    {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
//--------------------------------------------------------------
} // Конец класса ArrayBub
////////////////////////////////////////////////////////////////
class BubbleSortApp
{
    public static void main(String[] args)
    {
        int maxSize = 100; // Размер массива
        ArrayBub arr; // Ссылка на массив
        arr = new ArrayBub(maxSize); // Создание массива
        arr.insert(77); // Вставка 10 элементов
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(0);
        arr.insert(66);
        arr.insert(33);
        arr.display(); // Вывод элементов
        //arr.bubbleSort(); // Пузырьковая сортировка элементов
        arr.oddEvenSort();
        arr.display(); // Повторный вывод
    } //
} // Конец класса BubbleSortApp
////////////////////////////////////////////////////////////////
