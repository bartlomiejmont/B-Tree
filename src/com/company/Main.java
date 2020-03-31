package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class HuffmanNode {

    int data;
    char c;

    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {

        return x.data - y.data;
    }
}


public class Main {

    static int huffmanLength=0;

    public static void main(String[] args) throws IOException {

        String input = String.valueOf(Files.readString(Paths.get(".").resolve("input.txt"), StandardCharsets.UTF_8));

        Map<Integer, Integer> allCharsFrequencyMap = new HashMap<>();
        Map<Integer, Integer> finalFrequencyMap = new HashMap<>();

        for (int i = 0; i < 256; i++) {
            allCharsFrequencyMap.put(i, 0);
        }

        List<Character> inputChars = stringToCharList(input);

        inputChars.forEach(c -> allCharsFrequencyMap.replace((int) c, allCharsFrequencyMap.get((int) c) + 1));

        inputChars.stream().filter(c -> allCharsFrequencyMap.get((int) c) != 0)
                .forEach(c -> {
                    finalFrequencyMap.put((int) c, allCharsFrequencyMap.get((int) c));
                });

        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(inputChars.size(), new MyComparator());

        finalFrequencyMap.forEach((k, v) -> {
                    HuffmanNode hn = new HuffmanNode();
                    hn.c = (char)k.intValue();
                    hn.data = v;

                    hn.left = null;
                    hn.right = null;

                    q.add(hn);
                }
        );

        HuffmanNode root = null;

        while (q.size() > 1) {

            HuffmanNode x = q.peek();
            q.poll();

            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;

            root = f;

            q.add(f);
        }

        printCode(root, "", finalFrequencyMap);

        System.out.println("String length:" + input.length()*8 + " huffman lenght:" + huffmanLength);

    }

    private static List<Character> stringToCharList(String s) {
        List<Character> chars = new ArrayList<>();

        for (char ch : s.toCharArray()) {
            chars.add(ch);
        }
        return chars;
    }

    public static void printCode(HuffmanNode root, String s, Map map) {

        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            AtomicInteger huffmanSize= new AtomicInteger();
            System.out.println(root.c +":"+ map.get((int)root.c) + ":" + s);
            map.forEach((v,k) -> {
                huffmanSize.addAndGet(s.length());
            });
             huffmanLength += huffmanSize.intValue();
             return;
        }

        printCode(root.left, s + "0", map);
        printCode(root.right, s + "1", map);
    }

}
