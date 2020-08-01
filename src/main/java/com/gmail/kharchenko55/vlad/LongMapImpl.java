package com.gmail.kharchenko55.vlad;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LongMapImpl<V> implements LongMap<V> {

    private Node<V>[] buckets;
    private int capacity = 16;
    private float bucketsSize = 0;
    private float magnificationValue = 0.75f;

    public LongMapImpl() {
        buckets = new Node[capacity];
    }

    public LongMapImpl(int capacity) {
        this.capacity = capacity;
        buckets = new Node[capacity];
    }

    private int index(long key) {
        if (key == 0) {
            return 0;
        }

        return Math.abs(Long.hashCode(key) % capacity);
    }

    private Node<V>[] resize(Node<V>[] oldBuckets, int oldCapacity) {
        capacity = oldCapacity * 2;
        buckets = new Node[capacity];
        for (Node<V> element : oldBuckets) {
            while (element != null) {
                put(element.key, element.value);
                element = element.next;
            }
        }
        return buckets;
    }

    private Node<V> getNode(long key) {
        int bucketIndex = index(key);
        Node<V> node = buckets[bucketIndex];
        while (node != null) {
            if (node.getKey() == key) {
                return node;
            }

            node = node.next;
        }

        return null;
    }

    public V put(long key, V value) {
        if (bucketsSize / buckets.length > magnificationValue) {
            bucketsSize = 0;
            buckets = resize(buckets, capacity);

        }
        int bucketIndex = index(key);
        Node<V> node = buckets[bucketIndex];
        if (node == null) {
            buckets[bucketIndex] = new Node<>(key, value, null);
            bucketsSize++;
        } else {
            Node<V> previousNode = null;
            Node<V> currentNode = node;

            while (currentNode != null) {
                if (Long.valueOf(currentNode.getKey()).equals(key)) {
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if (previousNode != null) {
                previousNode.setNext(new Node<>(key, value, null));
                bucketsSize++;
            }

        }

        return value;
    }

    public V get(long key) {
        return Objects.requireNonNull(getNode(key)).getValue();
    }

    public V remove(long key) {
        int index = index(key);
        Node<V> previousNode = null;
        Node<V> currentNode = buckets[index];
        while (currentNode != null) {
            if (currentNode.getKey() == key) {
                if (previousNode == null) {
                    currentNode = currentNode.getNext();
                    buckets[index] = currentNode;
                } else {
                    previousNode.setNext(currentNode.getNext());
                }

                bucketsSize--;
                break;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();

        }
        return null;
    }

    public boolean isEmpty() {
        return bucketsSize == 0;
    }

    public boolean containsKey(long key) {
        return getNode(key) != null;
    }

    public boolean containsValue(V value) {
        return Arrays.asList(values()).contains(value);
    }

    public long[] keys() {
        long[] keys = new long[(int) size()];
        int i = 0;
        for (Node<V> node : buckets) {
            keys[i] = node.getKey();
            i++;
            if (i == size()) break;
        }

        return keys;
    }

    public V[] values() {
        List<V> values = new ArrayList<>((int) size());
        for (Node<V> node : buckets) {
            if (node == null) continue;
            values.add(node.getValue());
        }

        if (values.isEmpty()) return null;

        Class<?> c = values.get(0).getClass();

        @SuppressWarnings("unchecked")
        V[] result = (V[]) Array.newInstance(c, values.size());

        for (int i = 0; i < values.size(); i++) {
            result[i] = values.get(i);
        }

        return result;
    }

    public long size() {
        return (long) bucketsSize;
    }

    public void clear() {
        capacity = 16;
        bucketsSize = 0;
        buckets = new Node[capacity];
    }

    public void print() {
        for (Node<V> node : buckets) {
            if (node == null) continue;
            System.out.println("{" + node.getKey() + ":" + node.getValue() + "}");
        }
    }
}
