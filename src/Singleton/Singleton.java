package Singleton;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Singleton {

    private static Singleton firstInstance = null;

    String[] scrabbleLetters = {"a", "a", "a", "a", "a", "a", "a", "a", "a",
            "b", "b", "c", "c", "d", "d", "d", "d", "e", "e", "e", "e", "e",
            "e", "e", "e", "e", "e", "e", "e", "f", "f", "g", "g", "g", "h",
            "h", "i", "i", "i", "i", "i", "i", "i", "i", "i", "j", "k", "l",
            "l", "l", "l", "m", "m", "n", "n", "n", "n", "n", "n", "o", "o",
            "o", "o", "o", "o", "o", "o", "p", "p", "q", "r", "r", "r", "r",
            "r", "r", "s", "s", "s", "s", "t", "t", "t", "t", "t", "t", "u",
            "u", "u", "u", "v", "v", "w", "w", "x", "y", "y", "z",};

    private LinkedList<String> letterList = new LinkedList<String> (Arrays.asList(scrabbleLetters));

    // Used to slow down 1st thread
    static boolean firstThread = true;

    // Created to keep users from instantiation
    // Only Singleton will be able to instantiate this class

    private Singleton() { }


    /*
    Different Ways to create Singleton:-

    There is a lazy and early initiliazation without static and method which are intuitive

    1.) Eager Initialization using static block
    static
     {
    // static block to initialize instance
    instance = new Singleton();
    }

    2.) BillPughSingleton
     // Inner class to provide instance of class
    private static class BillPughSingleton
    {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static GFG getInstance()
      {
      return BillPughSingleton.INSTANCE;
     }

Implementing singleton with enum is recommended by Effective Java. Creation of enum is thread-safe, it's guaranteed by JVM. Compared with other implementation, enum version has unique advantages:

It prevents creating another instance via reflection, although you can work around this in previous version by throwing exception in private constructor.
If you're dealing with a serializable singleton, as deserialization will always create a new instance, you have to discard it by implementing readResolve() method and handling singleton state carefully. But with enum singleton, serialization and deserialization is guaranteed by JVM.



     */

    // We could make getInstance a synchronized method to force
    // every thread to wait its turn. That way only one thread
    // can access a method at a time. This can really slow everything
    // down though
    // public static synchronized Singleton getInstance()

    public static Singleton getInstance() {
        if(firstInstance == null) {

            // This is here to test what happens if threads try
            // to create instances of this class

            if(firstThread){

                firstThread = false;

                try {
                    Thread.currentThread();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }

            // Here we just use synchronized when the first object
            // is created

            synchronized(Singleton.class){

                if(firstInstance == null) {
                    // If the instance isn't needed it isn't created
                    // This is known as lazy instantiation

                    firstInstance = new Singleton();

                    // Shuffle the letters in the list
                    Collections.shuffle(firstInstance.letterList);

                }

            }

        }

        // Under either circumstance this returns the instance

        return firstInstance;
    }

    public LinkedList<String> getLetterList(){

        return firstInstance.letterList;

    }

    public LinkedList<String> getTiles(int howManyTiles){

        // Tiles to be returned to the user

        LinkedList<String> tilesToSend = new LinkedList<String>();

        // Cycle through the LinkedList while adding the starting
        // Strings to the to be returned LinkedList while deleting
        // them from letterList

        for(int i = 0; i <= howManyTiles; i++){

            tilesToSend.add(firstInstance.letterList.remove(0));

        }

        // Return the number of letter tiles requested

        return tilesToSend;

    }

}


enum Singleton2{
    SINGLETON_2;

    String[] scrabbleLetters = {"a", "a", "a", "a", "a", "a", "a", "a", "a",
            "b", "b", "c", "c", "d", "d", "d", "d", "e", "e", "e", "e", "e",
            "e", "e", "e", "e", "e", "e", "e", "f", "f", "g", "g", "g", "h",
            "h", "i", "i", "i", "i", "i", "i", "i", "i", "i", "j", "k", "l",
            "l", "l", "l", "m", "m", "n", "n", "n", "n", "n", "n", "o", "o",
            "o", "o", "o", "o", "o", "o", "p", "p", "q", "r", "r", "r", "r",
            "r", "r", "s", "s", "s", "s", "t", "t", "t", "t", "t", "t", "u",
            "u", "u", "u", "v", "v", "w", "w", "x", "y", "y", "z",};

    private LinkedList<String> letterList = new LinkedList<String> (Arrays.asList(scrabbleLetters));
}