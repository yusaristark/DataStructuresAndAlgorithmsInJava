package Chapter02Arrays;

import java.util.Arrays;

// orderedArray.java
// Работа с классом упорядоченного массива
// Запуск программы: C>java OrderedApp
////////////////////////////////////////////////////////////////
class OrdArray
{
    private long[] a; // Ссылка на массив a
    private int nElems; // Количество элементов данных
    //-----------------------------------------------------------
    public OrdArray(int max) // Конструктор
    {
        a = new long[max]; // Создание массива
        nElems = 0;
    }
    //-----------------------------------------------------------
    public int size()
    { return nElems; }
    //-----------------------------------------------------------
    public int find(long searchKey)
    {
        int lowerBound = 0;
        int upperBound = nElems-1;
        int curIn;
        while(true)
        {
            curIn = (lowerBound + upperBound ) / 2;
            if(a[curIn]==searchKey)
                return curIn; // Элемент найден
            else if(lowerBound > upperBound)
                return nElems; // Элемент не найден
            else // Деление диапазона
            {
                if(a[curIn] < searchKey)
                    lowerBound = curIn + 1; // В верхней половине
                else
                    upperBound = curIn - 1; // В нижней половине
            }
        }
    }
    //-----------------------------------------------------------
    public void insert(long value) // Вставка элемента в массив
    {
        int j;
        for(j=0; j<nElems; j++) // Определение позиции вставки
            if(a[j] > value) // (линейный поиск)
                break;
        for(int k=nElems; k>j; k--) // Перемещение последующих элементов
            a[k] = a[k-1];
        a[j] = value; // Вставка
        nElems++; // Увеличение размера
    }
    //-----------------------------------------------------------
    public boolean delete(long value)
    {
        int j = find(value);
        if(j==nElems) // Найти не удалось
            return false;
        else // Элемент найден
        {
            for(int k=j; k<nElems; k++) // Перемещение последующих элементов
                a[k] = a[k+1];
            nElems--; // Уменьшение размера
            return true;
        }
    }
    //-----------------------------------------------------------
    public void display() // Вывод содержимого массива
    {
        for(int j=0; j<nElems; j++) // Перебор всех элементов
            System.out.print(a[j] + " "); // Вывод текущего элемента
        System.out.println("");
    }
    //-----------------------------------------------------------
    public long[] merge(long[] arr1, long[] arr2) {
        long[] arr = new long[arr1.length + arr2.length];
        int i;
        for (i = 0; i < arr1.length; i++) {
            arr[i] = arr1[i];
        }
        for (i = i + 1; i < arr1.length + arr2.length; i++) {
            arr[i] = arr2[i - arr1.length];
        }
        Arrays.sort(arr);
        return arr;
    }

    public long[] mergeOrdered(long[] arr1, long[] arr2) {
        long[] arr = new long[arr1.length + arr2.length];
        int i, j, k;
        for (i = 0, j = 0, k = 0; k != arr.length; ) {
            if (i < arr1.length && j < arr2.length) {
                if (arr1[i] < arr2[j]) {
                    arr[k] = arr1[i];
                    ++k;
                    ++i;
                } else if (arr1[i] > arr2[j]) {
                    arr[k] = arr2[j];
                    ++k;
                    ++j;
                } else {
                    arr[k] = arr1[i];
                    ++k;
                    arr[k] = arr2[j];
                    ++k;
                    ++i;
                    ++j;
                }
            } else if (j < arr2.length) {
                for(; j < arr2.length; ++j) {
                    arr[k] = arr2[j];
                    ++k;
                }
                break;
            } else if (i < arr1.length) {
                for (; i < arr1.length; ++i) {
                    arr[k] = arr1[i];
                    ++k;
                }
                break;
            }
        }
        return arr;
    }
}
////////////////////////////////////////////////////////////////
class OrderedApp
{
    public static void main(String[] args)
    {
        int maxSize = 100; // Размер массива
        OrdArray arr; // Ссылка на массив
        arr = new OrdArray(maxSize); // Создание массива
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
        int searchKey = 55; // Поиск элемента
        if( arr.find(searchKey) != arr.size() )
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);
        arr.display(); // Вывод содержимого
        arr.delete(0); // Удаление трех элементов
        arr.delete(55);
        arr.delete(99);
        arr.display(); // Повторный вывод
        System.out.println(Arrays.toString(arr.merge(new long[]{5, 2, 4, 8, 9}, new long[]{0, 1, 2, 10, 12, 5})));
        System.out.println(Arrays.toString(arr.mergeOrdered(new long[]{0, 1, 3, 4, 6, 10, 17}, new long[] {1, 2, 5, 5, 7, 11})));
    }
}
////////////////////////////////////////////////////////////////
