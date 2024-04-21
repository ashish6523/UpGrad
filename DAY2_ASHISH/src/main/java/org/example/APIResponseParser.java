package org.example;
import org.example.Book;

public class APIResponseParser {

    public static Book parse(String response) {
        Book book = new Book();

        // Parse title
        String title = parse(response, "<title>", "</title>");
        book.setTitle(title);

        // Parse author name
        String authorName = parse(response, "<author>", "</author>");
        book.setAuthorName(authorName);

        // Parse publication year
        String publicationYearStr = parse(response, "<original_publication_year type=\"integer\">", "</original_publication_year>");
        if (publicationYearStr != null) {
            int publicationYear = Integer.parseInt(publicationYearStr);
            book.setPublicationYear(publicationYear);
        }

        // Parse average rating
        String averageRatingStr = parse(response, "<average_rating>", "</average_rating>");
        if (averageRatingStr != null) {
            double averageRating = Double.parseDouble(averageRatingStr);
            book.setAverageRating(averageRating);
        }

        // Parse ratings count
        String ratingsCountStr = parse(response, "<ratings_count type=\"integer\">", "</ratings_count>");
        if (ratingsCountStr != null) {
            // Remove commas from ratings count string before parsing
            ratingsCountStr = ratingsCountStr.replaceAll(",", "");
            int ratingsCount = Integer.parseInt(ratingsCountStr);
            book.setRatingsCount(ratingsCount);
        }

        // Parse image url
        String imageUrl = parse(response, "<image_url>", "</image_url>");
        book.setImageUrl(imageUrl);

        return book;
    }

    private static String parse(String response, String startRule, String endRule) {
        int startIndex = response.indexOf(startRule);
        if (startIndex == -1) {
            return null;
        }
        startIndex += startRule.length();
        int endIndex = response.indexOf(endRule, startIndex);
        if (endIndex == -1) {
            return null;
        }
        return response.substring(startIndex, endIndex);
    }

    public static void main(String[] args) {
        // Sample XML response
        String response = "<GoodreadsResponse>\n" +
                "  <book>\n" +
                "    <title>Walden</title>\n" +
                "    <author>\n" +
                "      <name>Henry David Thoreau</name>\n" +
                "    </author>\n" +
                "    <original_publication_year type=\"integer\">1854</original_publication_year>\n" +
                "    <average_rating>3.80</average_rating>\n" +
                "    <ratings_count type=\"integer\">116315</ratings_count>\n" +
                "    <image_url>https://images.gr-assets.com/books/1465675526m/16902.jpg</image_url>\n" +
                "  </book>\n" +
                "</GoodreadsResponse>";

        // Parse response and create book instance
        Book book = APIResponseParser.parse(response);

        // Output book details
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author Name: " + book.getAuthorName());
        System.out.println("Publication Year: " + book.getPublicationYear());
        System.out.println("Average Rating: " + book.getAverageRating());
        System.out.println("Ratings Count: " + book.getRatingsCount());
        System.out.println("Image URL: " + book.getImageUrl());
    }
}

