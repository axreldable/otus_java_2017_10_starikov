package ru.otus.hw03.collection;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;  // default initial size
    private static final float CAPACITY_INCREASER = 1.33f;

    private Object[] elementData;                    // elements in list
    private int size;                                // real size of list

    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.elementData = new Object[initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) elementData, 0, size, c);
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public Object[] toArray() {
        Object[] copy = new Object[size];
        System.arraycopy(elementData, 0, copy, 0, size);
        return copy;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public boolean add(T t) {
        if (elementData.length == size) {
            increaseCapacity();
        }

        elementData[size] = t;
        size++;

        return true;
    }

    private void increaseCapacity() {
        Object[] incremented = new Object[elementData.length + getSizeToAdd()];
        System.arraycopy(elementData, 0, incremented, 0, elementData.length);
        elementData = incremented;
    }

    private int getSizeToAdd() {
        return (int) (size * CAPACITY_INCREASER);
    }

    @Override
    public boolean remove(Object o) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);

        return (T) elementData[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = (T) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public T remove(int index) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new MyListIterator<>(this, index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new RuntimeException("the method not implemented");
    }
}
