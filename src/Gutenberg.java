import java.io.*;
import java.util.Map;

public class Gutenberg {
    public static void main(String[] args) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);
        String filePath = currentDirectory+"\\src\\JaneAusten.txt";
        String commonWordsPath = currentDirectory+"\\src\\1000Freq.txt";
        FileWriter fileWriter = new FileWriter(currentDirectory+"\\src\\output.txt");
        File file = new File(filePath);
        File commonWordFile = new File(commonWordsPath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        StringBuilder sb = new StringBuilder();
        while ((st = br.readLine()) != null) {
            if (st.isEmpty()) {
                continue;
            }
            sb.append(st.trim() + " ");
        }
        String fullText = sb.toString();
        fullText = fullText.replaceAll("[\uFEFF-\uFFFF]", "");
        br = new BufferedReader(new FileReader(commonWordFile));
        sb = new StringBuilder();
        while ((st = br.readLine()) != null) {
            if (st.isEmpty()) {
                continue;
            }
            sb.append(st.trim() + " ");
        }
        String commonText = sb.toString();
        Utils util = new Utils(fullText, commonText);

        int totalWords = util.getTotalNumberOfWords(fullText);
        fileWriter.write("Total num of words : " + totalWords+"\n");
        fileWriter.write("=========================\n");

        int uniqueWords = util.getTotalUniqueWords(fullText);
        fileWriter.write("Total num of unique words : " + uniqueWords+"\n");
        fileWriter.write("=========================\n");

        Map<String, Integer> top20Freq = util.get20MostFrequentWords(fullText);
        fileWriter.write("Top 20 freq words : \n");
        int i = 1;
        for (Map.Entry<String, Integer> entry : top20Freq.entrySet()) {
            fileWriter.write((i++) + ". " + entry.getKey() + " " + entry.getValue());
            fileWriter.write("\n");
        }
        fileWriter.write("=========================\n");
        Map<String, Integer> top20LeastFrequent = util.top20LeastFrequent();
        ;
        i = 1;
        fileWriter.write("Top 20 Least Frequent words : \n");
        for (Map.Entry<String, Integer> entry : top20LeastFrequent.entrySet()) {
            fileWriter.write((i++) + ". " + entry.getKey() + " " + entry.getValue());
            fileWriter.write("\n");
        }
        fileWriter.write("=========================\n");

        Map<String, Integer> top20Interesting = util.get20MostInterestingWords(fullText);
        ;
        i = 1;
        fileWriter.write("Top 20 Interesting words : \n");
        for (Map.Entry<String, Integer> entry : top20Interesting.entrySet()) {
            fileWriter.write((i++) + ". " + entry.getKey() + " " + entry.getValue());
            fileWriter.write("\n");
        }
        fileWriter.write("=========================\n");
        String searchWord = "Bennet";
        int[] frequencyInEachChapter = util.getFrequencyOfWord(searchWord);
        fileWriter.write("Frequency in each chapter : \n");
        for (int p = 0; p < frequencyInEachChapter.length; p++) {
            fileWriter.write(" " + frequencyInEachChapter[p] + " ");
        }
        fileWriter.write("\n");
        fileWriter.write("=========================\n");
        String quote = "Happiness in marriage is entirely a matter of chance.";
        int chapterNum = util.getChapterOfQuote(quote);
        fileWriter.write("Quote : " + quote+"\n");
        fileWriter.write("Chapter number : " + chapterNum+"\n");

        fileWriter.write("=========================\n");

        fileWriter.write("Generated sentence : \n");
        fileWriter.write(util.generateSentence());
        fileWriter.close();

    }

}
