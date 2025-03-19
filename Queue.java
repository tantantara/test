/***
Coder: Roslan S, UiTM Pahang, roslancs@uitm.edu.my
Year: 2012
***/

public class Queue <E> 
{
    private LinkedList<E> linkedList;
    private int size=0;
    
    public Queue() {
        linkedList = new LinkedList<>();
    }
    
        public boolean isEmpty() {
        return linkedList.isEmpty();
    }
    
    // i) add data at the end of the list (enqueue).
    public void enqueue(E element) {
        linkedList.addLast(element);
    }
    
    // ii) Removes data at the beginning of a list (dequeue).
    public E dequeue() {
        return linkedList.removeFirst();
    }

    // iii) Determine size of the list.
    public int size(){ return this.size; }
}
