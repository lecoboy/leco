package com.greg.leco.demo;

public class SwitchDemo {
    /**
     * 两种情况switch都会报错
     * @param args
     */
    public static void main(String[] args) {
        Byte a = null;
        switch (a) {
            case 1: {
                System.out.println("1");
                break;
            }
            case 2: {
                System.out.println("2");
                break;
            }
            default: {
                System.out.println("default");
                break;
            }
        }
        System.out.println("done");
        //testa(a);
    }
    public static void testa(byte a) {
        switch (a) {
            case 1: {
                System.out.println("1111");
                break;
            }
            case 2: {
                System.out.println("22222");
                break;
            }
            default: {
                System.out.println("12121default");
                break;
            }
        }
    }
}
