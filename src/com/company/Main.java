package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BigrammCounter a = new BigrammCounter();
        a.getNormalView(a.counter());
    }

}
