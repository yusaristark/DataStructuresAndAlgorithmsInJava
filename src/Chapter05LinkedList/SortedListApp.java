package Chapter05LinkedList;

// sortedList.java
// Работа с сортированным списком
// Запуск программы: C>java SortedListApp
////////////////////////////////////////////////////////////////
class Link6 {
    public long dData; // Данные
    public Link6 next; // Ссылка на следующий элемент списка

    // -------------------------------------------------------------
    public Link6(long dd) // Конструктор
    {
        dData = dd;
    }

    // -------------------------------------------------------------
    public void displayLink() // Вывод содержимого элемента
    {
        System.out.print(dData + " ");
    }
} // end class Link

class SortedList {
    private Link6 first; // Ссылка на первый элемент списка

    // -------------------------------------------------------------
    public SortedList() // Конструктор
    {
        first = null;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true, если список пуст
    {
        return (first == null);
    }

    // -------------------------------------------------------------
    public void insert(long key) // Вставка в порядке сортировки
    {
        Link6 newLink = new Link6(key); // Создание нового элемента
        Link6 previous = null; // От начала списка
        Link6 current = first;
        // До конца списка
        while (current != null && key > current.dData) { // или если key > current,
            previous = current;
            current = current.next; // Перейти к следующему элементу
        }
        if (previous == null) // В начале списка
            first = newLink; // first --> newLink
        else // Не в начале
            previous.next = newLink; // старое значение prev --> newLink
        newLink.next = current; // newLink --> старое значение current
    }

    // -------------------------------------------------------------
    public Link6 remove() // Удаление первого элемента
    { // (предполагается, что список не пуст)
        Link6 temp = first; // Сохранение ссылки
        first = first.next; // Удаление: first-->ссылка на второй элемент
        return temp; // Метод возвращает ссылку
    } // на удаленный элемент

    // -------------------------------------------------------------
    public void displayList() {
        System.out.print("List (first-->last): ");
        Link6 current = first; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод данных
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }
} // Конец класса SortedList

////////////////////////////////////////////////////////////////
class SortedListApp {
    public static void main(String[] args) { // Создание нового списка
        SortedList theSortedList = new SortedList();
        theSortedList.insert(20); // Вставка двух элементов
        theSortedList.insert(40);
        theSortedList.displayList(); // Вывод содержимого списка
        theSortedList.insert(10); // Вставка трех элементов
        theSortedList.insert(30);
        theSortedList.insert(50);
        theSortedList.displayList(); // Вывод содержимого списка
        theSortedList.remove(); // Удаление элемента
        theSortedList.displayList(); // Вывод содержимого списка
    }
} // Конец класса SortedListApp
////////////////////////////////////////////////////////////////
