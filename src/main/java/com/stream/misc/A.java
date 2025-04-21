package com.stream.misc;

public interface A {

    default void print(){
        System.out.println("Printing A");
    }
}
