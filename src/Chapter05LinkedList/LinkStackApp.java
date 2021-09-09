package Chapter05LinkedList;

// linkStack.java
// Реализация стека на базе связанного списка
// Запуск программы: C>java LinkStackApp
////////////////////////////////////////////////////////////////
class Link4 {
    public long dData; // Данные
    public Link4 next; // Следующий элемент в списке

    // -------------------------------------------------------------
    public Link4(long dd) // Конструктор
    {
        dData = dd;
    }

    // -------------------------------------------------------------
    public void displayLink() // Вывод содержимого элемента
    {
        System.out.print(dData + " ");
    }
} // Конце класса Link

////////////////////////////////////////////////////////////////
class Link4List {
    private Link4 first; // Ссылка на первый элемент в списке

    // -------------------------------------------------------------
    public Link4List() // Конструктор
    {
        first = null;
    } // Список пока не содержит элементов

    // -------------------------------------------------------------
    public boolean isEmpty() // true, если список пуст
    {
        return (first == null);
    }

    // -------------------------------------------------------------
    public void insertFirst(long dd) // Вставка элемента в начало списка
    { // Создание нового элемента
        Link4 newLink = new Link4(dd);
        newLink.next = first; // newLink --> старое значение first
        first = newLink; // first --> newLink
    }

    // -------------------------------------------------------------
    public long deleteFirst() // Удаление первого элемента
    { // (предполагается, что список не пуст)
        Link4 temp = first; // Сохранение ссылки
        first = first.next; // Удаление: first-->ссылка на второй элемент
        return temp.dData; // Метод возвращает данные
    } // удаленного элемента

    // -------------------------------------------------------------
    public void displayList() {
        Link4 current = first; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод данных
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }
// -------------------------------------------------------------
} // Конец класса LinkList

////////////////////////////////////////////////////////////////
class LinkStack {
    private Link4List theList;

    //--------------------------------------------------------------
    public LinkStack() // Конструктор
    {
        theList = new Link4List();
    }

    //--------------------------------------------------------------
    public void push(long j) // Размещение элемента на вершине стека
    {
        theList.insertFirst(j);
    }

    //--------------------------------------------------------------
    public long pop() // Извлечение элемента с вершины стека
    {
        return theList.deleteFirst();
    }

    //--------------------------------------------------------------
    public boolean isEmpty() // true, если стек пуст
    {
        return (theList.isEmpty());
    }

    //--------------------------------------------------------------
    public void displayStack() {
        System.out.print("Stack (top-->bottom): ");
        theList.displayList();
    }
//--------------------------------------------------------------
} // Конец класса LinkStack

////////////////////////////////////////////////////////////////
class LinkStackApp {
    public static void main(String[] args) {
        LinkStack theStack = new LinkStack(); // Создание стека
        theStack.push(20); // Вставка элементов
        theStack.push(40);
        theStack.displayStack(); // Вывод содержимого стека
        theStack.push(60); // Вставка элементов
        theStack.push(80);
        theStack.displayStack(); // Вывод содержимого стека
        theStack.pop(); // Извлечение элементов
        theStack.pop();
        theStack.displayStack(); // Вывод содержимого стека
    }
} // Конец класса LinkStackApp
////////////////////////////////////////////////////////////////
