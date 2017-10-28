package ru.otus.hw03.collection;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyListIterator<T> implements ListIterator<T> {
    int cursor;
    MyArrayList<T> list;

    public MyListIterator(MyArrayList<T> list, int index) {
        this.list = list;
        this.cursor = index;
    }

    @Override
    public boolean hasNext() {
        return cursor != list.size();
    }

    @Override
    public T next() {
        if (cursor >= list.size())
            throw new NoSuchElementException();

        return list.get(cursor++);
    }

    @Override
    public boolean hasPrevious() {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public T previous() {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public int nextIndex() {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public int previousIndex() {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public void remove() {
        throw new RuntimeException("the method not implemented");
    }

    @Override
    public void set(T t) {
        list.set(cursor-1, t);
    }

    @Override
    public void add(T t) {
        throw new RuntimeException("the method not implemented");
    }
}
