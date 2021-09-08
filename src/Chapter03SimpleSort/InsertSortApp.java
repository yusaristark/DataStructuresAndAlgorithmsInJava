package Chapter03SimpleSort;

// insertSort.java
// Сортировка методом вставки
// Запуск программы: C>java InsertSortApp
//--------------------------------------------------------------
class ArrayIns
{
    private long[] a; // Ссылка на массив a
    private int nElems; // Количество элементов данных
    //--------------------------------------------------------------
    public ArrayIns(int max) // Конструктор
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
    public void insertionSort()
    {
        int in, out;
        int copyCounter = 0;
        int compareCounter = 0;
        for(out=1; out<nElems; out++) // out - разделительный маркер
        {
            long temp = a[out]; // Скопировать помеченный элемент
            in = out; // Начать перемещения с out
            while(in>0) // Пока не найден меньший элемент
            {
                if (a[in - 1] >= temp) {
                    ++compareCounter;
                } else {
                    ++compareCounter;
                    break;
                }
                a[in] = a[in-1]; // Сдвинуть элемент вправо
                ++copyCounter;
                --in; // Перейти на одну позицию влево
            }
            a[in] = temp; // Вставить помеченный элемент
        }
        System.out.println("Copies: " + copyCounter + " Compares: " + compareCounter);
    }

    public long median() {
        insertionSort();
        return a[nElems / 2];
    }

    public void noDups() {
        insertionSort();
        long[] newArray = new long[nElems];
        int j = 0;
        for (long l : a) {
            if (!contains(newArray, l)) {
                newArray[j] = l;
                ++j;
            }
        }
        a = newArray;
        insertionSort();
    }

    private boolean contains(long[] array, long value) {
        for (long l : array) {
            if (l == value) {
                return true;
            }
        }
        return false;
    }
//--------------------------------------------------------------
} // Конец класса ArrayIns
////////////////////////////////////////////////////////////////
class InsertSortApp
{
    public static void main(String[] args)
    {
        int maxSize = 100; // Размер массива
        ArrayIns arr; // Ссылка на массив
        arr = new ArrayIns(maxSize); // Создание массива
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
        arr.insert(22);
        arr.insert(31);
        arr.display(); // Вывод элементов
        arr.insertionSort(); // Сортировка методом вставки
        arr.display(); // Повторный вывод
        System.out.println(arr.median());
        arr.noDups();
        arr.display();
    }
}
////////////////////////////////////////////////////////////////
