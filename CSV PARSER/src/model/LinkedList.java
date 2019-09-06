package model;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * The LinkedList class implements the generic "Iterable" interface
 *
 * @param <T>
 */
public class LinkedList<T> implements Iterable<T> {

    /**
     * Instance variable "head" of type Node which points to the beginning of the list
     */
    private Node head;

    /**
     * Instance variable of type int which keeps track of the number of elements in the
     * list.
     */
    private int size;

    /**
     * A constructor which receives no arguments and creates an empty list and initializing
     * the size to 0 accordingly.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * The method add() adds a new object to the END of the list. This method receives
     * one argument: a generic object as a parameter. This is the new data to be added
     * to the list. add() does not return anything.
     *
     * @param data
     * @TODO: implement
     */
    public void add(T data) {
        Node<T> node = new Node<>(data);
        if (this.head == null) {
            this.head = node;
            this.size++;
        } else {
            Node<T> n = this.head;
            while (n.getNext() != null) {
                n = n.getNext();
            }
            n.setNext(node);
        }
        if (this.size < 57) {
            this.size++;
        }
    }

    /**
     * The method contains() verifies whether the list in reference has the data being queried. This method receives
     * one argument of generic type for the object we want to search for. It also returns the object if the object
     * is found in the linked list. If the object is not found, null is returned.
     *
     * @param: type generic
     * @return: object type generic
     *
     */
    public T contains(T objectQuery) {
        Node<T> n = this.head;
        if (n.getData().equals(objectQuery)) return n.getData();
        while (n.getNext() != null) {
            if (n.getNext().getData().equals(objectQuery)) {
                return n.getNext().getData();
            }
            n = n.getNext();

        }
        return null;
    }

    /**
     * The method getIndex() returns the generic object at the requested index. Receives one argument: an int value for
     * the requested index. This method also returns the specific generic object located at the requested index.
     * "IndexOutOfBoundsException" is thrown is user inputs an index that is invalid.
     *
     * @throws IndexOutOfBoundsException
     * @param: type int
     * @return: returns object at the index specified by the user of type generic
     */
    public T getIndex(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos > this.size) throw new IndexOutOfBoundsException();
        Node<T> walker = head;
        int counter = 0;
        while (walker != null) {
            if (pos == counter)
                return walker.getData();
            counter++;
            walker = walker.getNext();
        }

        return null;
    }

    /**
     * The method insertAtIndex() enables the user to add a generic object to a chosen location. Receives two arguments:
     * a generic object for the object we want to insert in the list and an int value for the index of the location we
     * want to add our new element.
     *
     * @param dataToInsert
     * @param index
     * @throws IndexOutOfBoundsException
     * @TODO: implement
     */
    void insertAtIndex(T dataToInsert, int index) throws IndexOutOfBoundsException {
        if (index < 0) throw new IndexOutOfBoundsException();
        if (index > this.size()) {
            this.add(dataToInsert);
            return;
        }
        Node<T> newNode = new Node<>(dataToInsert);

        // insert as the new head?
        if (index == 0) {
            // The 1st case.
            newNode.setNext(this.head);
            this.head = newNode;
        } else {
            // The 2nd case.
            // start from the head:
            Node<T> node = this.head;
            // find position just before the expected one:
            while (--index > 0) {
                node = node.getNext();
            }
            // insert the new node:
            newNode.setNext(node.getNext());
            node.setNext(newNode);
        }
    }


    /**
     * The method iterator() returns an object of type ListIterator
     *
     * @return
     */
    @Override
    public ListIterator iterator() {
        return new ListIterator();
    }

    /**
     * The method size() returns the instance variable size to indicate the number of elements in the list.
     *
     * @return: type int
     */
    public int size() {
        return this.size;
    }

    /**
     * The method .toString() returns a String object containing the text representation of every element in the list.
     *
     * @return: type String
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        String index = "Index";
        String countryName = "Country Name";
        int indexCounter = 0;
        ListIterator itr = this.iterator();
        str.append(String.format("%5s %30s %s\n", index, countryName, ((Country) itr.current.getData()).generateYears()));
        while (itr.hasNext()) {
            T data = itr.next();
            str.append(String.format("%5d %s\n", indexCounter++, data.toString()));
        }
        return str.toString();
    }



    /**
     * Class Node is a nested class within LinkedList class. The purpose of this class is
     * to contain the data of interest.
     */
    public class Node<T> {

        /**
         * Instance variable data which is of a generic type.
         */
        private T data;

        /**
         * Instance variable next which is of type Node.
         */
        private Node<T> next;

        /**
         * A constructor for Node class that initializes one instance variable. Receives one
         * argument: A generic type for the data of the current instance which. With this
         * constructor instance variable next is set to null.
         *
         * @param data
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        /**
         * A constructor that initializes both instance variables. Receives two arguments:
         * A generic type for the data of the current instance and a Node object for another node
         * we want to connect to. Connect the two nodes such that the current next instance points
         * to their other Node object.
         *
         * @param data
         * @param next
         */
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        /**
         * The method .getData() does not receive any arguments and returns a generic type
         * "data"
         *
         * @return
         */
        public T getData() {
            return this.data;
        }

        /**
         * The method .getNext() does not receive any arguments and returns a Node type
         * for the next object.
         *
         * @return
         */
        public Node<T> getNext() {
            return this.next;
        }

        /**
         * The method .setNext() receives one argument of type generic Node type and does
         * not return anything. User the argument to update the "next" instance variable.
         *
         * @param node
         */
        public void setNext(Node<T> node) {
            this.next = node;
        }

        /**
         * The method .toString() returns the String representation of the data instance
         * variable.
         *
         * @return
         */
        @Override
        public String toString() {
            return data.toString();
        }

    }

    /**
     * The class ListIterator is nested within the class LinkedList. The class ListIterator
     * implements the generic interface Iterator. The purpose of this class is to traverse
     * the collection of objects within the list.
     */
    public class ListIterator implements Iterator<T> {

        /**
         * Instance variable current is of type Node
         */
        private Node<T> current;


        /**
         * A constructor that receives no argument. Calling the constructor initializes the
         * instance variable "current" to the beginning of the list.
         */
        ListIterator() {
            this.current = head;
        }

        /**
         * The method .hasNext() does not receive any arguments and returns a boolean value.
         * If the list has another element this method should return true.
         *
         * @return: type boolean
         */
        @Override
        public boolean hasNext() {
            if (current == null) {
                return false;
            }
            return true;
        }

        /**
         * The method next() does not receive any arguments and saves and returns data that
         * "current" is pointing to. Then moves current to the next available reference.
         *
         * @return: type Node
         */
        @Override
        public T next() throws NoSuchElementException {
            if (current == null) {
                throw new NoSuchElementException();
            }
            T data = this.current.getData();
            this.current = current.getNext();
            return data;

        }

        /**
         * The method .next() does not receive any arguments and does not return anything.
         * A call to this method should throw an "UnsupportedOperationException()" to
         * indicate that the operation is not supported.
         *
         * @throws UnsupportedOperationException
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

    }
}
