public class LinkedList<E> {
   
    private Node<E> head, current, tail;    
    private int size = 0;
    
    public LinkedList() {
        head = current = tail = null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }    
    
    // i) insert node at front and at the back of the list
    public void addFirst(E element) {
        Node newNode = new Node(element);
        newNode.next = this.head;
        this.head = newNode;          
        if(this.tail == null) {
            this.tail = this.head;
        }
    }
    
    // ii) remove node anywhere in the list.
    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            this.current = this.head;
            this.head = this.head.next;            
            if (this.head == null)
                this.tail = null;
            return current.element;
        }
    }
        
    public E removeLast() {
        if (this.isEmpty()) 
            return null;
        else if (this.head == this.tail) {
            this.current = this.head;
            this.head = this.tail = null;           
            return current.element;            
        }
        else {
            this.current = this.head;
            while (this.current.next != tail) {
                this.current = this.current.next;                
            }      
            Node<E> temp = this.tail;
            this.tail = this.current;
            this.tail.next = null;            
            return temp.element;
        }
    }
    
    public E removeAfter(E element) {        
        if (this.isEmpty()) {
            return null;
        }
        else if (this.head == this.tail) {
            this.current = this.head;
            this.head = this.tail = null;           
            return current.element;            

        }
        else {
            Node<E> previous = this.head;            
            while (previous.next != null) {
                if (element.equals(previous.element))
                {
                    break;
                }
                previous = previous.next;
            }            
            current = previous.next;
            previous.next = current.next;          
            return current.element;
        }
    }
    
    public void addLast(E element) {
        Node newNode = new Node(element);
        
        if(this.tail == null) {
            this.head = this.tail = newNode;
        }
        else {
            this.tail.next = newNode;
            this.tail = this.tail.next;
        }       
    }
     
    // iii) provide traversal from head until the last node in the list. (getHead() and getNext()).
    public E getHead() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            this.current = this.head;
            return this.current.element;
        }
    }
    
    public E getLast() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            return this.tail.element;
        }
    }
    
    public E getNext() {
        if (this.current == this.tail) {
            return null;
        }
        else {
            this.current = this.current.next;
            return this.current.element;
        }
    }

    // iv) determine the size of the list.
    public int size(){ return this.size; }

    // v) status of whether the list is empty or has element(s).
    public boolean contains(E element) {
        boolean isContain = false;
        this.current = this.head;
        
        while (this.current != null) {
            if (element.equals(this.current.element)) {
                isContain = true;
                break;
            }
        }
 
        return isContain;
    }
    
    // vi) a method to display details of all elements in the list.
    public String toString() {
        StringBuilder result = new StringBuilder("[");        
        if (this.isEmpty()) {
            result.append("The list is empty]");
        }
        else {
            this.current = this.head;
            while (this.current != null) {
                result.append(this.current.element);
                this.current = this.current.next;
                if (this.current != null)
                    result.append(", ");
                else
                    result.append("]");                
            }            
        }
        return result.toString();
    }
}
