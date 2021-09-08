package Chapter02Arrays;

class HighArray
{
    private long[] a; // Ссылка на массив a
    private int nElems; // Количество элементов в массиве
    //-----------------------------------------------------------
    public HighArray(int max) // Конструктор
    {
        a = new long[max]; // Создание массива
        nElems = 0; // Пока нет ни одного элемента
    }
    //-----------------------------------------------------------
    public boolean find(long searchKey)
    { // Поиск заданного значения
        int j;
        for(j=0; j<nElems; j++) // Для каждого элемента
            if(a[j] == searchKey) // Значение найдено?
                break; // Да - выход из цикла
        if(j == nElems) // Достигнут последний элемент?
            return false; // Да
        else
            return true; // Нет
    }
    //-----------------------------------------------------------
    public void insert(long value) // Вставка элемента в массив
    {
        a[nElems] = value; // Собственно вставка
        nElems++; // Увеличение размера
    }
    //-----------------------------------------------------------
    public boolean delete(long value)
    {
        int j;
        for(j=0; j<nElems; j++) // Поиск заданного значения
            if( value == a[j] )
                break;
        if(j==nElems) // Найти не удалось
            return false;
        else // Значение найдено
        {
            for (int k=j; k<nElems; k++) // Сдвиг последующих элементов
                a[k] = a[k+1];
            nElems--; // Уменьшение размера
            return true;
        }
    }
    //-----------------------------------------------------------
    public void display() // Вывод содержимого массива
    {
        for(int j=0; j<nElems; j++) // Для каждого элемента
            System.out.print(a[j] + " "); // Вывод
        System.out.println("");
    }
    //-----------------------------------------------------------
    public long getMax() {
        if (a.length == 0) {
            return -1;
        } else if (a.length == 1) {
            return a[0];
        }
        long max = a[0];
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] > max)
                max = a[i];
        }
        return max;
    }
    public void removeMax() {
        long max = getMax();
        if (getMax() != -1) {
            delete(max);
        }
    }

    public void noDups() {
        long[] newArray = new long[a.length];
        int j = 0;
        for (long l : a) {
            if (!contains(newArray, l)) {
                newArray[j] = l;
                ++j;
            }
        }
        a = newArray;
    }

    private boolean contains(long[] array, long value) {
        for (long l : array) {
            if (l == value) {
                return true;
            }
        }
        return false;
    }
} // Конец класса HighArray
////////////////////////////////////////////////////////////////
class HighArrayApp
{
    public static void main(String[] args)
    {
        int maxSize = 100; // Размер массива
        HighArray arr; // Ссылка на массив
        arr = new HighArray(maxSize); // Создание массива
        arr.insert(77); // Вставка 10 элементов
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(0);
        arr.insert(66);
        arr.insert(33);
        arr.display(); // Вывод элементов
        int searchKey = 35; // Поиск элемента
        if( arr.find(searchKey) )
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);
        arr.delete(0); // Удаление трех элементов
        arr.delete(55);
        arr.delete(99);
        arr.display(); // Повторный вывод
        System.out.println(arr.getMax());
        arr.removeMax();
        arr.display();
        arr.insert(11);
        arr.insert(11);
        arr.display();
        arr.noDups();
        arr.display();
    }
} // Конец класса HighArrayApp
////////////////////////////////////////////////////////////////
