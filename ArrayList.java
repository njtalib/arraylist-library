public class ArrayList<E> implements ArrayInterface<E>{
    private int size;
    private int capacity;
    private E[] myArray;

    //constructors
    public ArrayList() {
        this.capacity = 5;
        this.size = 0;
        myArray = (E[]) new Object[this.capacity];
    }

    public ArrayList(int capacity) {
        // this.capacity = limit;
        this.size = 0;
        myArray = (E[]) new Object[this.capacity];
        System.out.println("DEBUG" + this.capacity);
    }

    public void add(E a) {
        if (size < capacity) {
            myArray[size] = a; //set object at first empty space
            size++;
        }
        else {
            // System.out.println("not enough space resizing ArrayList " + size);
            this.reallocate();
            this.add(a);
            // System.out.println("size: " + size);
        }
    }

    public void reallocate() {
        E[] temp = (E[]) new Object[capacity*2];
        for (int i=0; i<size; i++) {
            temp[i] = myArray[i];
        }
        this.myArray = temp;
        this.capacity *= 2;
    }
    
    public void add(int index, E a) {
        // check if index is valid
        if (index<0 || index>size) {
            // System.out.println("invalid index - add idx " + index);
            return;
        }
        else if (index == size) {
            // adds to the end so we use existing add method
            this.add(a);
        }
        else {
            if (this.capacity == this.size) {
                // array is full
                this.reallocate();
            }
            // shift all contents over from index to the end by one
            for (int i=size; i>index; i--) {
                myArray[i] = this.myArray[i-1];
            }

            this.myArray[index]=a; //add intended object to index
            this.size++;
        }
    }
    
    public E remove(int index) {
        if (index<0 || index>=size) {
            // System.out.println("invalid index - remove idx " + index);
            return null;
        }
        else {
            E temp = myArray[index];
            for (int i=index; i<size-1; i++) {
                myArray[i] = this.myArray[i+1];
            }
            this.size--;
            return(temp);

        }


    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            // System.out.println("invalid index - get idx " + index);
            return null;
        }
        return myArray[index];
    }

    public void set(int index, E a) {
        if (index < 0 || index >= size) {
            // System.out.println("invalid index - set idx " + index);z
            return;
        }
        myArray[index] = a;
    }

    public int getSize() {
        return size;
    }

    public int indexOf(E a) {
        for (int i=0; i<size; i++) {
            if (myArray[i].equals(a)) {
                return i;
            } 
        } 
        return -1;
    }

    public String toString() {
        String output = "{";
        for (int i=0; i<size; i++) {
            if (i == size-1) {
                output += (myArray[i] + "");
            }
            else {
                output += (myArray[i] +  ", ");
            }
        }
        output += "}";
        return output;
    }

    public void print(E toPrint) {
        System.out.println(toPrint);
    }
}
