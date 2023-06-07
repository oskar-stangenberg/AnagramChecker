package com.ostangenberg.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private enum Mode {
        GROUP, ALL
    }

    public static void main(String[] args) {
        Mode mode = Mode.ALL;
        List<String> inputList = new ArrayList<>(Arrays.asList(args));
        if(args.length > 0) {
            final String firstArg = args[0];
            if("-group".equalsIgnoreCase(firstArg)) {
                mode = Mode.GROUP;
                inputList.remove(0);
            }
        }
        if(inputList.size() < 2) {
            System.err.println("At least two words/phrases should be provided");
            System.exit(-1);
        }

        AnagramChecker anagramChecker = new AnagramChecker();

        if(mode == Mode.ALL) {
            boolean success = anagramChecker.areAllAnagrams(inputList);
            if(success) {
                System.out.println("All provided words are anagrams of each other");
                System.exit(0);
            } else {
                System.out.println("Not all provided words are anagrams of each other");
                System.exit(1);
            }
        } else {
            final List<List<String>> groups = anagramChecker.groupAnagrams(inputList);
            System.out.println(groups.size()+" groups found");
            for (int i = 0; i < groups.size(); i++) {
                List<String> group = groups.get(i);
                System.out.println("# Group "+(i+1)+":");
                for (String str : group) {
                    System.out.println(str);
                }
            }
            System.exit(0);
        }
    }
}