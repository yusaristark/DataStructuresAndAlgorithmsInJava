package Chapter13Graphs;

// topo.java
// Топологическая сортировка
// Запуск программы: C>java TopoApp
////////////////////////////////////////////////////////////////
class Vertex4 {
    public char label; // Метка (например, 'A')

    // -------------------------------------------------------------
    public Vertex4(char lab) // Конструктор
    {
        label = lab;
    }
}

////////////////////////////////////////////////////////////////
class Graph4 {
    private final int MAX_VERTS = 20;
    private Vertex4 vertexList[]; // Список вершин
    private int adjMat[][]; // Матрица смежности
    private int nVerts; // Текущее количество вершин
    private char sortedArray[];

    // -------------------------------------------------------------
    public Graph4() // Конструктор
    {
        vertexList = new Vertex4[MAX_VERTS];
        // Матрица смежности
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) // Матрица смежности
            for (int k = 0; k < MAX_VERTS; k++) // заполняется нулями
                adjMat[j][k] = 0;
        sortedArray = new char[MAX_VERTS]; // Метки отсортированных вершин
    }

    // -------------------------------------------------------------
    public void addVertex4(char lab) {
        vertexList[nVerts++] = new Vertex4(lab);
    }

    // -------------------------------------------------------------
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    // -------------------------------------------------------------
    public void displayVertex4(int v) {
        System.out.print(vertexList[v].label);
    }

    // -------------------------------------------------------------
    // -------------------------------------------------------------
    public void topo() // Топологическая сортировка
    {
        int orig_nVerts = nVerts; // Сохранение количества вершин
        while (nVerts > 0) // Пока в графе остаются вершины
        {
            // Получение вершины без преемников или -1
            int currentVertex4 = noSuccessors();
            if (currentVertex4 == -1) // В графе есть цикл
            {
                System.out.println("ERROR: Graph has cycles");
                return;
            }
            // Вставка метки вершины в массив (начиная с конца)
            sortedArray[nVerts - 1] = vertexList[currentVertex4].label;
            deleteVertex4(currentVertex4); // Удаление вершины
        }
        // Все вершины удалены, вывод содержимого sortedArray
        System.out.print("Topologically sorted order: ");
        for (int j = 0; j < orig_nVerts; j++)
            System.out.print(sortedArray[j]);
        System.out.println("");
    }

    // -------------------------------------------------------------
    public int noSuccessors() // Метод возвращает вершину, не имеющую
    { // преемников (или -1, если ее нет)
        boolean isEdge; // Ребро в матрице adjMat, ведущее от row в column
        for (int row = 0; row < nVerts; row++) // Для каждой вершины
        {
            isEdge = false; // Проверка ребер
            for (int col = 0; col < nVerts; col++) {
                if (adjMat[row][col] > 0) // Если существует ребро
                { // от текущей вершины
                    isEdge = true;
                    break; // У вершины имеется преемник
                } // Переход к проверке
            } // другой вершины
            if (!isEdge) // Если ребер нет,
                return row; // то нет и преемников
        }
        return -1; // Вершина не найдена
    }

    // -------------------------------------------------------------
    public void deleteVertex4(int delVert) {
        if (delVert != nVerts - 1) // Если вершина не последняя
        { // Удаление из vertexList
            for (int j = delVert; j < nVerts - 1; j++)
                vertexList[j] = vertexList[j + 1];
            // удаление строки из adjMat
            for (int row = delVert; row < nVerts - 1; row++)
                moveRowUp(row, nVerts);
            // Удаление столбца из adjMat
            for (int col = delVert; col < nVerts - 1; col++)
                moveColLeft(col, nVerts - 1);
        }
        nVerts--; // Количество вершин уменьшается на 1
    }

    // -------------------------------------------------------------
    private void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++)
            adjMat[row][col] = adjMat[row + 1][col];
    }

    // -------------------------------------------------------------
    private void moveColLeft(int col, int length) {
        for (int row = 0; row < length; row++)
            adjMat[row][col] = adjMat[row][col + 1];
    }
// -------------------------------------------------------------
} // Конец класса Graph4

////////////////////////////////////////////////////////////////
class TopoApp {
    public static void main(String[] args) {
        Graph4 theGraph4 = new Graph4();
        theGraph4.addVertex4('A'); // 0
        theGraph4.addVertex4('B'); // 1
        theGraph4.addVertex4('C'); // 2
        theGraph4.addVertex4('D'); // 3
        theGraph4.addVertex4('E'); // 4
        theGraph4.addVertex4('F'); // 5
        theGraph4.addVertex4('G'); // 6
        theGraph4.addVertex4('H'); // 7
        theGraph4.addEdge(0, 3); // AD
        theGraph4.addEdge(0, 4); // AE
        theGraph4.addEdge(1, 4); // BE
        theGraph4.addEdge(2, 5); // CF
        theGraph4.addEdge(3, 6); // DG
        theGraph4.addEdge(4, 6); // EG
        theGraph4.addEdge(5, 7); // FH
        theGraph4.addEdge(6, 7); // GH
        theGraph4.topo(); // Сортировка
    }
} // Конец класса TopoApp
////////////////////////////////////////////////////////////////
