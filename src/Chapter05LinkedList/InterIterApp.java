package Chapter05LinkedList;

// interIterator.java
// Использование итератора связанного списка
// Запуск программы: C>java InterIterApp

import java.io.*; // Для ввода/вывода

////////////////////////////////////////////////////////////////
class Link9 {
    public long dData; // Данные
    public Link9 next; // Следующий элемент в списке

    // -------------------------------------------------------------
    public Link9(long dd) // Конструктор
    {
        dData = dd;
    }

    // -------------------------------------------------------------
    public void displayLink() // Вывод содержимого элемента
    {
        System.out.print(dData + " ");
    }
} // Конец класса Link

////////////////////////////////////////////////////////////////
class LinkList3 {
    private Link9 first; // Ссылка на первый элемент в списке

    // -------------------------------------------------------------
    public LinkList3() // Конструктор
    {
        first = null;
    } // Список пока не содержит элементов

    // -------------------------------------------------------------
    public Link9 getFirst() // Получение первого элемента
    {
        return first;
    }

    // -------------------------------------------------------------
    public void setFirst(Link9 f) // Присваивание нового значения first
    {
        first = f;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true, если список пуст
    {
        return first == null;
    }

    // -------------------------------------------------------------
    public ListIterator getIterator() // Получение итератора
    {
        return new ListIterator(this); // Инициализация списком this
    }

    // -------------------------------------------------------------
    public void displayList() {
        Link9 current = first; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод текущего элемента
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }
// -------------------------------------------------------------
} // Конец класса LinkList3

////////////////////////////////////////////////////////////////
class ListIterator {
    private Link9 current; // Текущий элемент списка
    private Link9 previous; // Предыдущий элемент списка
    private LinkList3 ourList; // Связанный список

    //--------------------------------------------------------------
    public ListIterator(LinkList3 list) // Конструктор
    {
        ourList = list;
        reset();
    }

    //--------------------------------------------------------------
    public void reset() // Возврат к 'first'
    {
        current = ourList.getFirst();
        previous = null;
    }

    //--------------------------------------------------------------
    public boolean atEnd() // true, если текущим является
    {
        return (current.next == null);
    } // последний элемент

    //--------------------------------------------------------------
    public void nextLink() // Переход к следующему элементу
    {
        previous = current;
        current = current.next;
    }

    //--------------------------------------------------------------
    public Link9 getCurrent() // Получение текущего элемента
    {
        return current;
    }

    //--------------------------------------------------------------
    public void insertAfter(long dd) // Вставка после
    { // текущего элемента
        Link9 newLink = new Link9(dd);
        if (ourList.isEmpty()) // Пустой список
        {
            ourList.setFirst(newLink);
            current = newLink;
        } else // Список не пуст
        {
            newLink.next = current.next;
            current.next = newLink;
            nextLink(); // Переход к новому элементу
        }
    }

    //--------------------------------------------------------------
    public void insertBefore(long dd) // Вставка перед
    { // текущим элементом
        Link9 newLink = new Link9(dd);
        if (previous == null) // В начале списка
        { // (или пустой список)
            newLink.next = ourList.getFirst();
            ourList.setFirst(newLink);
            reset();
        } else // Не в начале списка
        {
            newLink.next = previous.next;
            previous.next = newLink;
            current = newLink;
        }
    }

    //--------------------------------------------------------------
    public long deleteCurrent() // Удаление текущего элемента
    {
        long value = current.dData;
        if (previous == null) // Если в начале списка
        {
            ourList.setFirst(current.next);
            reset();
        } else // Не в начале списка
        {
            previous.next = current.next;
            if (atEnd())
                reset();
            else
                current = current.next;
        }
        return value;
    }
//--------------------------------------------------------------
} // Конец класса ListIterator

////////////////////////////////////////////////////////////////
class InterIterApp {
    public static void main(String[] args) throws IOException {
        LinkList3 theList = new LinkList3(); // Создание списка
        ListIterator iter1 = theList.getIterator(); // Создание итератора
        long value;
        iter1.insertAfter(20); // Вставка элементов
        iter1.insertAfter(40);
        iter1.insertAfter(80);
        iter1.insertBefore(60);
        while (true) {
            System.out.print("Enter first letter of show, reset, ");
            System.out.print("next, get, before, after, delete: ");
            System.out.flush();
            int choice = getChar(); // Ввод команды
            switch (choice) {
                case 's': // Вывод списка
                    if (!theList.isEmpty())
                        theList.displayList();
                    else
                        System.out.println("List is empty");
                    break;
                case 'r': // Возврат к первому элементу
                    iter1.reset();
                    break;
                case 'n': // Переход к следующему элементу
                    if (!theList.isEmpty() && !iter1.atEnd())
                        iter1.nextLink();
                    else
                        System.out.println("Can't go to next link");
                    break;
                case 'g': // Получение текущего элемента
                    if (!theList.isEmpty()) {
                        value = iter1.getCurrent().dData;
                        System.out.println("Returned " + value);
                    } else
                        System.out.println("List is empty");
                    break;
                case 'b': // Вставка перед текущим элементом
                    System.out.print("Enter value to insert: ");
                    System.out.flush();
                    value = getInt();
                    iter1.insertBefore(value);
                    break;
                case 'a': // Вставка после текущего элемента
                    System.out.print("Enter value to insert: ");
                    System.out.flush();
                    value = getInt();
                    iter1.insertAfter(value);
                    break;
                case 'd': // Удаление текущего элемента
                    if (!theList.isEmpty()) {
                        value = iter1.deleteCurrent();
                        System.out.println("Deleted " + value);
                    } else
                        System.out.println("Can't delete");
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

    //--------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    //-------------------------------------------------------------
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
//-------------------------------------------------------------
} // Конец класса InterIterApp
////////////////////////////////////////////////////////////////

