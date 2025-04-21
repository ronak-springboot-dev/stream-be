package com.stream.misc;

public interface B {
    default void print() {
        System.out.println("Printing B");
    }
}
