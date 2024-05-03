package com.hakimen;

import com.hakimen.persistance.JPAInstance;

public class Main {
    public static void main(String[] args) {
        JPAInstance.INSTANCE.getManager();
    }
}