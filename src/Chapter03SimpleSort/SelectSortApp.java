package Chapter03SimpleSort;

// selectSort.java
// Сортировка методом выбора
// Запуск программы: C>java SelectSortApp
////////////////////////////////////////////////////////////////
class ArraySel {
    private final long[] a; // Ссылка на массив a
    private int nElems; // Количество элементов данных

    //--------------------------------------------------------------
    public ArraySel(int max) // Конструктор
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
        for (int j = 0; j < nElems; j++) // Для каждого элемента
            System.out.print(a[j] + " "); // Вывод
        System.out.println();
    }

    //--------------------------------------------------------------
    public void selectionSort() {
        int out, in, min;
        for (out = 0; out < nElems - 1; out++) // Внешний цикл
        {
            min = out; // Минимум
            for (in = out + 1; in < nElems; in++) // Внутренний цикл
                if (a[in] < a[min]) // Если значение min больше,
                    min = in; // значит, найден новый минимум
            swap(out, min); // swap them
        }
    }

    //--------------------------------------------------------------
    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
//--------------------------------------------------------------
}

////////////////////////////////////////////////////////////////
class SelectSortApp {
    public static void main(String[] args) {
        int maxSize = 100; // Размер массива
        ArraySel arr; // Ссылка на массив
        arr = new ArraySel(maxSize); // Создание массива
        arr.insert(77); // Вставка 10 элементов
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);
        arr.display(); // Вывод элементов
        arr.selectionSort(); // Сортировка методом выбора
        arr.display(); // Повторный вывод
    }
} // Конец класса SelectSortApp
////////////////////////////////////////////////////////////////
