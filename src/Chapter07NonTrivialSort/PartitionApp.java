package Chapter07NonTrivialSort;

//Демонстрация разбиения в массиве

class ArrayPar {
    private long[] theArray; //ссылка на массив
    private int nElems; //количество элементов в массиве

    public ArrayPar(int max) {
        nElems = 0;
        theArray = new long[max];
    }

    public void insert(long value) {
        theArray[nElems] = value;
        nElems++;
    }

    public int size() {
        return nElems;
    }

    public void display() {
        System.out.print("A=");
        for (int j = 0; j < nElems; j++) {
            System.out.print(theArray[j] + " ");
        }
        System.out.println();
    }

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1; //слева от первого элемента
        int rightPtr = right + 1; //справа от последнего элемента

        while (true) {
            while (leftPtr < right && theArray[++leftPtr] < pivot) ; //поиск большего элемента, чем опорное значение
            while (rightPtr > left && theArray[--rightPtr] > pivot) ; //поиск меньшего элемента, чем опорное значение
            if (leftPtr >= rightPtr) //Если указатели сошлись, то разбиение закончено
                break;
            else                    //иначе поменять элементы местами
                swap(leftPtr, rightPtr);
        }
        return leftPtr; //позиция разбиения
    }

    private void swap(int dex1, int dex2) {
        long temp = theArray[dex1];
        theArray[dex1] = theArray[dex2];
        theArray[dex2] = temp;
    }
}

public class PartitionApp {
    public static void main(String[] args) {
        int maxSize = 16;
        ArrayPar array = new ArrayPar(maxSize);

        for (int j = 0; j < maxSize; j++) {
            long n = (int) (java.lang.Math.random() * 199);
            array.insert(n);
        }
        array.display();

        long pivot = 99; //опорное значение
        System.out.print("Pivot is " + pivot);
        int size = array.size();
        int partDex = array.partitionIt(0, size - 1, pivot); //разбиение массива
        System.out.println(", Partition is at index " + partDex);
        array.display();
    }
}
