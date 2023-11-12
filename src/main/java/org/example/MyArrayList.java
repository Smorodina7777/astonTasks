package org.example;

import java.rmi.NotBoundException;
import java.util.Collection;
import java.util.Comparator;

public class MyArrayList<E> {
    private static final int CAPACITY = 8;
    private int capacity = CAPACITY;
    private int size;
    Object[] elements;




    public MyArrayList() {
        elements = new Object[capacity];
    }

    public void add( E element){
        checkCapacity();
        elements[size++] = element;
    }
    public void add(int index, E element) throws NotBoundException {
        if (index >= size) {
            throw new NotBoundException();
        }
        else    elements[index] = element;
    }

    public void addAll( Collection<? extends E> c){
        checkCapacity(c);
        for (Object elem:c) {
            elements[size++] = elem;
        }
    }
    public void checkCapacity(){
        if (size + 1 >= capacity)
            capacity = capacity*3/2+1;
        changeCapacity(capacity);
    }
    public void checkCapacity(Collection<? extends E> c){
        int newCapacity = size + c.size() + 1;
        if (newCapacity >= capacity)
            changeCapacity(newCapacity);

    }

    public void changeCapacity(int capacity){
        Object[] newElements = new Object[capacity];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    public void clear(){
        elements = new Object[CAPACITY];
        size = 0;
    }

    public Object get(int index) throws NotBoundException {
        if (index >= size) {
            throw new NotBoundException();
        }
        else
            return elements[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    public void removeInd(int index) throws NotBoundException {
        if (index >= size) {
            throw new NotBoundException();
        }
        else{
            for (int i = index; i <size ; i++) {
                elements[i] = elements[i + 1];
            }
            elements[size - 1] = null;
            size--;

        }
    }


    public void remove(Object o) throws NotBoundException {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                removeInd(i);
                break;
            }
        }
    }

    public void removeAll(Object o) throws NotBoundException {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                removeInd(i);
                i--;
            }
        }
    }

    public void sort(Comparator<? super E> c){
        mergeSort((E[]) elements, c, size);

    }

    private  static <E> void mergeSort(E[] array, Comparator<? super E> c, int size){
        E[] leftArray;
        E[] rightArray;
        if (array.length >size){
            leftArray = (E[]) new Object[size / 2];
            rightArray = (E[]) new Object[size - leftArray.length];
        }
        else {
            leftArray = (E[]) new Object[array.length / 2];
            rightArray = (E[]) new Object[array.length - leftArray.length];
        }

        if (array.length >size){
            for (int i = 0; i < size; i++) {
                if (i < leftArray.length) leftArray[i] = array[i];
                else
                    rightArray[i - leftArray.length] = array[i];
            }
        }else {
            for (int i = 0; i < array.length; i++) {
                if (i < leftArray.length) leftArray[i] = array[i];
                else
                    rightArray[i - leftArray.length] = array[i];
            }
        }

        if(rightArray.length>1) {
            mergeSort(leftArray, c, size);
            mergeSort(rightArray, c, size);
        }

        int leftId = 0;
        int rightId = 0;

        if (leftArray.length+rightArray.length == size){
            for (int i = 0; i < size; i++) {
                if (leftId == leftArray.length && rightId < rightArray.length) {
                    array[i] = rightArray[rightId++];
                } else if (rightId == rightArray.length && leftId < leftArray.length) {
                    array[i] = leftArray[leftId++];
                } else {
                    if (c.compare(rightArray[rightId], leftArray[leftId]) > 0) {
                        array[i] = leftArray[leftId++];
                    } else {
                        array[i] = rightArray[rightId++];
                    }
                }
            }
        }else {
            for (int i = 0; i < array.length; i++) {
                if (leftId == leftArray.length && rightId < rightArray.length) {
                    array[i] = rightArray[rightId++];
                } else if (rightId == rightArray.length && leftId < leftArray.length) {
                    array[i] = leftArray[leftId++];
                } else {
                    if (c.compare(rightArray[rightId], leftArray[leftId]) > 0) {
                        array[i] = leftArray[leftId++];
                    } else {
                        array[i] = rightArray[rightId++];
                    }
                }
            }
        }
    }
}
