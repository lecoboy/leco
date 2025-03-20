package com.leco.demo;


import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author greg
 * @version 2022/2/25
 */
public class HashMapDemo {

    //@Test
    public void testResize() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);
        map.put("5",5);
        map.put("6",6);
        map.put("7",7);
        map.put("8",8);
        map.put("9",9);
        map.put("10",10);
        map.put("11",11);
        map.put("12",12);
        map.put("13",13);
        map.put("14",14);
        System.out.println(1645891200000L-1645891199999L);
    }

    //@Test
    public void testDeadLoop() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(17,1);
    }
}
