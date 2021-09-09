package Chapter05LinkedList;

// linkList.java
// Работа со связанным списком
// Запуск программы: C>java LinkListApp
////////////////////////////////////////////////////////////////
class Link {
    public int iData; // Данные (ключ)
    public double dData; // Данные
    public Link next; // Следующий элемент в списке

    // -------------------------------------------------------------
    public Link(int id, double dd) // Конструктор
    {
        iData = id; // Инициализация данных
        dData = dd; // ('next' автоматически
    } // присваивается null)

    // -------------------------------------------------------------
    public void displayLink() // Вывод содержимого элемента
    {
        System.out.print("{" + iData + ", " + dData + "} ");
    }
} // Конец класса Link

////////////////////////////////////////////////////////////////
class LinkList {
    private Link first; // Ссылка на первый элемент списка

    // -------------------------------------------------------------
    public LinkList() // Конструктор
    {
        first = null; // Список пока не содержит элементов
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true, если список пуст
    {
        return (first == null);
    }

    // -------------------------------------------------------------
// Вставка элемента в начало списка
    public void insertFirst(int id, double dd) { // Создание нового элемента
        Link newLink = new Link(id, dd);
        newLink.next = first; // newLink --> старое значение first
        first = newLink; // first --> newLink
    }

    // -------------------------------------------------------------
    public Link deleteFirst() // Удаление первого элемента
    { // (предполагается, что список не пуст)
        Link temp = first; // Сохранение ссылки
        first = first.next; // Удаление: first-->ссылка на второй элемент
        return temp; // Метод возвращает ссылку
    } // на удаленный элемент

    // -------------------------------------------------------------
    public void displayList() {
        System.out.print("List (first-->last): ");
        Link current = first; // От начала списка
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
class LinkListApp {
    public static void main(String[] args) {
        LinkList theList = new LinkList(); // Создание нового списка
        theList.insertFirst(22, 2.99); // Вставка четырех элементов
        theList.insertFirst(44, 4.99);
        theList.insertFirst(66, 6.99);
        theList.insertFirst(88, 8.99);
        theList.displayList(); // Вывод содержимого списка
        while (!theList.isEmpty()) // Пока остаются элементы,
        {
            Link aLink = theList.deleteFirst(); // Удаление элемента
            System.out.print("Deleted "); // Вывод удаленного элемента
            aLink.displayLink();
            System.out.println("");
        }
        theList.displayList(); // Вывод содержимого списка
    }
} // Конец класса LinkListApp
////////////////////////////////////////////////////////////////
