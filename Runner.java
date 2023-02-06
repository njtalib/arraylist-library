public class Runner {
    public static ArrayList<Book> collection;
    public static ArrayList<String> subjects;
    public static void main(String[] args) {
        // creates library
        createLibrary();
        // new library object and sends created library and valid subjects to the library object
        Library library = new Library(collection, subjects);
        String output = "";
        // print out menu of options until exit
        do {
            System.out.println(output);
            output = library.printMenu();
        } while (!output.equals("exit"));
        
    }

    // creates the new library with random inputs 
    public static void createLibrary() {
        collection = new ArrayList<Book>();
        subjects = new ArrayList<String>();
        // can only have 5 of each subject so create an arraylist with 5 of the 5 options
        for (int s=0; s<5; s++) {
            subjects.add("programming");
            subjects.add("data structures");
            subjects.add("algorithms");
            subjects.add("operating systems");
            subjects.add("gaming");
        }
        ArrayList<Integer> years = new ArrayList<Integer>();
        // can only have one of each of these years so an array list with one of each
        for (int y=1980; y<=2019; y++) {
            years.add(y);
        }
        // for each of the preset 20 books pick random
        for (int i=0; i<20; i++) {
            // random subject, but has to be out of correct number of options so minus i
            int randSub = (int) (Math.random() * (20-i));
            // take random num and get from arraylist
            String subject = subjects.get(randSub);
            // same for year
            int randYear = (int) (Math.random() * (39-i));
            int year = years.get(randYear);
            // pages is 50 - 1000 but can be duplicates
            int randPages = (int) (Math.random() * 950) + 50;
            // rating has to be a double to out of 100 and then divide by 10
            int r = (int) (Math.random() * 99) + 1;
            double randRate = r/10.0;
            // add with all correcy vals
            collection.add(i, new Book(("Book " + (i + 1)),subject, year, randPages, randRate)); 
            // remove from arraylist so no duplicates
            subjects.remove(randSub);
            years.remove(randYear);
        }
    }
}
