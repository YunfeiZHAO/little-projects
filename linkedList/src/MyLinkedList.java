public class MyLinkedList {

	static class Node {
		String value;
		Node next = null;
		Node(String value) {
			this.value = value;
		}
	}
	
	protected Node head = null;
	protected Node tail = null;
	protected int size = 0;
	
	
	public void addFirst(String value) {
    	Node newNode = new Node(value);
    	newNode.next = head;
    	head = newNode;
    	if (newNode.next == null) {
    		tail = newNode;
    	}
    	size++;
    }
	
    public void addLast(String value) {
		Node newNode = new Node(value);
		if (tail == null) {
		    head = newNode;
		} else {
		    tail.next = newNode;
		}
		tail = newNode;
		size++;
    }
    
    public void add(int index, String value) {
        if (index < 0) throw new IndexOutOfBoundsException();
        if (index == 0) { 
        	addFirst(value);
        }
        else {
            Node newNode = new Node(value);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                if (current == null) throw new IndexOutOfBoundsException();
                current = current.next;
            }
            if (current.next == null) { tail = newNode; }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }	
    }
    
    public String get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                if (current == null || current.next == null) {
                    throw new IndexOutOfBoundsException();
                }
                current = current.next;
            }
            return current.value;
        }
    }

    
    public boolean contains(String value) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
        }
        if (head == null) {
            tail = null;
        }  
        if (size > 0) size--;
    }

    public void removeLast() {
        if (head == null) { //empty list
            return;
        } else if (head == tail) { 
          //single element list
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            current.next = null;
        }
        size--;
    }

    public void remove(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        else if (index == 0) removeFirst();
        else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                if (current == null) throw new IndexOutOfBoundsException();
                current = current.next;
            }
            current.next = current.next.next;
            if (current.next == null) {
                tail = current;
            }
            size--;
        }
    }

    public void remove(String s) {
        Node cur = this.head;
        if(cur == null) { return; }
        while(cur != null && cur.next != null) {
            if(cur.next.value.equals(s)) {
                cur.next = cur.next.next;
                size--;
                if(cur.next == null) {
                    tail = cur;
                }
            } else {
                cur = cur.next;
            }
        }
        if(this.head.value.equals(s)) { removeFirst(); }
    }
    

    /*
     * Implement the methods below.
     * Please do not change their signatures!
     */

	public void reverse() {
        Node prev = null;
        Node cur = this.head;
        Node next = null;
        while(cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        this.tail = this.head;
        this.head = prev;
	}

	public void removeMaximumValues(int N) {
	    if(N <= 0) return;
        for(int i = 0; i<N; i++) {
            Node cur = this.head;
            if(cur == null) return;
            String max = cur.value;
            while(cur.next != null) {
                cur = cur.next;
                if(max.compareTo(cur.value) < 0){
                    max = cur.value;
                }
            }
            remove(max);
        }
	}

    public boolean Subsequence(Node one, Node two) {
	    Node cur1 = one;
	    Node cur2 = two;
        while(cur1 != null && cur2 != null) {
            if(cur1.value != cur2.value) {
                return false;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true;
    }

	public boolean containsSubsequence(MyLinkedList two) {
        if(two == null || two.size == 0) { return false; }
        int gap = this.size - two.size;
        if(gap < 0 || !contains(two.head.value)) {return false;}
        else {
            Node cur = head;
            for(int i = 0; i<=gap; i++) {
                if(cur.value == two.head.value) {
                    Node cur1 = cur.next;
                    Node cur2 = two.head.next;
                    if(Subsequence(cur1, cur2)) {return true;}
                }
                cur = cur.next;
            }
        }
        return false;
	}

    public void printList(){
	    Node cur = this.head;
	    while(cur != null) {
	        System.out.println(cur.value);
	        cur = cur.next;
        }
    }


    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add(0,"A");
        list.add(1,"B");
        list.add(2,"C");
        list.add(3,"D");
        list.add(3,"C");
        list.add(3,"C");
        MyLinkedList list2 = new MyLinkedList();
        list2.add(0,"C");
        list2.add(1,"B");
        list2.add(2,"C");

/*        System.out.println("Given Linked list");
        list.printList();
        list.reverse();
        System.out.println("Reversed linked list ");
        list.printList();
        System.out.println("remove elements ");
        list.removeMaximumValues(2);
        list.remove("A");
        list.remove("B");
        list.remove("D");
        list.remove("C");
        list.printList();*/
        System.out.println("containsSubsequence ");
        System.out.println(list.containsSubsequence(list2));
    }
}



