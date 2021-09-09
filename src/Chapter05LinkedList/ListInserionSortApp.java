package Chapter05LinkedList;

// listInsertionSort.java
// Применение сортированного списка для сортировки массива
// Запуск программы: C>java ListInsertionSortApp
////////////////////////////////////////////////////////////////
class Link7 {
    public long dData; // Данные
    public Link7 next; // Следующий элемент списка

    // -------------------------------------------------------------
    public Link7(long dd) // Конструктор
    {
        dData = dd;
    }
// -------------------------------------------------------------
} // Конец класса Link

////////////////////////////////////////////////////////////////
class SortedList2 {
    private Link7 first; // Ссылка на первый элемент списка

    // -------------------------------------------------------------
    public SortedList2() // Конструктор (без аргументов)
    {
        first = null;
    } // Инициализация списка

    // -------------------------------------------------------------
    public SortedList2(Link7[] linkArr) // Конструктор (аргумент - массив)
    { //
        first = null; // Инициализация списка
        for (int j = 0; j < linkArr.length; j++) // Копирование массива
            insert(linkArr[j]); // в список
    }

    // -------------------------------------------------------------
    public void insert(Link7 k) // Вставка (в порядке сортировки)
    {
        Link7 previous = null; // От начала списка
        Link7 current = first;
        // До конца списка
        while (current != null && k.dData > current.dData) { // или если ключ > current,
            previous = current;
            current = current.next; // Перейти к следующему элементу
        }
        if (previous == null) // В начале списка
            first = k; // first --> k
        else // Не в начале
            previous.next = k; // старое значение prev --> k
        k.next = current; // k --> старое значение current
    }

    // -------------------------------------------------------------
    public Link7 remove() // Возвращает и удаляет первую ссылку
    { // (assumes non-empty list)
        Link7 temp = first; // Сохранение ссылки
        first = first.next; // Удаление первого элемента
        return temp; // Метод возвращает ссылку
    } // на удаленный элемент
// -------------------------------------------------------------
} // Конец класса SortedList2

////////////////////////////////////////////////////////////////
class ListInsertionSortApp {
    public static void main(String[] args) {
        int size = 10;
        // Создание массива
        Link7[] linkArray = new Link7[size];
        for (int j = 0; j < size; j++) // Заполнение массива
        { // Случайные числа
            int n = (int) (java.lang.Math.random() * 99);
            Link7 newLink = new Link7(n); // Создание элемента
            linkArray[j] = newLink; // Сохранение в массиве
        }
        // Вывод содержимого массива
        System.out.print("Unsorted array: ");
        for (int j = 0; j < size; j++)
            System.out.print(linkArray[j].dData + " ");
        System.out.println("");
        // Создание нового списка,
        // инициализированного содержимым массива
        SortedList2 theSortedList2 = new SortedList2(linkArray);
        for (int j = 0; j < size; j++) // links from list to array
            linkArray[j] = theSortedList2.remove();
        // Вывод содержимого массива
        System.out.print("Sorted Array: ");
        for (int j = 0; j < size; j++)
            System.out.print(linkArray[j].dData + " ");
        System.out.println("");
    }
} // Конец класса ListInsertionSortApp
////////////////////////////////////////////////////////////////
