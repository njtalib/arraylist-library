public class Book {
    private String title;
    private String subject;
    private int year;
    private int pages;
    private double rating;

    // only constructor to keep track of the title, subject, year, pages, and rating 
    public Book(String t, String s, int y, int p, double r) {
        this.title = t;
        this.subject = s;
        this.year = y;
        this.pages = p;
        this.rating = r;
    }

    // accessor method to get title
    public String getTitle() {
        return this.title;
    }

    // accessor method to get subject
    public String getSubject() {
        return this.subject;
    }

    // accessor method to get publication year
    public int getYear() {
        return this.year;
    }

    // accessor method to get total page number
    public int getPages() {
        return this.pages;
    }

    // accessor method to get rating
    public double getRating() {
        return this.rating;
    }
}
