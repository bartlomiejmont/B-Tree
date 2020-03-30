package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        String input = String.valueOf(Files.readString(Paths.get(".").resolve("input.txt"), StandardCharsets.UTF_8));
        System.out.print(input);

        Map <Integer,Integer> allCharsRarityMap = new HashMap<>();
        for(int i=0; i<256; i++){
            allCharsRarityMap.put(i,0);
        }

    }
}
