import java.io.*;
import java.util.*;

public class Utils {
    private String fullText;
    private String commonText;

    Utils(String text, String common) throws IOException {
        fullText = text;
        commonText = common;
    }

    /*******************************PUBLIC METHODS***********************************************/

    public int getTotalNumberOfWords(String text) throws IOException {
        int count = 0;
        String[] arr = text.split("\\s+");
        return arr.length;
    }

    public int getTotalUniqueWords(String text) throws IOException {
        HashSet<String> set = new HashSet<>();
        String[] arr = text.split("\\s+");
        for (String s : arr) {
            set.add(s);
        }
        return set.size();
    }

    public Map<String, Integer> get20MostFrequentWords(String text) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        text = text.trim();
        String[] arr = text.split("\\s+");
        for (String s : arr) {
            String[] strArr = s.split("[\\p{Punct}\\s]+");
            if (strArr.length == 1)
                map.put(strArr[0], map.getOrDefault(strArr[0], 0) + 1);
        }

        TreeMap<Integer, List<String>> treeMap = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String str = entry.getKey();
            int freq = entry.getValue();
            if (treeMap.containsKey(freq)) {
                List<String> list = treeMap.get(freq);
                list.add(str);
                treeMap.put(freq, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                treeMap.put(freq, list);
            }
        }
        int n = 20;
        map = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<String>> entry : treeMap.entrySet()) {
            if (n == 0) {
                break;
            }
            int freq = entry.getKey();
            List<String> list = entry.getValue();
            for (String word : list) {
                if (n == 0) {
                    break;
                }
                map.put(word, freq);
                n--;
            }
        }
        return map;
    }

    public Map<String, Integer> get20MostInterestingWords(String text) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> topCommonWords = getMostCommonWords();
        text = text.trim();
        String[] arr = text.split("\\s+");
        for (String s : arr) {
            String[] strArr = s.split("[\\p{Punct}\\s]+");
            if (strArr.length == 1) {
                if (!strArr[0].equals("I")) {
                    strArr[0] = strArr[0].toLowerCase();
                }
                if (!topCommonWords.contains(strArr[0]))
                    map.put(strArr[0].toLowerCase(), map.getOrDefault(strArr[0].toLowerCase(), 0) + 1);
            }
        }
        TreeMap<Integer, List<String>> treeMap = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String str = entry.getKey();
            int freq = entry.getValue();
            if (treeMap.containsKey(freq)) {
                List<String> list = treeMap.get(freq);
                list.add(str);
                treeMap.put(freq, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                treeMap.put(freq, list);
            }
        }
        int n = 20;
        map = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<String>> entry : treeMap.entrySet()) {
            if (n == 0) {
                break;
            }
            int freq = entry.getKey();
            List<String> list = entry.getValue();
            for (String word : list) {
                if (n == 0) {
                    break;
                }
                map.put(word, freq);
                n--;
            }
        }
        return map;
    }

    public Map<String, Integer> top20LeastFrequent() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        String[] arr = fullText.split("\\s+");
        for (String s : arr) {
            String[] strArr = s.split("[\\p{Punct}\\s]+");
            if (strArr.length == 1) {
                if (strArr[0].equals("Chapter") || strArr[0].matches("[0-9]+")) {
                    continue;
                }
                map.put(strArr[0], map.getOrDefault(strArr[0], 0) + 1);
            }
        }
        TreeMap<Integer, List<String>> treeMap = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String str = entry.getKey();
            int freq = entry.getValue();
            if (treeMap.containsKey(freq)) {
                List<String> list = treeMap.get(freq);
                list.add(str);
                treeMap.put(freq, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                treeMap.put(freq, list);
            }
        }
        int n = 20;
        map = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<String>> entry : treeMap.entrySet()) {
            if (n == 0) {
                break;
            }
            int freq = entry.getKey();
            List<String> list = entry.getValue();
            for (String word : list) {
                if (n == 0) {
                    break;
                }
                map.put(word, freq);
                n--;
            }
        }
        return map;
    }

    public int[] getFrequencyOfWord(String word) throws IOException {
        int numOfChapters = getNumOfChapters();
        int[] res = new int[numOfChapters];
        for (int i = 0; i < numOfChapters; i++) {
            String currChapterText = getTextOfChapter(i + 1);
            int occurrences = getWordFreqInText(currChapterText, word);
            res[i] = occurrences;
        }
        return res;
    }

    public int getChapterOfQuote(String quote) throws IOException {
        int numOfChapters = getNumOfChapters();
        int[] res = new int[numOfChapters];
        for (int i = 0; i < numOfChapters; i++) {
            String currChapterText = getTextOfChapter(i + 1);
            if (doesQuoteExist(currChapterText, quote)) {
                return i + 1;
            }
        }
        return -1;
    }


    public String generateSentence() throws IOException {
        String currWord = "the";
        StringBuilder sb = new StringBuilder();
        sb.append(currWord);
        for (int i = 0; i < 19; i++) {
            String[] arr = getNextWordList(currWord);
            String nextWord = getRandomWord(arr);
            //System.out.println(nextWord);
            sb.append(" ").append(nextWord);
            currWord = nextWord;
        }
        return sb.toString();
    }

    /**********************************HELPER FUNCTIONS***********************************************/

    private HashSet<String> getMostCommonWords() throws IOException {
        String[] arr = commonText.split("\\s+");
        HashSet<String> set = new HashSet<>();
        for (String st : arr) {
            set.add(st);
        }
        return set;
    }

    private int getWordFreqInText(String currChapterText, String word) {
        String[] arr = currChapterText.split("[\\p{Punct}\\s]+");
        int count = 0;
        for (String s : arr) {
            if (s.equalsIgnoreCase(word)) {
                count++;
            }
        }
        return count;
    }

    private String getTextOfChapter(int chapterNumber) throws IOException {
        int start = fullText.indexOf("Chapter " + (chapterNumber));
        int end = fullText.indexOf("Chapter " + (chapterNumber + 1));
        if (end == -1) {
            return "";
        }
        String chapterText = fullText.substring(fullText.indexOf("Chapter " + chapterNumber), fullText.indexOf("Chapter " + (chapterNumber + 1)));
        return chapterText;
    }

    private int getNumOfChapters() throws IOException {
        String[] arr = fullText.split("\\s+");
        int count = 0;
        for (String st : arr) {
            if (st.equalsIgnoreCase("Chapter")) {
                count++;
            }
        }
        return count;
    }


    private boolean doesQuoteExist(String currChapterText, String quote) {
        if (currChapterText.contains(quote))
            return true;
        return false;
    }


    private String getRandomWord(String[] arr) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s).append(" ");
        }
        Map<String, Integer> map = get20MostFrequentWords(sb.toString());
        String[] keyArr = new String[20];
        int p = 0;
        for (String s : map.keySet()) {
            keyArr[p++] = s;
        }
        int max = keyArr.length - 1;
        int min = 0;
        int index = (int) ((Math.random() * (max - min)) + min);
        return keyArr[index];
    }

    private String[] getNextWordList(String currWord) throws IOException {
        String[] arr = fullText.split("[\\p{Punct}\\s]+");
        int size = getWordFreqInText(fullText, currWord);
        String[] res = new String[size];
        int k = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].equalsIgnoreCase(currWord)) {
                res[k++] = arr[i + 1];
            }
        }
        return res;
    }

}
