package com.company;

import org.omg.CORBA.INTERNAL;

import javax.lang.model.util.Elements;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Polina on 3/11/14.
 */
public class BigrammCounter {

    private String text;
    private int size;

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

    private class Element {


        private Element(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        Integer value;
        String key;

    }

    public List<Element> hashMapToArrayOfElements(HashMap<String, Integer> holder) {
        Set<String> bigramms = new HashSet<String>();
        List<Element> elementArray = new ArrayList<Element>();
        for (String a : bigramms) {
            this.size += holder.get(a);
            Element e = new Element(a, holder.get(a));
            elementArray.add(e);
        }
        return elementArray;
    }

    public void countingProbability(List<Element> elements) {
        for (int i = 0; i < elements.size(); i++) {
            elements.set(i, new Element(elements.get(i).key, elements.get(i).value / size));
        }
    }

    public void sortElementsByProbability(List<Element> elements) {
        Collections.sort(elements, new Comparator<Element>(){
            public int compare(Element o1, Element o2){
               return o1.value.compareTo(o2.value);
            }
        });

    }


    public Object[] getArrayFromHashMap(HashMap<String, Integer> holder) {
        Set<Map.Entry<String, Integer>> representationsHashSet = new HashSet<Map.Entry<String, Integer>>();
        representationsHashSet = holder.entrySet();
        Object[] a = representationsHashSet.toArray();
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        return a;
    }

}
