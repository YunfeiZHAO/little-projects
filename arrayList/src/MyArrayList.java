import java.lang.reflect.Array;

public class MyArrayList<E> {
	
	/*
	 * Do not change this initial capacity; it is used by our test cases
	 */
	private static final int INITIAL_CAPACITY = 4;

	/*
	 *  These are protected so that test cases can access them.
	 *  Please do not change the visibility of these fields!
	 */
	protected E[] data = (E[])new Object[INITIAL_CAPACITY];
	protected int size = 0;

	public MyArrayList() {}

	/*
	 * Need to implement this in step 5
	 */
	public MyArrayList(E[] arr) {
		if(arr == null) {
			new MyArrayList();
		} else{
			size = arr.length;
			data = (E[])new Object[this.size];
			System.arraycopy(arr, 0, data, 0, size);}
	}
	
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		else return data[index];
	}
	
	private void increaseCapacity() {
		E[] newData = (E[])new Object[2 * data.length];
		System.arraycopy(data, 0, newData, 0, size);
		data = newData;
	}

	public void shrink() {
		if(size <= data.length/4) {
			E[] newData = (E[])new Object[data.length/2];
			System.arraycopy(data, 0, newData, 0, size);
			data = newData;
		}
	}
	
	/*
	 * This method adds the element to the list.
	 * Except for modifying it to use Java Generics,
	 * DO NOT OTHERWISE CHANGE THIS METHOD
	 * as it is used in testing your code.
	 */
	public void add(E value) {
		if (size == data.length) {
			increaseCapacity();
		}
		data[size++] = value;
	}
	
	public void add(int index, E element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (size == data.length) {
			increaseCapacity();
		}
		for (int i = size-1; i >= index; i--) {
			data[i+1] = data[i];
		}
		data[index] = element;
		size++;
	}

	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E target = data[index];
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--;
		shrink();
		return target;
	}
	

	/*
	 * Need to implement this in Step 2.
	 */
	public boolean remove(E obj) {
		for (int i = 0; i < size - 1; i++) {
			if(obj.equals(data[i])) {
				for(int j = i; j < size - 1; j++) {
					data[j] = data[j + 1];
				}
				size--;
				shrink();
				return true;
			}
		}
		return false;
	}
	
	public void print() {
		for (int i = 0; i < size; i++) {
			System.out.println(i + ": " + data[i]);
		}
	}
	
	public boolean contains(String obj) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(obj))
				return true;
		}
		return false;
	}
	
	/*
	 * Need to implement this in Step 4
	 */
	public E set(int index, E obj) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E old = data[index];
		data[index] = obj;
		return old;
	}


    public static void main(String[] args) {
            MyArrayList a = new  <Integer>MyArrayList();
            String[] data = {"cat", "dog", "elephant"};
			String[] data1;
		 	MyArrayList c = new MyArrayList(null);
					/*
			MyArrayList b = new MyArrayList(data);
			System.out.println(b.data[1]);
			b.remove("cat");
			b.add("dophine");
			b.remove(1);
			b.remove("elephant");
			System.out.println(b.data.length);
			System.out.println(b.size);
			a.add(1);
			System.out.println(a.data[0]);
			a.set(0,"9");
			System.out.println(a.data[0]);
			a.set(0,"sd");
			System.out.println(a.data[0]);

					 */

    }
}

