package com.gmail.kharchenko55.vlad;

import java.util.Objects;

public class Node<V> {
    long key;
    V value;
    Node<V> next;

    public Node(long key, V value, Node<V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<V> getNext() {
        return next;
    }

    public void setNext(Node<V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        if (key != node.key) return false;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return (int) (key ^ (key >>> 32));
    }

    @Override
    public String toString() {
        return "Node --> [" + key + " : " + value + "]";
    }
}
