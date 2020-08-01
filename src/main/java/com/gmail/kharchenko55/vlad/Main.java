package com.gmail.kharchenko55.vlad;

public class Main {
    public static void main(String[] args) {
        LongMapImpl<String> map = new LongMapImpl<>();

        System.out.println("Is map empty? -> " + map.isEmpty());
        System.out.println("The map size -> " + map.size());

        for (int i = 0; i < 20; i++) {
            map.put(i, "Word_" + i);
        }

        System.out.println("\nLet`s fill the map with key from 0 to 19 and values Word_i:)\n ");

        System.out.println("Is map empty? -> " + map.isEmpty());
        System.out.println("The map size -> " + map.size());

        System.out.println("Get value by key 10 --> value: " + map.get(10));
        System.out.println("Is map contains values 'Word_10'? " + map.containsValue("Word_10"));
        System.out.println("Is map contains key 10? " + map.containsKey(10));
        System.out.println("Let`s remove key 10 ");
        map.remove(10);
        System.out.println("The map size -> " + map.size());
        System.out.println("Our map: ");
        map.print();
        System.out.println("And finally clear all map");

        map.clear();

        System.out.println("The map size -> " + map.size());
    }
}
