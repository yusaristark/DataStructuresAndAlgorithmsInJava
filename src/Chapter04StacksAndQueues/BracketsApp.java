package Chapter04StacksAndQueues;

// brackets.java
// Использование стека для поиска парных скобок
// Запуск программы: C>java BracketsApp

import java.io.*; // Для ввода/вывода

////////////////////////////////////////////////////////////////
class StackR {
    private int maxSize;
    private char[] stackArray;
    private int top;

    //--------------------------------------------------------------
    public StackR(int s) // Конструктор
    {
        maxSize = s;
        stackArray = new char[maxSize];
        top = -1;
    }

    //--------------------------------------------------------------
    public void push(char j) // Размещение элемента на вершине стека
    {
        stackArray[++top] = j;
    }

    //--------------------------------------------------------------
    public char pop() // Извлечение элемента с вершины стека
    {
        return stackArray[top--];
    }

    //--------------------------------------------------------------
    public char peek() // Чтение элемента с вершины стека
    {
        return stackArray[top];
    }

    //--------------------------------------------------------------
    public boolean isEmpty() // True, если стек пуст
    {
        return (top == -1);
    }
//--------------------------------------------------------------
} // Конец класса StackX

////////////////////////////////////////////////////////////////
class BracketChecker {
    private String input; // Входная строка

    //--------------------------------------------------------------
    public BracketChecker(String in) // Конструктор
    {
        input = in;
    }

    //--------------------------------------------------------------
    public void check() {
        int stackSize = input.length(); // Определение размера стека
        StackR theStack = new StackR(stackSize); // Создание стека
        for (int j = 0; j < input.length(); j++) // Последовательное чтение
        {
            char ch = input.charAt(j); // Чтение символа
            switch (ch) {
                case '{': // Открывающие скобки
                case '[':
                case '(':
                    theStack.push(ch); // Занести в стек
                    break;
                case '}': // Закрывающие скобки
                case ']':
                case ')':
                    if (!theStack.isEmpty()) // Если стек не пуст,
                    {
                        char chx = theStack.pop(); // Извлечь и проверить
                        if ((ch == '}' && chx != '{') ||
                                (ch == ']' && chx != '[') ||
                                (ch == ')' && chx != '('))
                            System.out.println("Error: " + ch + " at " + j);
                    } else // Преждевременная нехватка элементов
                        System.out.println("Error: " + ch + " at " + j);
                    break;
                default: // Для других символов действия не выполняются
                    break;
            }
        }
        // В этой точке обработаны все символы
        if (!theStack.isEmpty())
            System.out.println("Error: missing right delimiter");
    }//--------------------------------------------------------------
} // Конец класса BracketChecker

////////////////////////////////////////////////////////////////
class BracketsApp {
    public static void main(String[] args) throws IOException {
        String input;
        while (true) {
            System.out.print(
                    "Enter string containing delimiters: ");
            System.out.flush();
            input = getString(); // Чтение строки с клавиатуры
            if (input.equals("")) // Завершение, если [Enter]
                break;
            // Создание объекта BracketChecker
            BracketChecker theChecker = new BracketChecker(input);
            theChecker.check(); // Проверка парных скобок
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
} // Конец класса BracketsApp
////////////////////////////////////////////////////////////////
