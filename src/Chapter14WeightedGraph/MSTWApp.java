package Chapter14WeightedGraph;

// mstw.java
// Построение минимального остовного дерева для взвешенного графа
// Запуск программы: C>java MSTWApp
////////////////////////////////////////////////////////////////
class Edge {
    public int srcVert; // Индекс начальной вершины ребра
    public int destVert; // Индекс конечной вершины ребра
    public int distance; // Расстояние от начала до конца

    // -------------------------------------------------------------
    public Edge(int sv, int dv, int d) // Конструктор
    {
        srcVert = sv;
        destVert = dv;
        distance = d;
    }
// -------------------------------------------------------------
} // Конец класса Edge

////////////////////////////////////////////////////////////////
class PriorityQ {
    // Массив отсортирован по убыванию от ячейки 0 до size-1
    private final int SIZE = 20;
    private Edge[] queArray;
    private int size;

    // -------------------------------------------------------------
    public PriorityQ() // Конструктор
    {
        queArray = new Edge[SIZE];
        size = 0;
    }

    // -------------------------------------------------------------
    public void insert(Edge item) // Вставка элемента в порядке сортировки
    {
        int j;
        for (j = 0; j < size; j++) // Поиск места для вставки
            if (item.distance >= queArray[j].distance)
                break;
        for (int k = size - 1; k >= j; k--) // Перемещение элементов вверх
            queArray[k + 1] = queArray[k];
        queArray[j] = item; // Вставка элемента
        size++;
    }

    // -------------------------------------------------------------
    public Edge removeMin() // Извлечение наименьшего элемента
    {
        return queArray[--size];
    }

    // -------------------------------------------------------------
    public void removeN(int n) // Удаление элемента в позиции N
    {
        for (int j = n; j < size - 1; j++) // Перемещение элементов вниз
            queArray[j] = queArray[j + 1];
        size--;
    }

    // -------------------------------------------------------------
    public Edge peekMin() // Чтение наименьшего элемента
    {
        return queArray[size - 1];
    }

    // -------------------------------------------------------------
    public int size() // Получение количества элементов
    {
        return size;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true, если очередь пуста
    {
        return (size == 0);
    }

    // -------------------------------------------------------------
    public Edge peekN(int n) // Чтение элемента в позиции N
    {
        return queArray[n];
    }

    // -------------------------------------------------------------
    public int find(int findDex) // Поиск элемента с заданным
    { // значением destVert
        for (int j = 0; j < size; j++)
            if (queArray[j].destVert == findDex)
                return j;
        return -1;
    }
// -------------------------------------------------------------
} // Конец класса PriorityQ

////////////////////////////////////////////////////////////////
class Vertex {
    public char label; // Метка (например, 'A')
    public boolean isInTree;

    // -------------------------------------------------------------
    public Vertex(char lab) // Конструктор
    {
        label = lab;
        isInTree = false;
    }
// -------------------------------------------------------------
} // Конец класса Vertex

////////////////////////////////////////////////////////////////
class Graph {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex vertexList[]; // Список вершин
    private int adjMat[][]; // Матрица смежности
    private int nVerts; // Текущее количество вершин
    private int currentVert;
    private PriorityQ thePQ;
    private int nTree; // Количество вершин в дереве

    // -------------------------------------------------------------
    public Graph() // Конструктор
    {
        vertexList = new Vertex[MAX_VERTS];
        // Матрица смежности
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) // Матрица смежности
            for (int k = 0; k < MAX_VERTS; k++) // заполняется нулями
                adjMat[j][k] = INFINITY;
        thePQ = new PriorityQ();
    }

    // -------------------------------------------------------------
    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    // -------------------------------------------------------------
    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }

    // -------------------------------------------------------------
    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    // -------------------------------------------------------------
    public void mstw() // Построение минимального остовного дерева
    {
        currentVert = 0; // Начиная с ячейки 0
        while (nTree < nVerts - 1) // Пока не все вершины включены в дерево
        { // Включение currentVert в дерево
            vertexList[currentVert].isInTree = true;
            nTree++;
            // Вставка в приоритетную очередь ребер, смежных с currentVert
            for (int j = 0; j < nVerts; j++) // Для каждой вершины
            {
                if (j == currentVert) // Пропустить, если текущая вершина
                    continue;
                if (vertexList[j].isInTree) // Пропустить, если уже в дереве
                    continue;
                int distance = adjMat[currentVert][j];
                if (distance == INFINITY) // Пропустить, если нет ребер
                    continue;
                putInPQ(j, distance); // Поместить в приоритетную очередь
            }
            if (thePQ.size() == 0) // Очередь не содержит вершин?
            {
                System.out.println(" GRAPH NOT CONNECTED");
                return;
            }
            // Удаление ребра с минимальным расстоянием из очереди
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;
            // Вывод ребра от начальной до текущей вершины
            System.out.print(vertexList[sourceVert].label);
            System.out.print(vertexList[currentVert].label);
            System.out.print(" ");
        }
        // Минимальное остовное дерево построено
        for (int j = 0; j < nVerts; j++) // Снятие пометки с вершин
            vertexList[j].isInTree = false;
    }

    // -------------------------------------------------------------
    public void putInPQ(int newVert, int newDist) {
        // Существует ли другое ребро с той же конечной вершиной?
        int queueIndex = thePQ.find(newVert); // Получение индекса
        if (queueIndex != -1) // Если ребро существует,
        { // получить его
            Edge tempEdge = thePQ.peekN(queueIndex);
            int oldDist = tempEdge.distance;
            if (oldDist > newDist) // Если новое ребро короче,
            {
                thePQ.removeN(queueIndex); // удалить старое ребро
                Edge theEdge = new Edge(currentVert, newVert, newDist);
                thePQ.insert(theEdge); // Вставка нового ребра
            }
            // Иначе ничего не делается; оставляем старую вершину
        } else // Ребра с той же конечной вершиной не существует
        { // Вставка нового ребра
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }
// -------------------------------------------------------------
} // Конец класса Graph

////////////////////////////////////////////////////////////////
class MSTWApp {
    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A'); // 0 (исходная вершина)
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E'); // 4
        theGraph.addVertex('F'); // 5
        theGraph.addEdge(0, 1, 6); // AB 6
        theGraph.addEdge(0, 3, 4); // AD 4
        theGraph.addEdge(1, 2, 10); // BC 10
        theGraph.addEdge(1, 3, 7); // BD 7
        theGraph.addEdge(1, 4, 7); // BE 7
        theGraph.addEdge(2, 3, 8); // CD 8
        theGraph.addEdge(2, 4, 5); // CE 5
        theGraph.addEdge(2, 5, 6); // CF 6
        theGraph.addEdge(3, 4, 12); // DE 12
        theGraph.addEdge(4, 5, 7); // EF 7
        System.out.print("Minimum spanning tree: ");
        theGraph.mstw(); // Минимальное остовное дерево
        System.out.println();
    }
} // Конец класса MSTWApp
///////////////////////////////////////////////////////////////
