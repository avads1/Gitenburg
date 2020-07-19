import java.io.*;
import java.util.*;

public class Utils {
    private String filePath;
    private String mostCommonFile;
    private String fullText;

    Utils(String fPath, String freqFile) throws IOException {
        filePath = fPath;
        mostCommonFile = freqFile;
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        StringBuilder sb = new StringBuilder();
        while ((st = br.readLine()) != null) {
            if(st.isEmpty()){
                continue;
            }
            sb.append(st.trim() + " ");
        }
        fullText = sb.toString();
    }

    public int getTotalNumberOfWords() throws IOException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int count = 0;
        while ((st = br.readLine()) != null) {
            st = st.trim();
            String[] arr = st.split("\\s+");
            if (arr.length == 1 && arr[0].isEmpty()) {
                continue;
            }
            count = count + arr.length;
        }
        return count;
    }

    public int getTotalUniqueWords() throws IOException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        HashSet<String> set = new HashSet<>();
        while ((st = br.readLine()) != null) {
            st = st.trim();
            String[] arr = st.split("\\s+");
            if (arr.length == 1 && arr[0].isEmpty()) {
                continue;
            }
            for (String s : arr) {
                set.add(s);
            }
        }
//        for(String s:set){
//            System.out.println(s+" | ");
//        }
        return set.size();
    }

    public Map<String, Integer> get20MostFrequentWords() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            st = st.trim();
            String[] arr = st.split("\\s+");
            if (arr.length == 1 && arr[0].isEmpty()) {
                continue;
            }
            for (String s : arr) {
                String[] strArr = s.split("[\\p{Punct}\\s]+");
//               // System.out.println(strArr.length+" | ");
                if (strArr.length == 1)
                    map.put(strArr[0], map.getOrDefault(strArr[0], 0) + 1);
//                if (strArr.length == 1)
//                    map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }

//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println("map " + entry.getKey() + " " + entry.getValue());
//        }

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

    public Map<String, Integer> get20MostInterestingWords() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> topCommonWords = getMostCommonWords();
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            st = st.trim();
            String[] arr = st.split("\\s+");
            if (arr.length == 1 && arr[0].isEmpty()) {
                continue;
            }
            for (String s : arr) {
                String[] strArr = s.split("[\\p{Punct}\\s]+");
//               // System.out.println(strArr.length+" | ");
                if (strArr.length == 1) {
                    if (!strArr[0].equals("I")) {
                        strArr[0] = strArr[0].toLowerCase();
                    }
                    if (!topCommonWords.contains(strArr[0]))
                        map.put(strArr[0].toLowerCase(), map.getOrDefault(strArr[0].toLowerCase(), 0) + 1);
                }

//                if (strArr.length == 1)
//                    map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        int i = 1;
        /*for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println((i++) + " map " + entry.getKey() + " " + entry.getValue());
        }*/

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

    private HashSet<String> getMostCommonWords() throws IOException {
        File file = new File(mostCommonFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        HashSet<String> set = new HashSet<>();
        while ((st = br.readLine()) != null) {
            set.add(st);
        }

//        for(String s:set){
//            System.out.println(s+"|");
//        }
        return set;
    }

    public Map<String, Integer> top20LeastFrequent() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
//        File file = new File(filePath);
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        String st;
//        while ((st = br.readLine()) != null) {
            String[] arr = fullText.split("\\s+");
//            if (arr.length == 1 && arr[0].isEmpty()) {
//                continue;
//            }
            for (String s : arr) {
                String[] strArr = s.split("[\\p{Punct}\\s]+");
//               // System.out.println(strArr.length+" | ");
                if (strArr.length == 1){
                    if(strArr[0].equals("Chapter")||strArr[0].matches("[0-9]+")){
                        continue;
                    }
                    map.put(strArr[0], map.getOrDefault(strArr[0], 0) + 1);
                }

            }
//        }

//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println("map " + entry.getKey() + " " + entry.getValue());
//        }

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

    private int getWordFreqInText(String currChapterText, String word) {
        String[] arr = currChapterText.split("[\\p{Punct}\\s]+");
        int count = 0;
        for (String s : arr) {
            if (s.trim().equalsIgnoreCase(word)) {
                count++;
            }
        }
        return count;
    }

    private String getTextOfChapter(int chapterNumber) throws IOException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        while ((st = br.readLine()) != null) {
            if (st.contains("Chapter " + chapterNumber)) {
                flag = true;
            }
            if (st.contains("Chapter " + (chapterNumber + 1))) {
                flag = false;
                return sb.toString();
            }
            if (flag) {
                sb.append(st.trim() + " ");
            }

        }
        return sb.toString();
    }

    private int getNumOfChapters() throws IOException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int count = 0;
        while ((st = br.readLine()) != null) {
            if (st.contains("Chapter ")) {
                count++;
            }
        }
        return count;
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

    private boolean doesQuoteExist(String currChapterText, String quote) {
        if (currChapterText.contains(quote))
            return true;
        return false;
    }

    public String generateSentence() throws IOException {
        String currWord = "the";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 19; i++) {
            String[] arr = getNextWordList(currWord);
            String nextWord = getRandomWord(arr);
            sb.append(nextWord);
            currWord = nextWord;
        }
        return sb.toString();
    }

    private String getRandomWord(String[] arr) {
        return null;
    }

    private String[] getNextWordList(String currWord) throws IOException {
        int numOfChapters = getNumOfChapters();
//        int occurrences = getWordFreqInText();
        for (int i = 0; i < numOfChapters; i++) {
            String currChapterText = getTextOfChapter(i + 1);
            getWordList(currChapterText, currWord);
        }
        return null;
    }

    private void getWordList(String currChapterText, String currWord) {

    }
}
