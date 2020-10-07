import java.util.EmptyStackException;

public class ArrayStack {

    private int topo;
    private String p[];

    public ArrayStack() {
        p = new String[100];
        topo = 0;
    }

    public int size() {
        return topo;
    }

    public void push(char e) {
        if (topo >= p.length) {
            throw new StackOverflowError();
        }
        p[topo] = e + "";
        topo++;
    }

    public String pop() {
        if (topo == 0) {
            throw new EmptyStackException();
        }
        topo--;
        String aux = p[topo];
        p[topo] = null;
        return aux;
    }

    public String top() {
        if (topo == 0) {
            throw new EmptyStackException();
        }
        return p[topo - 1];
    }

    public boolean isEmpty() {
        return (topo == 0);
    }

    public void clear() {
        p = new String[100];
        topo = 0;
    }
}
