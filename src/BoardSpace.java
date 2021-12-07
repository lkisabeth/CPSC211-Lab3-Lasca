import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public enum BoardSpace {
    Available,
    Red { public String toString () { return "Red";}},
    Black { public String toString () { return "Black";}};

    public Deque<String> pieces = new LinkedList<>();
}