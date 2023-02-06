import java.util.Scanner;
public class Library {
    private ArrayList<Book> collection;
    private ArrayList<String> subjects;

    // only constructor to fill in the arraylist "collection" with the preset books created in the method in runner
    public Library(ArrayList<Book> c, ArrayList<String> s) {
        collection = c;
        subjects = s;
    }

    // main method called by runner.java
    public String printMenu() {
        // create new Scanner object called scan
        Scanner scan = new Scanner(System.in);
        // actually print out menu 
        System.out.println("1. List all books\n2. Sort books by publication year\n3. Sort books by book length\n4. Sort books by rating\n5. List books from subject\n6. Search for book by name\n7. Add book to list\n8. Exit");
        // store input in input
        int input = scan.nextInt();
        scan.nextLine();
        // different options depending on what the input is
        if (input == 1) {
            // if its 1 that means print books so call the print books method
            return printBooks(collection, "all");
        }
        else if (input == 2) {
            // if its 2 that means sort by year so yearSort method
            return yearSort();
        }
        else if (input == 3) {
            // if its 3 that means sort by length so lengthSort method
            return lengthSort();
        }
        else if (input == 4) {
            // if its 4 that means sort by rating so ratingSort method
            return ratingSort();
        }
        else if (input == 5) {
            // if its 5 that means sort by subject; initialize the subject
            String subject = "";
            // do while loop to make sure that the subject inputed is valid
            do {
                // create a string of the possible subjects to tell the user
                String listSub = "";
                // for loop to loop through subjects and add to string with commas in between
                for (int i=0; i<subjects.getSize()-1; i++) {
                    listSub += subjects.get(i) + ", ";
                }
                // add last item without comma
                listSub += subjects.get(subjects.getSize()-1);
                // print out subjects for users to see
                System.out.print("The list of subjects are " + listSub + "\nChoose Subject: ");
                // keep track of what they input in subject
                subject = scan.nextLine();
                subject = subject.toLowerCase();
                // if the subject is exit end code completely
                if (subject.equals("exit")) {
                    return "exit";
                }
                // keep repeating until the subject inputed is valid
            } while(!subjectCheck(subject));
            // method subjectSort
            return subjectSort(subject);
        }
        else if (input == 6) {
            // if its 6 that means search for book by name 
            String name = "";
            String item;
            // make sure that the name is valid
            do {
                System.out.print("Input Name: ");
                name = scan.nextLine();
                // use method get book to atempt to extract the book
                item = getBook(name);
                if (item.equals("exit")) {
                    break;
                }
                // if the book is invalid, getBook will return invalid and this while loop will repeat until a valid book is found
            } while(item.equals("invalid"));
            return item;
        }
        else if (input == 7) {
            // if its 7 that means a book needs to be added
            // ask for title aand subject and hold those in variaables 
            System.out.print("Input Title: ");
            String title = scan.nextLine();
            System.out.print("Input Subject: ");
            String subject = scan.nextLine();
            // initialize year variable and hold a varaible running to keep the while loop running
            int year = 0;
            Boolean running = true;
            do {
                try {
                    // try to make variable year as the input, but if the next int is not a number 
                    // it will go to the catch and restart while loop
                    System.out.print("Input Publication Year: ");
                    year = scan.nextInt();
                    scan.nextLine();
                    // if it goes through, make sure that the number is within the correct time range, is between 0 and 2023
                    if (year > 0 && year <= 2023) {
                        running = false;
                    }
                    else {
                        System.out.println("invalid publication year");
                    }
                }
                catch (Exception e) {
                    scan.nextLine();
                }
            } while (running);
            // repeat same thing from publication year for total pages; initialize varaible
            running  = true;
            int pages = 0;
            do {
                try {
                    // make sure that its an int and if not catch and redo
                    System.out.print("Input Total Pages: ");
                    pages = scan.nextInt();
                    scan.nextLine();
                    // make sure page amoutn is valid
                    if (pages > 0) {
                        running = false;
                    }
                    else {
                        System.out.println("invalid page number");
                    }
                }
                catch (Exception e) {
                    scan.nextLine();
                }
            } while (running);
            // repeat again for rating; initilizae variables
            running  = true;
            double rating = 0;
            do {
                try {
                    // make sure that its a number if not catch and rerun
                    System.out.print("Input Rating: ");
                    rating = scan.nextDouble();
                    scan.nextLine();
                    // make sure that its a valid rating
                    if (rating >= 0.1 && rating <= 10.0) {
                        running = false;
                    }
                    else {
                        System.out.println("invalid rating");
                    }
                }
                catch (Exception e) {
                    scan.nextLine();
                }
            } while (running);
            collection.add(new Book(title, subject, year, pages, rating));
            return ("");

        }
        // if 8 completly end code
        else if (input == 8) {
            return "exit";
        }
        return "";
    }

    // print out all books method
    // also print out the sorted lists with only the selected type printed out
    public String printBooks(ArrayList<Book> sorted, String type) {
        // keep track 
        String output = "";
        for (int i=0; i<sorted.getSize(); i++) {
            Book item = sorted.get(i);
            // always print out the title
            output += " Title: " + item.getTitle();
            // if its a year type then only print out the year, if its an all types then print this as well
            if (type.equals("year") || type.equals("all")) {
                 output += "; Year of Publication: " + item.getYear();
            }
            if (type.equals("page") || type.equals("all")) {
                output += "; Total Pages: " + item.getPages();
            }
            if (type.equals("rating") || type.equals("all")) {
                output += "; Rating: " + item.getRating();
            }
            if (type.equals("subject") || type.equals("all")) {
                output += "; Subject: " + item.getSubject();
            }
            output += "\n";
        }

        return output;
        
    }

    // sort by year
    public String yearSort() {
        ArrayList<Book> yearSorted = collection;
        // repeat this as many times as length of the array
        for (int j=0; j<collection.getSize(); j++) {
            // loop through the array list until one less than the size in order to prevent index error
            for (int i=0; i<collection.getSize()-1; i++) {
                Book current = collection.get(i);
                Book next = collection.get(i+1);
                // if the number is greater than the next switch them to bring the highest to the end and continue on
                if (current.getYear() > next.getYear()) {
                    yearSorted.set(i, next);
                    yearSorted.set(i+1, current);
                }
            }
        }
        
        // print with type year
        return printBooks(yearSorted, "year");
    }
    
    // same sort of sort as the previous
    // loop through until one less and switch if greater than 
    // repeat that to make sure that you contineu to move the longest to the end
    public String lengthSort() {
        ArrayList<Book> lengthSorted = collection;
        for (int j=0; j<collection.getSize(); j++) {
            for (int i=0; i<collection.getSize()-1; i++) {
                Book current = collection.get(i);
                Book next = collection.get(i+1);
                if (current.getPages() > next.getPages()) {
                    lengthSorted.set(i, next);
                    lengthSorted.set(i+1, current);
                }
            }
        }

        // print with type page
        return printBooks(lengthSorted, "page");
    }
    // same sort of loop as the one before but backwards
    // bring lowest to the end
    public String ratingSort() {
        ArrayList<Book> ratingSorted = collection;
        for (int j=0; j<collection.getSize(); j++) {
            for (int i=0; i<collection.getSize()-1; i++) {
            Book current = collection.get(i);
            Book next = collection.get(i+1);
            if (current.getRating() < next.getRating()) {
                ratingSorted.set(i, next);
                ratingSorted.set(i+1, current);
            }
        }
        }
        

        return printBooks(ratingSorted, "rating");
    }
    // check by looping through the index to check if the subject is one of the subjects
    public Boolean subjectCheck(String subject) {
        
        for (int i=0; i<subjects.getSize(); i++) {
            if (subjects.get(i).equals(subject)) {
                return true;
            }
        }
        return false;
    }
    // sort through the subjects and add it to subject sorted arraylist if it has the attribute with the right subject
    public String subjectSort(String subject) {
        ArrayList<Book> subjectSorted = new ArrayList<Book>();
        for (int i=0; i<collection.getSize(); i++) {
            Book item = collection.get(i);
            if (item.getSubject().equals(subject)) {
                subjectSorted.add(item);
            }
        }

        // print books with type subject
        return printBooks(subjectSorted, "subject");
    } 

    // get book just goes through the collection and checks for the correct book
    // if it is it will send it to print books with type all otherwise will be invalid and loop through
    // asking the user to try a different name
    public String getBook(String name) {
        ArrayList<Book> correctBook = new ArrayList<Book>();
        for (int i=0; i<collection.getSize(); i++) {
            Book item = collection.get(i);
            if (item.getTitle().toLowerCase().equals(name.toLowerCase())) {
                correctBook.add(item);
                return printBooks(correctBook, "all");
            }
        }
        return "invalid";
    }
    
}
