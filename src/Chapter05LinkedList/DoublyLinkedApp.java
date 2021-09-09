package Chapter05LinkedList;

import java.util.ArrayList;
import java.util.Arrays;

// doublyLinked.java
// Двусвязный список
// Запуск программы: C>java DoublyLinkedApp
////////////////////////////////////////////////////////////////
class Link8 {
    public long dData; // Данные
    public Link8 next; // Следующий элемент в списке
    public Link8 previous; // Предыдущий элемент в списке

    // -------------------------------------------------------------
    public Link8(long d) // Конструктор
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
class DoublyLinkedList {
    private Link8 first; // Ссылка на первый элемент списка
    private Link8 last; // Ссылка на последний элемент списка

    // -------------------------------------------------------------
    public DoublyLinkedList() // Конструктор
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
    public void insertFirst(long dd) // Вставка элемента в начало списка
    {
        Link8 newLink = new Link8(dd); // Создание нового элемента
        if (isEmpty()) // Если список не содержит элементов,
            last = newLink; // newLink <-- last
        else
            first.previous = newLink; // newLink <-- старое значение first
        newLink.next = first; // newLink --> старое значение first
        first = newLink; // first --> newLink
    }

    // -------------------------------------------------------------
    public void insertLast(long dd) // элемент в конец списка
    {
        Link8 newLink = new Link8(dd); // Создание нового элемента
        if (isEmpty()) // Если список не содержит элементов,
            first = newLink; // first --> newLink
        else {
            last.next = newLink; // старое значение last --> newLink
            newLink.previous = last; // старое значение last <-- newLink
        }
        last = newLink; // newLink <-- last
    }

    // -------------------------------------------------------------
    public Link8 deleteFirst() // Удаление первого элемента
    { // (предполагается, что список не пуст)
        Link8 temp = first;
        if (first.next == null) // Если только один элемент
            last = null; // null <-- last
        else
            first.next.previous = null; // null <-- старое значение next
        first = first.next; // first --> старое значение next
        return temp;
    }

    // -------------------------------------------------------------
    public Link8 deleteLast() // Удаление последнего элемента
    { // (предполагается, что список не пуст)
        Link8 temp = last;
        if (first.next == null) // Если только один элемент
            first = null; // first --> null
        else
            last.previous.next = null; // старое значение previous --> null
        last = last.previous; // старое значение previous <-- last
        return temp;
    }

    // -------------------------------------------------------------
    // Вставка dd в позицию после key
    public boolean insertAfter(long key, long dd) { // (предполагается, что список не пуст)
        Link8 current = first; // От начала списка
        while (current.dData != key) // Пока не будет найдено совпадение
        {
            current = current.next; // Переход к следующему элементу
            if (current == null)
                return false; // Ключ не найден
        }
        Link8 newLink = new Link8(dd); // Создание нового элемента
        if (current == last) // Для последнего элемента списка
        {
            newLink.next = null; // newLink --> null
            last = newLink; // newLink <-- last
        } else // Не последний элемент
        {
            newLink.next = current.next; // newLink --> старое значение next
            // newLink <-- старое значение next
            current.next.previous = newLink;
        }
        newLink.previous = current; // старое значение current <-- newLink
        current.next = newLink; // старое значение current --> newLink
        return true; // Ключ найден, вставка выполнена
    }

    // -------------------------------------------------------------
    public Link8 deleteKey(long key) // Удаление элемента с заданным ключом
    { // (предполагается, что список не пуст)
        Link8 current = first; // От начала списка
        while (current.dData != key) // Пока не будет найдено совпадение
        {
            current = current.next; // Переход к следующему элементу
            if (current == null)
                return null; // Ключ не найден
        }
        if (current == first) // Ключ найден; это первый элемент?
            first = current.next; // first --> старое значение next
        else // Не первый элемент
            // старое значение previous --> старое значение next
            current.previous.next = current.next;
        if (current == last) // Последний элемент?
            last = current.previous; // старое значение previous <-- last
        else // Не последний элемент
            // Старое значение previous <-- старое значение next
            current.next.previous = current.previous;
        return current; // Возвращение удаленного элемента
    }

    // -------------------------------------------------------------
    public void displayForward() {
        System.out.print("List (first-->last): ");
        Link8 current = first; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод данных
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }

    // -------------------------------------------------------------
    public void displayBackward() {
        System.out.print("List (last-->first): ");
        Link8 current = last; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод данных
            current = current.previous; // Переход к следующему элементу
        }
        System.out.println("");
    }
// -------------------------------------------------------------
} // Конец класса DoublyLinkedList

////////////////////////////////////////////////////////////////
class DoublyLinkedApp {
    public static void main(String[] args) { // Создание нового списка
        DoublyLinkedList theList = new DoublyLinkedList();
        theList.insertFirst(22); // Вставка в начале
        theList.insertFirst(44);
        theList.insertFirst(66);
        theList.insertLast(11); // Вставка в конце
        theList.insertLast(33);
        theList.insertLast(55);
        theList.displayForward(); // Вывод в прямом направлении
        theList.displayBackward(); // Вывод в обратном направлении
        theList.deleteFirst(); // Удаление первого элемента
        theList.deleteLast(); // Удаление последнего элемента
        theList.deleteKey(11); // Удаление элемента с ключом 11
        theList.displayForward(); // Вывод в прямом направлении
        theList.insertAfter(22, 77); // Вставка 77 после 22
        theList.insertAfter(33, 88); // Вставка 88 после 33
        theList.displayForward(); // Вывод в прямом направлении
    }
} // Конец класса DoublyLinkedApp
////////////////////////////////////////////////////////////////

