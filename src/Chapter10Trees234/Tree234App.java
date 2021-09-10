package Chapter10Trees234;

// tree234.java
// Работа с 234-деревом
// Запуск программы: C>java Tree234App

import java.io.*;

////////////////////////////////////////////////////////////////
class DataItem {
    public long dData; // Один объект данных

    //--------------------------------------------------------------
    public DataItem(long dd) // Конструктор
    {
        dData = dd;
    }

    //--------------------------------------------------------------
    public void displayItem() // Вывод элемента в формате "/27"
    {
        System.out.print("/" + dData);
    }
//--------------------------------------------------------------
} // Конец класса DataItem

////////////////////////////////////////////////////////////////
class Node {
    private static final int ORDER = 4;
    private int numItems;
    private Node parent;
    private Node childArray[] = new Node[ORDER];
    private DataItem itemArray[] = new DataItem[ORDER - 1];

    // -------------------------------------------------------------
    // Связывание узла с потомком
    public void connectChild(int childNum, Node child) {
        childArray[childNum] = child;
        if (child != null)
            child.parent = this;
    }

    // -------------------------------------------------------------
    // Метод отсоединяет потомка от узла и возвращает его
    public Node disconnectChild(int childNum) {
        Node tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    // -------------------------------------------------------------
    public Node getChild(int childNum) {
        return childArray[childNum];
    }

    // -------------------------------------------------------------
    public Node getParent() {
        return parent;
    }

    // -------------------------------------------------------------
    public boolean isLeaf() {
        return (childArray[0] == null) ? true : false;
    }

    // -------------------------------------------------------------
    public int getNumItems() {
        return numItems;
    }

    // -------------------------------------------------------------
    public DataItem getItem(int index) // Получение объекта DataItem
    {
        return itemArray[index];
    } // с заданным индексом

    // -------------------------------------------------------------
    public boolean isFull() {
        return (numItems == ORDER - 1) ? true : false;
    }

    // -------------------------------------------------------------
    public int findItem(long key) // Определение индекса элемента
    { // (в пределах узла)
        for (int j = 0; j < ORDER - 1; j++) // Если элемент найден
        { // В противном случае
            if (itemArray[j] == null) // Вернуть -1
                break;
            else if (itemArray[j].dData == key)
                return j;
        }
        return -1;
    }

    // -------------------------------------------------------------
    public int insertItem(DataItem newItem) {
        // Предполагается, что узел не пуст
        numItems++; // Добавление нового элемента
        long newKey = newItem.dData; // Ключ нового элемента
        for (int j = ORDER - 2; j >= 0; j--) // Начиная справа,
        { // проверяем элементы
            if (itemArray[j] == null) // Если ячейка пуста,
                continue; // перейти на одну ячейку влево
            else // Если нет,
            { // получить ее ключ
                long itsKey = itemArray[j].dData;
                if (newKey < itsKey) // Если новый ключ больше
                    itemArray[j + 1] = itemArray[j]; // Сдвинуть вправо
                else {
                    itemArray[j + 1] = newItem; // Вставка нового элемента
                    return j + 1; // Метод возвращает индекс
                } // нового элемента
            }
        } // Все элементы сдвинуты,
        itemArray[0] = newItem; // вставка нового элемента
        return 0;
    }

    // -------------------------------------------------------------
    public DataItem removeItem() // Удаление наибольшего
    { // элемента
        // Предполагается, что узел не пуст
        DataItem temp = itemArray[numItems - 1]; // Сохранение элемента
        itemArray[numItems - 1] = null; // Отсоединение
        numItems--; // На один элемент меньше
        return temp; // Метод возвращает
    } // удаленный элемент

    // -------------------------------------------------------------
    public void displayNode() // Формат "/24/56/74/"
    {
        for (int j = 0; j < numItems; j++)
            itemArray[j].displayItem(); // "/56"
        System.out.println("/"); // Завершающий символ "/"
    }
// -------------------------------------------------------------
} // Конец класса Node

////////////////////////////////////////////////////////////////
class Tree234 {
    private Node root = new Node(); // Создание корневого узла

    // -------------------------------------------------------------
    public int find(long key) {
        Node curNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1)
                return childNumber; // Узел найден
            else if (curNode.isLeaf())
                return -1; // Узел не найден
            else // Искать глубже
                curNode = getNextChild(curNode, key);
        }
    }

    // -------------------------------------------------------------
    // Вставка элемента данных
    public void insert(long dValue) {
        Node curNode = root;
        DataItem tempItem = new DataItem(dValue);
        while (true) {
            if (curNode.isFull()) // Если узел полон,
            {
                split(curNode); // он разбивается.
                curNode = curNode.getParent(); // Возврат уровнем выше
                // Поиск
                curNode = getNextChild(curNode, dValue);
            } else if (curNode.isLeaf()) // Если узел листовой,
                break; // переход к вставке
                // Узел не полный и не листовой; спуститься уровнем ниже
            else
                curNode = getNextChild(curNode, dValue);
        }
        curNode.insertItem(tempItem); // Вставка нового объекта DataItem
    }

    // -------------------------------------------------------------
    public void split(Node thisNode) // Разбиение узла
    {
        // Предполагается, что узел полон
        DataItem itemB, itemC;
        Node parent, child2, child3;
        int itemIndex;
        itemC = thisNode.removeItem(); // Удаление элементов из
        itemB = thisNode.removeItem(); // текущего узла
        child2 = thisNode.disconnectChild(2); // Отсоединение потомков
        child3 = thisNode.disconnectChild(3); // от текущего узла
        Node newRight = new Node(); // Создание нового узла
        if (thisNode == root) // Если узел является корнем,
        {
            root = new Node(); // Создание нового корня
            parent = root; // Корень становится родителем
            root.connectChild(0, thisNode); // Связывание с родителем
        } else // Текущий узел не является корнем
            parent = thisNode.getParent(); // Получение родителя
        // Разбираемся с родителем
        itemIndex = parent.insertItem(itemB); // B вставляется в родителя
        int n = parent.getNumItems(); // Всего элементов?
        for (int j = n - 1; j > itemIndex; j--) // Перемещение связей
        { // родителя
            Node temp = parent.disconnectChild(j); // На одного потомка
            parent.connectChild(j + 1, temp); // вправо
        }
        // Связывание newRight с родителем
        parent.connectChild(itemIndex + 1, newRight);
        // Разбираемся с узлом newRight
        newRight.insertItem(itemC); // Элемент C в newRight
        newRight.connectChild(0, child2); // Связывание 0 и 1
        newRight.connectChild(1, child3); // с newRight
    }

    // -------------------------------------------------------------
    // Получение соответствующего потомка при поиске значения
    public Node getNextChild(Node theNode, long theValue) {
        int j;
        // Предполагается, что узел не пуст, не полон и не является листом
        int numItems = theNode.getNumItems();
        for (j = 0; j < numItems; j++) // Для каждого элемента в узле
        { // Наше значение меньше?
            if (theValue < theNode.getItem(j).dData)
                return theNode.getChild(j); // Вернуть левого потомка
        } // Наше значение больше,
        return theNode.getChild(j); // Вернуть правого потомка
    }

    // -------------------------------------------------------------
    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    // -------------------------------------------------------------
    private void recDisplayTree(Node thisNode, int level,
                                int childNumber) {
        System.out.print("level=" + level + " child=" + childNumber + " ");
        thisNode.displayNode(); // Вывод содержимого узла
        // Рекурсивный вызов для каждого потомка текущего узла
        int numItems = thisNode.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            Node nextNode = thisNode.getChild(j);
            if (nextNode != null)
                recDisplayTree(nextNode, level + 1, j);
            else
                return;
        }
    }
// -------------------------------------------------------------
} // Конец класса Tree234

////////////////////////////////////////////////////////////////
class Tree234App {
    public static void main(String[] args) throws IOException {
        long value;
        Tree234 theTree = new Tree234();
        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);
        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    theTree.insert(value);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = getInt();
                    int found = theTree.find(value);
                    if (found != -1)
                        System.out.println("Found " + value);
                    else
                        System.out.println("Could not find " + value);
                    break;
                default:
                    System.out.print("Invalid entry\n");
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

    //--------------------------------------------------------------
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
} // Конец класса Tree234App
////////////////////////////////////////////////////////////////
