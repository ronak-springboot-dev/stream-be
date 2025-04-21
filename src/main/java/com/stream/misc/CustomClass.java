package com.stream.misc;

public class CustomClass implements A, B {


    @Override
    public void print() {
        B.super.print();
    }

    public static void main(String[] args) {
        CustomClass customClass = new CustomClass();
        customClass.print();
    }
}
