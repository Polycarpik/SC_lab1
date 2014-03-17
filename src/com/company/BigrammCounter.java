package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Polina on 3/11/14.
 */
public class BigrammCounter {

    private String text;

    public BigrammCounter() throws FileNotFoundException {
        this.text = "";
        Scanner in = new Scanner(new File("text"));
        while (in.hasNext()) {
            this.text += in.nextLine();
        }
        in.close();
    }

    public HashMap<String, Integer> counter() {
        HashMap<String, Integer> holder = new HashMap<String, Integer>();
        int length = this.text.length();
        for (int i = 1; i < length; i++) {
            String bigramm = this.text.substring(i - 1, i + 1);
            if (!holder.containsKey(bigramm)) {
                holder.put(bigramm, 1);
            } else {
                int a = holder.remove(bigramm);
                holder.put(bigramm, a + 1);
            }

        }
        return holder;
    }

    public void getNormalView(HashMap<String, Integer> holder) {
        Set<Map.Entry<String,Integer>> representationsHashSet = new HashSet<Map.Entry<String, Integer>>();
        representationsHashSet = holder.entrySet();
        Object[] a = representationsHashSet.toArray();
        for(int i = 0;i < a.length; i++ ){
            System.out.println(a[i]);
        }

    }

}
