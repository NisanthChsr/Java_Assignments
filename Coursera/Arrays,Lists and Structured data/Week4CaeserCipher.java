package Assignments2;

import edu.duke.*;
import java.util.*;

public class Week4CaeserCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;

    public Week4CaeserCipher(int key) {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }

    private char transformLetter(char c, String from, String to) {
        int idx = from.indexOf(c);
        if (idx != -1) {
            return to.charAt(idx);
        }
        return c;
    }

    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }

    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }

    private String transform(String input, String from, String to){
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            c = transformLetter(c, from, to);
            sb.setCharAt(i, c);
        }
        return sb.toString();
    }

    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet);
    }

    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet);
    }

    public String toString() {
        return "" + theKey;
    }

}


class Week4CaesarCracker {
    char mostCommon;

    public Week4CaesarCracker() {
        mostCommon = 'e';
    }

    public Week4CaesarCracker(char c) {
        mostCommon = c;
    }

    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++){
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }

    public int getKey(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos-maxDex);
        }
        return dkey;
    }

    public String decrypt(String encrypted){
        int key = getKey(encrypted);
        Week4CaeserCipher cc = new Week4CaeserCipher(key);
        return cc.decrypt(encrypted);

    }

}

class Week4VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedMessage = new StringBuilder();

        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slicedMessage.append(message.charAt(i));
        }

        return slicedMessage.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];

        for (int i = 0; i < klength; i++) {
            String slicedMessage = sliceString(encrypted, i, klength);
            Week4CaesarCracker cc = new Week4CaesarCracker();
            key[i] = cc.getKey(slicedMessage);
        }

        return key;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] key = tryKeyLength(encrypted, 5, 'e');
        Week4VigenereCipher vc = new Week4VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("Decrypted message = " + decrypted);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> wordList = new HashSet<String>();

        for (String word : fr.lines()) {
            word = word.toLowerCase();
            wordList.add(word);
        }

        return wordList;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] wordList = message.split("\\W+");
        int totalOccurences = 0;

        for (String word : wordList) {
            if (dictionary.contains(word.toLowerCase())) {
                totalOccurences++;
            }
        }

        return totalOccurences;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String finalMessage = "";
        int maxOccurences = Integer.MIN_VALUE;

        for (int i = 1; i <= 100; i++) {
            /*
             * Code for break vigenere with unknown key using english language:
             * int[] key = tryKeyLength(encrypted, i, 'e');
             */ 
            int[] key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            Week4VigenereCipher vc = new Week4VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int currOccurences = countWords(decrypted, dictionary);

            if (currOccurences > maxOccurences) {
                maxOccurences = currOccurences;
                finalMessage = decrypted;
            }
        }

        return finalMessage;
    }

    public void breakVigenereUnknownKey() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource dictResource = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictResource);
        String decrypted = breakForLanguage(encrypted, dictionary);
        System.out.println("Decrypted message = " + decrypted);
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charCounts = new HashMap<Character, Integer>();

        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);

                if (!charCounts.containsKey(currChar)) {
                    charCounts.put(currChar, 1);
                } else {
                    charCounts.put(currChar, charCounts.get(currChar) + 1);
                }
            }
        }

        char mostCommon = '\0';
        int maxOccurences = Integer.MIN_VALUE;

        for (char key : charCounts.keySet()) {
            int currCount = charCounts.get(key);

            if (currCount > maxOccurences) {
                maxOccurences = currCount;
                mostCommon = key;
            }
        }

        return mostCommon;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxOccurences = Integer.MIN_VALUE;
        String detectedLanguage = "";
        String decrypted = "";

        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);

            decrypted = breakForLanguage(encrypted, dictionary);
            int currentOccurences = countWords(decrypted, dictionary);

            if (currentOccurences > maxOccurences) {
                detectedLanguage = language;
                maxOccurences = currentOccurences;
            }
        }

        System.out.println("Language = " + detectedLanguage);
        System.out.println("Decrypted message = " + decrypted);
    }

    public void breakVigenereUnknownKeyAndLanguage() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String[] languageList = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        FileResource dictResource;

        for (String language : languageList) {
            dictResource = new FileResource("dictionaries/" + language);
            languages.put(language, readDictionary(dictResource));
            System.out.println("Finished reading " + language + " dictionary");
        }

        breakForAllLangs(encrypted, languages);
    }

}


class Week4VigenereCipher {
    Week4CaeserCipher[] ciphers;

    public VigenereCipher(int[] key) {
        ciphers = new Week4CaeserCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new Week4CaeserCipher(key[i]);
        }
    }

    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            Week4CaeserCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            Week4CaeserCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    public String toString() {
        return Arrays.toString(ciphers);
    }

}
