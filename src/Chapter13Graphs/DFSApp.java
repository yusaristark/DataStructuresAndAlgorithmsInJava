package Chapter13Graphs;

// dfs.java
// Обход в глубину
// Запуск программы: C>java DFSApp
////////////////////////////////////////////////////////////////
class StackX {
    private final int SIZE = 20;
    private int[] st;
    private int top;

    // -----------------------------------------------------------
    public StackX() // Конструктор
    {
        st = new int[SIZE]; // Создание массива
        top = -1;
    }

    // -----------------------------------------------------------
    public void push(int j) // Размещение элемента в стеке
    {
        st[++top] = j;
    }

    // -----------------------------------------------------------
    public int pop() // Извлечение элемента из стека
    {
        return st[top--];
    }

    // ------------------------------------------------------------
    public int peek() // Чтение с вершины стека
    {
        return st[top];
    }

    // ------------------------------------------------------------
    public boolean isEmpty() // true, если стек пуст
    {
        return (top == -1);
    }
// ------------------------------------------------------------
} // Конец класса StackX

////////////////////////////////////////////////////////////////
class Vertex {
    public char label; // метка (например, 'A')
    public boolean wasVisited;

    // ------------------------------------------------------------
    public Vertex(char lab) // Конструктор
    {
        label = lab;
        wasVisited = false;
    }
// ------------------------------------------------------------
} // Конец класса Vertex

////////////////////////////////////////////////////////////////
class Graph {
    private final int MAX_VERTS = 20;
    private Vertex vertexList[]; // Список вершин
    private int adjMat[][]; // Матрица смежности
    private int nVerts; // Текущее количество вершин
    private StackX theStack;

    // -----------------------------------------------------------
    public Graph() // Конструктор
    {
        vertexList = new Vertex[MAX_VERTS];
        // Матрица смежности
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) // Матрица смежности
            for (int k = 0; k < MAX_VERTS; k++) // заполняется нулями
                adjMat[j][k] = 0;
        theStack = new StackX();
    }

    // -----------------------------------------------------------
    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    // -----------------------------------------------------------
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    // ------------------------------------------------------------
    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    // ------------------------------------------------------------
    public void dfs() // Обход в глубину
    { // Алгоритм начинает с вершины 0
        vertexList[0].wasVisited = true; // Пометка
        displayVertex(0); // Вывод
        theStack.push(0); // Занесение в стек
        while (!theStack.isEmpty()) // Пока стек не опустеет
        {
            // Получение непосещенной вершины, смежной к текущей
            int v = getAdjUnvisitedVertex(theStack.peek());
            if (v == -1) // Если такой вершины нет,
                theStack.pop(); // элемент извлекается из стека
            else // Если вершина найдена
            {
                vertexList[v].wasVisited = true; // Пометка
                displayVertex(v); // Вывод
                theStack.push(v); // Занесение в стек
            }
        }
        // Стек пуст, работа закончена
        for (int j = 0; j < nVerts; j++) // Сброс флагов
            vertexList[j].wasVisited = false;
    }

    // ------------------------------------------------------------
    // Метод возвращает непосещенную вершину, смежную по отношению к v
    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++)
            if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
                return j; // Возвращает первую найденную вершину
        return -1; // Таких вершин нет
    }
    // ------------------------------------------------------------
} // Конец класса Graph

////////////////////////////////////////////////////////////////
class DFSApp {
    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A'); // 0 (исходная вершина)
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E'); // 4
        theGraph.addEdge(0, 1); // AB
        theGraph.addEdge(1, 2); // BC
        theGraph.addEdge(0, 3); // AD
        theGraph.addEdge(3, 4); // DE
        System.out.print("Visits: ");
        theGraph.dfs(); // Обход в глубину
        System.out.println();
    }
} // Конец класса DFSApp
////////////////////////////////////////////////////////////////
