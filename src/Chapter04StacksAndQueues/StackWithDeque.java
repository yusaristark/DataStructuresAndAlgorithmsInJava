package Chapter04StacksAndQueues;

class StackD extends Deque {

    public StackD(int s) {
        super(s);
    }

    public void push(long value) {
        super.insertRight(value);
    }

    public long pop() {
        return super.removeRight();
    }

    public void display() {
        super.display();
    }
}

public class StackWithDeque {
    public static void main(String[] args) {
        StackD stackD = new StackD(5);
        stackD.push(10);
        stackD.push(20);
        stackD.push(30);
        stackD.display();
        stackD.pop();
        stackD.display();
    }
}
