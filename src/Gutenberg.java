import java.io.IOException;

public class Gutenberg {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\grees\\Documents\\Codepath\\Gutenberg\\src\\JaneAusten.txt";
        String commonWordsPath = "C:\\Users\\grees\\Documents\\Codepath\\Gutenberg\\src\\1000Freq.txt";
        Utils util = new Utils(filePath, commonWordsPath);
//        int totalWords = util.getTotalNumberOfWords();
//        System.out.println("Total num of words : " + totalWords);
//        System.out.println("=========================");
//
//        int uniqueWords = util.getTotalUniqueWords();
//        System.out.println("Total num of unique words : " + uniqueWords);
//        System.out.println("=========================");
//
//        Map<String, Integer> top20Freq = util.get20MostFrequentWords();
//        System.out.println("Top 20 freq words : ");
//        int i=1;
//        for (Map.Entry<String, Integer> entry : top20Freq.entrySet()) {
//            System.out.println((i++)+". "+entry.getKey() + " " + entry.getValue());
//        }
//        System.out.println("=========================");
//        Map<String, Integer> top20LeastFrequent = util.top20LeastFrequent();;
//        i=1;
//        System.out.println("Top 20 Least Frequent words : ");
//        for (Map.Entry<String, Integer> entry : top20LeastFrequent.entrySet()) {
//            System.out.println((i++)+". "+entry.getKey() + " " + entry.getValue());
//        }
//        System.out.println("=========================");
//
//        Map<String, Integer> top20Interesting = util.get20MostInterestingWords();;
//        i=1;
//        System.out.println("Top 20 Interesting words : ");
//        for (Map.Entry<String, Integer> entry : top20Interesting.entrySet()) {
//            System.out.println((i++)+". "+entry.getKey() + " " + entry.getValue());
//        }
//        System.out.println("=========================");
//        String searchWord = "Bennet";
//        int[] frequencyInEachChapter = util.getFrequencyOfWord(searchWord);
//        for(int p=0;p<frequencyInEachChapter.length;p++){
//            System.out.print(" "+frequencyInEachChapter[p]+" ");
//        }

        System.out.println("=========================");
        String quote = "Happiness in marriage is entirely a matter of chance.";
        int chapterNum = util.getChapterOfQuote(quote);
        System.out.println("Quote : " + quote);
        System.out.println("Chapter number : " + chapterNum);

        System.out.println("=========================");

        System.out.println("Generated sentence : ");
        System.out.println(util.generateSentence());


    }

}
