package com.ostangenberg.anagram;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AnagramCheckerTest {

    private final AnagramChecker anagramChecker = new AnagramChecker();

    @Test
    void testSingleAnagram() {
        assertTrue(anagramChecker.isAnagram("abcd", "dcba"));
        assertTrue(anagramChecker.isAnagram("123", "3 1 2"));
        assertTrue(anagramChecker.isAnagram("eleven plus two", "twelve plus one"));
        assertTrue(anagramChecker.isAnagram("forty five", "over fifty"));
        assertTrue(anagramChecker.isAnagram("New York Times", "monkeys write"));

        assertFalse(anagramChecker.isAnagram("Wrong", "Right"));
        assertFalse(anagramChecker.isAnagram("Red", "Blue"));
    }

    @Test
    void testMultiAnagram() {
        assertTrue(anagramChecker.areAllAnagrams(Arrays.asList("abcd", "bcda", "acb d", "bad c")));
        assertFalse(anagramChecker.areAllAnagrams(Arrays.asList("abcd", "bcda", "acb d", "ddd d")));
        assertFalse(anagramChecker.areAllAnagrams(Arrays.asList("abcd", "bcda", "acb d", "1234")));
    }

    @Test
    void testAnagramGrouping() {
        final List<List<String>> groups = anagramChecker.groupAnagrams(Arrays.asList("abcd", "b cda", "1234", "dbac", "4321", "xxx"));
        assertEquals(3, groups.size());
        assertEquals(3, groups.get(0).size());
        assertEquals(2, groups.get(1).size());
        assertEquals(1, groups.get(2).size());
        assertArrayEquals(new String[]{"abcd", "b cda", "dbac"}, groups.get(0).toArray());
        assertArrayEquals(new String[]{"1234", "4321"}, groups.get(1).toArray());
        assertArrayEquals(new String[]{"xxx"}, groups.get(2).toArray());
    }
}
