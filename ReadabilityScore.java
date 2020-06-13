import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ReadabilityScore {
    public static void main(String[] args) {
        File file = new File(args[0]);
        String str = "";
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                str += scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + args[0]);
            return;
        }
        int sentences = str.split("[?!.]").length;
        int words = str.split("\\s").length;
        int characters = str.replaceAll("[\\s]", "").length();
        int syllable = 0;
        int polySyllable = 0;
        String[] wordArr = str.split("\\s");
        for (int i = 0; i < wordArr.length; i++) {
            int count = 0;
            String temp = wordArr[i];
            temp = temp.replaceAll("[\\W]", "");
            temp = temp.charAt(temp.length()-1) == 'e' ? temp.substring(0, temp.length()-1) : temp;
            temp = temp.replaceAll("[aeiouyAEIOUY]?[aeiouyAEIOUY]?[aeiouyAEIOUY]", "~");
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == '~') {
                    count++;
                }
            }
            polySyllable += count > 2 ? 1 : 0;
            syllable += count == 0 ? 1 : count;
        }
        System.out.println("The text is:");
        System.out.println(str + "\n");
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllable);
        System.out.println("Polysyllables: " + polySyllable);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "ARI":
                double score = 4.71 * characters/words + 0.5 * words/sentences - 21.43;
                int ariAge = getScore((double) Math.round(score));
                System.out.printf("Automated Readability Index: %.2f (about %d year olds).%n", score, ariAge);
                break;
            case "FK":
                score = 0.39 * words/sentences + 11.8 * syllable/words - 15.59;
                int fkAge = getScore((double) Math.round(score));
                System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d year olds).%n", score, fkAge);
                break;
            case "SMOG":
                score = 1.043 * Math.sqrt(polySyllable * 30.0/sentences) + 3.1291;
                int smogAge = getScore((double) Math.round(score));
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).%n", score, smogAge);
                break;
            case "CL":
                score = 0.0588 * ((double)characters/words)*100 - 0.296 *
                        ((double)sentences/words)*100 - 15.8;
                int clAge = getScore((double) Math.round(score));
                System.out.printf("Coleman–Liau index: %.2f (about %d year olds).%n", score, clAge);
                break;
            case "all":
                score = 4.71 * characters/words + 0.5 * words/sentences - 21.43;
                ariAge = getScore((double) Math.round(score));
                System.out.printf("Automated Readability Index: %.2f (about %d year olds).%n", score, ariAge);
                score = 0.39 * words/sentences + 11.8 * syllable/words - 15.59;
                fkAge = getScore((double) Math.round(score));
                System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d year olds).%n", score, fkAge);
                score = 1.043 * Math.sqrt(polySyllable * 30.0/sentences) + 3.1291;
                smogAge = getScore((double) Math.round(score));
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).%n", score, smogAge);
                score = 0.0588 * ((double)characters/words)*100 - 0.296 *
                        ((double)sentences/words)*100 - 15.8;
                clAge = getScore((double) Math.round(score));
                System.out.printf("Coleman–Liau index: %.2f (about %d year olds).%n", score, clAge);

                System.out.printf("\nThis text should be understood in average by %.2f year olds.", (double)(ariAge+fkAge+smogAge+clAge)/4);
        }
    }

    static int getScore(Double score) {
        if (score >= 1 && score < 2) {
           return 6;
        } else if (score >= 2 && score < 3) {
            return 7;
        } else if (score >= 3 && score < 4) {
            return 9;
        } else if (score >= 4 && score < 5) {
            return 10;
        } else if (score >= 5 && score < 6) {
            return 11;
        } else if (score >= 6 && score < 7) {
            return 12;
        } else if (score >= 7 && score < 8) {
            return 13;
        } else if (score >= 8 && score < 9) {
            return 14;
        } else if (score >= 9 && score < 10) {
            return 15;
        } else if (score >= 10 && score < 11) {
            return 16;
        } else if (score >= 11 && score < 12) {
            return 17;
        } else if (score >= 12 && score < 13) {
            return 18;
        } else if (score >= 13 && score < 14) {
            return 24;
        }
        return 0;
    }
}
