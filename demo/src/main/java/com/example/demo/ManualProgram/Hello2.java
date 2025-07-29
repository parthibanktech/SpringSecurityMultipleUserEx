package com.example.demo.ManualProgram;

public class Hello2 {

    public int showCount(int a, int b)
    {
        int c = a * b;
        System.out.println(c);
       return showCount(a,b-1);
    }

    public static void main(String[] args) {
        //showCount(5,10);
    }
}
