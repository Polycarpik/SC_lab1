package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] c = {"_processed.txt","_ws.txt"};
        String[] b = {"dostoevsky","remark","uliss"};

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                BigrammCounter a = new BigrammCounter(b[j] + c[i]);
                a.countingProbability();
                a.write("Letters_" + b[j] + c[i]);

            }
        }

    }

}
