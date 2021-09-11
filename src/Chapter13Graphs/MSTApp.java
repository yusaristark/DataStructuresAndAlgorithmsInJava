package Chapter13Graphs;

// mst.java
// Построение минимального остовного дерева
// Запуск программы: C>java MSTApp
////////////////////////////////////////////////////////////////
class StackX3 {
    private final int SIZE = 20;
    private int[] st;
    private int top;

    // -------------------------------------------------------------
    public StackX3() // Конструктор
    {
        st = new int[SIZE]; // Создание массива
        top = -1;
    }

    // -------------------------------------------------------------
    public void push(int j) // Размещение элемента в стеке
    {
        st[++top] = j;
    }

    // -------------------------------------------------------------
    public int pop() // Извлечение элемента из стека
    {
        return st[top--];
    }

    // -------------------------------------------------------------
    public int peek() // Чтение с вершины стека
    {
        return st[top];
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true, если стек пуст
    {
        return (top == -1);
    }
// -------------------------------------------------------------
} // Конец класса StackX3

////////////////////////////////////////////////////////////////
class Vertex3 {
    public char label; // Метка (например, 'A')
    public boolean wasVisited;

    // -------------------------------------------------------------
    public Vertex3(char lab) // Конструктор
    {
        label = lab;
        wasVisited = false;
    }
// -------------------------------------------------------------
} // Конец класса Vertex3

////////////////////////////////////////////////////////////////
class Graph3 {
    private final int MAX_VERTS = 20;
    private Vertex3 vertexList[]; // Список вершин
    private int adjMat[][]; // Матрица смежности
    private int nVerts; // Текущее количество вершин
    private StackX3 theStack;

    // -------------------------------------------------------------
    public Graph3() // Конструктор
    {
        vertexList = new Vertex3[MAX_VERTS];
        // Матрица смежности
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) // Матрица смежности
            for (int k = 0; k < MAX_VERTS; k++) // заполняется нулями
                adjMat[j][k] = 0;
        theStack = new StackX3();
    }

    // -------------------------------------------------------------
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void addVertex3(char lab) {
        vertexList[nVerts++] = new Vertex3(lab);
    }

    // -------------------------------------------------------------
    public void displayVertex3(int v) {
        System.out.print(vertexList[v].label);
    }

    // -------------------------------------------------------------
    public void mst() // Построение минимального остовного дерева
    { //
        vertexList[0].wasVisited = true; // Пометка
        theStack.push(0); // Занесение в стек
        while (!theStack.isEmpty()) // Пока стек не опустеет
        { // Извлечение элемента из стека
            int currentVertex3 = theStack.peek();
            // Получение следующего соседа
            int v = getAdjUnvisitedVertex3(currentVertex3);
            if (v == -1) // Если соседей больше нет,
                theStack.pop(); // извлечь элемент из стека
            else // Сосед существует
            {
                vertexList[v].wasVisited = true; // Пометка
                theStack.push(v); // Занесение в стек
                // Вывод ребра
                displayVertex3(currentVertex3); // От currentVertex3
                displayVertex3(v); // к v
                System.out.print(" ");
            }
        }
        // Стек пуст, работа закончена
        for (int j = 0; j < nVerts; j++) // Сброс флагов
            vertexList[j].wasVisited = false;
    }

    // -------------------------------------------------------------
    // Метод возвращает непосещенную вершину, смежную по отношению к v
    public int getAdjUnvisitedVertex3(int v) {
        for (int j = 0; j < nVerts; j++)
            if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
                return j;
        return -1;
    }
    // -------------------------------------------------------------
} // Конец класса Graph3

////////////////////////////////////////////////////////////////
class MSTApp {
    public static void main(String[] args) {
        Graph3 theGraph3 = new Graph3();
        theGraph3.addVertex3('A'); // 0 (исходная вершина)
        theGraph3.addVertex3('B'); // 1
        theGraph3.addVertex3('C'); // 2
        theGraph3.addVertex3('D'); // 3
        theGraph3.addVertex3('E'); // 4
        theGraph3.addEdge(0, 1); // AB
        theGraph3.addEdge(0, 2); // AC
        theGraph3.addEdge(0, 3); // AD
        theGraph3.addEdge(0, 4); // AE
        theGraph3.addEdge(1, 2); // BC
        theGraph3.addEdge(1, 3); // BD
        theGraph3.addEdge(1, 4); // BE
        theGraph3.addEdge(2, 3); // CD
        theGraph3.addEdge(2, 4); // CE
        theGraph3.addEdge(3, 4); // DE
        System.out.print("Minimum spanning tree: ");
        theGraph3.mst(); // Минимальное остовное дерево
        System.out.println();
    }
} // Конец класса MSTApp
////////////////////////////////////////////////////////////////
