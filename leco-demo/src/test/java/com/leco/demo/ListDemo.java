package com.leco.demo;

import com.greg.leco.demo.entity.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author greg
 * @version 2022/2/14
 */
public class ListDemo {

    //@Test
    public void testAddAllEmpty() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        list.addAll(new ArrayList<>());
        System.out.println("done");
    }
    
    /**
     * 测试交并补差
     */
    //@Test
    public void testCollectionOperate() {
        List<Integer> list1 = Lists.newArrayList(1,2,3,4);
        List<Integer> list2 = Lists.newArrayList(3,4,5,6);

        // 差集，这种方式存在一个问题，改变了list1的元素内容
        // 如果不想改变list1的元素内容，可以用深拷贝，或者用apace的CollectionUtils.subtract(list1, list2);
        list1.removeAll(list2);
        System.out.println(list1);
    }

    /**
     * 测试ArrayList.addAll参数能否为null
     * 结论：不能
     */
    //@Test
    public void testArrayListAddAll() {
        List<User> users = Lists.newArrayList();
        List<User> users1 = null;
        users.addAll(users1);
        System.out.println(users);
    }

    /**
     * 测试foreach
     */
    //@Test
    public void testForEach() {
        List<Integer> list = Lists.newArrayList(1,2,3,4);
        list.remove(1);
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    public void testBase() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
    }

    public static void main(String[] args) {
        ListDemo listDemo = new ListDemo();
        listDemo.testForEach();
        HashMap<Integer,String> map = new HashMap<>();
        map.put(2,"222");
    }
}
