package Chapter05LinkedList;

// linkQueue.java
// Реализация очереди на базе двустороннего списка
// Запуск программы: C>java LinkQueueApp
////////////////////////////////////////////////////////////////
class Link5 {
    public long dData; // Данные
    public Link5 next; // Следующий элемент в списке

    // -------------------------------------------------------------
    public Link5(long d) // Конструктор
    {
        dData = d;
    }

    // -------------------------------------------------------------
    public void displayLink() // Вывод содержимого элемента
    {
        System.out.print(dData + " ");
    }
// -------------------------------------------------------------
} // Конец класса Link

////////////////////////////////////////////////////////////////
class FirstLastList2 {
    private Link5 first; // Ссылка на первый элемент
    private Link5 last; // Ссылка на последний элемент

    // -------------------------------------------------------------
    public FirstLastList2() // Конструктор
    {
        first = null; // Список пока не содержит элементов
        last = null;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true, если список пуст
    {
        return first == null;
    }

    // -------------------------------------------------------------
    public void insertLast(long dd) // Вставка элемента в конец списка
    {
        Link5 newLink = new Link5(dd); // Создание нового элемента
        if (isEmpty()) // Если список пуст,
            first = newLink; // first --> newLink
        else
            last.next = newLink; // Старое значение last --> newLink
        last = newLink; // newLink <-- last
    }

    // -------------------------------------------------------------
    public long deleteFirst() // Удаление первого элемента
    { // (предполагается, что список не пуст)
        long temp = first.dData;
        if (first.next == null) // Сохранение ссылки
            last = null; // null <-- last
        first = first.next; // first --> старое значение next
        return temp;
    }

    // -------------------------------------------------------------
    public void displayList() {
        Link5 current = first; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод данных
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }
// -------------------------------------------------------------
} // Конец класса FirstLastList

////////////////////////////////////////////////////////////////
class LinkQueue {
    private FirstLastList2 theList;

    //--------------------------------------------------------------
    public LinkQueue() // Конструктор
    {
        theList = new FirstLastList2();
    } // Создание 2-стороннего списка

    //--------------------------------------------------------------
    public boolean isEmpty() // true, если очередь пуста
    {
        return theList.isEmpty();
    }

    //--------------------------------------------------------------
    public void insert(long j) // Вставка элемента в конец очереди
    {
        theList.insertLast(j);
    }

    //--------------------------------------------------------------
    public long remove() // Удаление элемента в начале очереди
    {
        return theList.deleteFirst();
    }

    //--------------------------------------------------------------
    public void displayQueue() {
        System.out.print("Queue (front-->rear): ");
        theList.displayList();
    }
//--------------------------------------------------------------
} // Конец класса LinkQueue

////////////////////////////////////////////////////////////////
class LinkQueueApp {
    public static void main(String[] args) {
        LinkQueue theQueue = new LinkQueue();
        theQueue.insert(20); // Вставка элементов
        theQueue.insert(40);
        theQueue.displayQueue(); // Вывод содержимого очереди
        theQueue.insert(60); // Вставка элементов
        theQueue.insert(80);
        theQueue.displayQueue(); // Вывод содержимого очереди
        theQueue.remove(); // Удаление элементов
        theQueue.remove();
        theQueue.displayQueue(); // Вывод содержимого очереди
    }
}
////////////////////////////////////////////////////////////////
