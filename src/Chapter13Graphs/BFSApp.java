package Chapter13Graphs;

// bfs.java
// Обход в ширину
// Запуск программы: C>java BFSApp
////////////////////////////////////////////////////////////////
class Queue {
    private final int SIZE = 20;
    private int[] queArray;
    private int front;
    private int rear;

    // -------------------------------------------------------------
    public Queue() // Конструктор
    {
        queArray = new int[SIZE];
        front = 0;
        rear = -1;
    }

    // -------------------------------------------------------------
    public void insert(int j) // Вставка элемента в конец очереди
    {
        if (rear == SIZE - 1)
            rear = -1;
        queArray[++rear] = j;
    }

    // -------------------------------------------------------------
    public int remove() // Извлечение элемента в начале очереди
    {
        int temp = queArray[front++];
        if (front == SIZE)
            front = 0;
        return temp;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true, если очередь пуста
    {
        return (rear + 1 == front || (front + SIZE - 1 == rear));
    }
// -------------------------------------------------------------
} // Конец класса Queue

////////////////////////////////////////////////////////////////
class Vertex2 {
    public char label; // Метка (например, 'A')
    public boolean wasVisited;

    // -------------------------------------------------------------
    public Vertex2(char lab) // Конструктор
    {
        label = lab;
        wasVisited = false;
    }
// -------------------------------------------------------------
} // Конец класса Vertex2

////////////////////////////////////////////////////////////////
class Graph2 {
    private final int MAX_VERTS = 20;
    private Vertex2 vertexList[]; // Список вершин
    private int adjMat[][]; // Матрица смежности
    private int nVerts; // Текущее количество вершин
    private Queue theQueue;

    // ------------------
    public Graph2() // Конструктор
    {
        vertexList = new Vertex2[MAX_VERTS];
        // Матрица смежности
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) // Матрица смежности
            for (int k = 0; k < MAX_VERTS; k++) // заполняется нулями
                adjMat[j][k] = 0;
        theQueue = new Queue();
    }

    // -------------------------------------------------------------
    public void addVertex2(char lab) {
        vertexList[nVerts++] = new Vertex2(lab);
    }

    // -------------------------------------------------------------
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    // -------------------------------------------------------------
    public void displayVertex2(int v) {
        System.out.print(vertexList[v].label);
    }

    // -------------------------------------------------------------
    public void bfs() // Обход в ширину
    { // Алгоритм начинает с вершины 0
        vertexList[0].wasVisited = true; // Пометка
        displayVertex2(0); // Вывод
        theQueue.insert(0); // Вставка в конец очереди
        int v2;
        while (!theQueue.isEmpty()) // Пока очередь не опустеет
        {
            int v1 = theQueue.remove(); // Извлечение вершины в начале очереди
            // Пока остаются непосещенные соседи
            while ((v2 = getAdjUnvisitedVertex2(v1)) != -1) { // Получение вершины
                vertexList[v2].wasVisited = true; // Пометка
                displayVertex2(v2); // Вывод
                theQueue.insert(v2); // Вставка
            }
        }
        // Очередь пуста, обход закончен
        for (int j = 0; j < nVerts; j++) // Сброс флагов
            vertexList[j].wasVisited = false;
    }

    // -------------------------------------------------------------
    // Метод возвращает непосещенную вершину, смежную по отношению к v
    public int getAdjUnvisitedVertex2(int v) {
        for (int j = 0; j < nVerts; j++)
            if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
                return j; // Возвращает первую найденную вершину
        return -1; // Таких вершин нет
    }
// -------------------------------------------------------------
} // Конец класса Graph2

////////////////////////////////////////////////////////////////
class BFSApp {
    public static void main(String[] args) {
        Graph2 theGraph2 = new Graph2();
        theGraph2.addVertex2('A'); // 0 (исходная вершина)
        theGraph2.addVertex2('B'); // 1
        theGraph2.addVertex2('C'); // 2
        theGraph2.addVertex2('D'); // 3
        theGraph2.addVertex2('E'); // 4
        theGraph2.addEdge(0, 1); // AB
        theGraph2.addEdge(1, 2); // BC
        theGraph2.addEdge(0, 3); // AD
        theGraph2.addEdge(3, 4); // DE
        System.out.print("Visits: ");
        theGraph2.bfs(); // Обход в ширину
        System.out.println();
    }
}
////////////////////////////////////////////////////////////////
