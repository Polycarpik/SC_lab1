package com.company;

import java.io.*;
import java.util.*;

/**
 * Created by Polina on 3/11/14.
 */
public class Counter {

    private String textName;
    private String text;
    private int total;

    private class Element {

        private Element(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        Double probability;
        Integer value;
        String key;

    }

    public Counter(String textName) throws FileNotFoundException {
        this.textName = textName;
        this.text = "";
        Scanner in = new Scanner(new File(textName));
        while (in.hasNext()) {
            this.text += in.nextLine();
        }
        in.close();
    }

    private HashMap<String, Integer> counter(int step, int gramm) {
        HashMap<String, Integer> holder = new HashMap<String, Integer>();
        int length = this.text.length();
        for (int i = 0; i + 1 < length; i += step) {
            String bigramm = this.text.substring(i, i + gramm);
            if (!holder.containsKey(bigramm)) {
                holder.put(bigramm, 1);
            } else {
                int a = holder.remove(bigramm);
                holder.put(bigramm, a + 1);
            }

        }
        return holder;
    }

//    public Object[] getArrayFromHashMap(HashMap<String, Integer> holder) {
//        Set<Map.Entry<String, Integer>> representationsHashSet = new HashSet<Map.Entry<String, Integer>>();
//        representationsHashSet = holder.entrySet();
//        Object[] a = representationsHashSet.toArray();
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
//        return a;
//    }

    private List<Element> hashMapToArrayOfElements(HashMap<String, Integer> holder) {
        List<Element> elements = new ArrayList<Element>();
        Set<String> bigramms = holder.keySet();
        for (String a : bigramms) {
            this.total += holder.get(a);
            Element e = new Element(a, holder.get(a));
            elements.add(e);
        }
        total++;
        return elements;
    }

    private void countingProbability(List<Element> elements) {
        for (int i = 0; i < elements.size(); i++) {

            Element a = new Element(elements.get(i).key, elements.get(i).value);
            a.probability = (double) a.value / (total);
            elements.set(i, a);
        }
        Collections.sort(elements, new Comparator<Element>() {
            public int compare(Element o1, Element o2) {
                return -(o1.probability.compareTo(o2.probability));
            }
        });
        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i).key + " = " + elements.get(i).value);
            System.out.println(elements.get(i).key + " = " + elements.get(i).probability);

        }
        System.out.println("Total is " + total);
//        System.out.println("entropy = " + entropyCounter(elements));
    }

    private double entropyCounter(List<Element> elements, int gramm) {
        double entropy = 0;
        for (int i = 0; i < elements.size(); i++) {
            entropy += elements.get(i).probability * (Math.log(elements.get(i).probability) / Math.log(2));
        }
        return -entropy / gramm;
    }

    private void toFile(String addon,String fileName, List<Element> elements, int gramm) {
        try {
            PrintWriter out = new PrintWriter(new File(addon + fileName).getAbsoluteFile());
            try {
                for (int i = 0; i < elements.size(); i++) {
                    out.println(elements.get(i).key + " = " + elements.get(i).probability);
                }
                out.println("entropy = " + entropyCounter(elements, gramm));
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void letterCounter() {
        List<Element> elements = hashMapToArrayOfElements(counter(1, 1));
        countingProbability(elements);
        toFile("letters_", textName, elements, 1);
    }

    public void crossingBigrammsCounter() {
        List<Element> elements = hashMapToArrayOfElements(counter(1, 2));
        countingProbability(elements);
        toFile("crossingBigramms_", textName, elements, 2);

    }

    public void noncrossingBigrammsCounter() {
        List<Element> elements = hashMapToArrayOfElements(counter(2, 2));
        countingProbability(elements);
        toFile("noncrossingBigramms", textName, elements, 2);

    }

}
