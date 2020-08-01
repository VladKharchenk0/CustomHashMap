package com.gmail.kharchenko55.vlad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongMapImplTest {

    LongMapImpl<String> vocabulary;

    @Before
    public void fillTheMap() {
        vocabulary = new LongMapImpl<>();

        vocabulary.put(1, "One");
        vocabulary.put(2, "Two");
        vocabulary.put(17, "Seventeen");
        vocabulary.put(100, "One hundred");
    }

    @Test
    public void testPutMethod() {
        vocabulary.put(3, "Three");
        assertEquals(5, vocabulary.size());
    }

    @Test
    public void testGetMethod() {
        String seventeen = vocabulary.get(17);
        assertEquals("Seventeen", seventeen);
    }

    @Test(expected = NullPointerException.class)
    public void testGetMethodShouldReturnNPE() {
        vocabulary.get(999);
    }

    @Test
    public void testRemoveMethod() {
        vocabulary.remove(100);
        assertEquals(3, vocabulary.size());
    }

    @Test
    public void testEmptyMethodShouldReturnFalse() {
        Assert.assertFalse(vocabulary.isEmpty());
    }

    @Test
    public void testContainsKeyMethodShouldReturnTrue() {
        Assert.assertTrue(vocabulary.containsKey(100));
    }

    @Test
    public void testContainsValueMethodShouldReturnTrue() {
        Assert.assertTrue(vocabulary.containsValue("One"));
    }

    @Test
    public void testSizeMethod(){
        assertEquals(4, vocabulary.size());
    }

    @Test
    public void testClearMethod(){
        vocabulary.clear();
        assertEquals(0, vocabulary.size());
    }
}