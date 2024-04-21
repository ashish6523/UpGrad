package org.example;

public class SentimentAnalyzer {

    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        int[] results = new int[featureSet.length];

        for (int i = 0; i < featureSet.length; i++) {
            String[] featureWords = featureSet[i];
            int opinion = 0;
            for (String feature : featureWords) {
                int opinionOnFeature = getOpinionOnFeature(review, feature, posOpinionWords, negOpinionWords);
                if (opinionOnFeature != 0) {
                    opinion = opinionOnFeature;
                    break;
                }
            }
            results[i] = opinion;
        }

        return results;
    }

    public static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int wasPhrasePattern = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
        if (wasPhrasePattern != 0) {
            return wasPhrasePattern;
        }

        int opinionFirstPattern = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
        if (opinionFirstPattern != 0) {
            return opinionFirstPattern;
        }

        return 0;
    }

    public static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        // Check for pattern
        String pattern = feature + " was ";
        for (String posOpinion : posOpinionWords) {
            if (review.contains(pattern + posOpinion)) {
                return 1; // Positive opinion
            }
        }
        for (String negOpinion : negOpinionWords) {
            if (review.contains(pattern + negOpinion)) {
                return -1; // Negative opinion
            }
        }
        return 0; // No opinion found
    }

    public static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        // Check for pattern
        for (String posOpinion : posOpinionWords) {
            if (review.contains(posOpinion + " " + feature)) {
                return 1; // Positive opinion
            }
        }
        for (String negOpinion : negOpinionWords) {
            if (review.contains(negOpinion + " " + feature)) {
                return -1; // Negative opinion
            }
        }
        return 0; // No opinion found
    }

    public static void main(String[] args) {
        String review = "This place has delicious food, although bad ambiance also unprofessional staff";

        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress", "bartender", "staff", "server"}
        };

        String[] posOpinionWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious"};

        String[] negOpinionWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

        int[] results = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);

        // Output results
        System.out.println("Results:");
        for (int i = 0; i < featureSet.length; i++) {
            System.out.println(featureSet[i][0] + ": " + results[i]);
        }
    }
}
