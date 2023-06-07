package com.ostangenberg.anagram;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnagramChecker {

    public List<List<String>> groupAnagrams(List<String> inputList) {
        Map<String, List<String>> groups = new LinkedHashMap<>();
        inputListLoop:
        for (String str : inputList) {
            for (Map.Entry<String, List<String>> groupEntry : groups.entrySet()) {
                final String groupReferenceString = groupEntry.getKey();
                if (isAnagram(groupReferenceString, str)) {
                    groupEntry.getValue().add(str);
                    continue inputListLoop;
                }
            }
            List<String> newGroupList = new ArrayList<>();
            newGroupList.add(str);
            groups.put(str, newGroupList);
        }

        return new ArrayList<>(groups.values());
    }

    public boolean areAllAnagrams(List<String> inputList) {
        if (inputList.size() < 2) {
            return false;
        }
        final String referenceString = inputList.get(0);
        for (int i = 1; i < inputList.size(); i++) {
            if (!isAnagram(referenceString, inputList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram(String str1, String str2) {
        final String str1Normalized = normalizeString(str1);
        final String str2Normalized = normalizeString(str2);

        if (str1Normalized.length() != str2Normalized.length()) {
            return false;
        }

        final char[] chars1 = str1Normalized.toCharArray();
        final char[] chars2 = str2Normalized.toCharArray();

        final List<Character> char1Pool = getCharacterList(chars1);

        for (char str2Char : chars2) {
            boolean found = char1Pool.remove((Character) str2Char);
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private String normalizeString(String str) {
        return str.toLowerCase().replaceAll("\\s", "");
    }

    private List<Character> getCharacterList(char[] chars) {
        final List<Character> char1Pool = new ArrayList<>(chars.length);
        for (char str1Char : chars) {
            char1Pool.add(str1Char);
        }
        return char1Pool;
    }
}
