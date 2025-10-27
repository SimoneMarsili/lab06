package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.Set;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private final static int ELEMS = 100000;
    private final static int READING_TIME = 1000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */

        List<Integer> numeri = new ArrayList<>();
        for (int i = 1000; i < 2000; i++) {
            numeri.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */

        List<Integer> veloce = new LinkedList<>(numeri);

        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */

        int tmp = numeri.get(0);
        numeri.set(0, numeri.get(numeri.size()-1));
        numeri.set(numeri.size() - 1, tmp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */

        for(final int elem : numeri) {
            System.out.println(elem);
        }

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long time = System.nanoTime();
        
        for (int i = 1; i <= UseListsAndMaps.ELEMS; i++) {
            numeri.add(0,i);
        }
        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);

        System.out.println(// NOPMD
            "Adding "
                + ELEMS
                + " ints into head in ArrayList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );

        time = System.nanoTime();
        
        for (int i = 1; i <= UseListsAndMaps.ELEMS; i++) {
            veloce.add(0,i);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);

        System.out.println(// NOPMD
            "Adding "
                + ELEMS
                + " ints into head in LinkedList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );



        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        int idx = numeri.size() / 2;
        time = System.nanoTime();
        
        for (int i = 1; i <= UseListsAndMaps.READING_TIME; i++) {
            numeri.get(idx);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);

        System.out.println(// NOPMD
            "Reading  "
                + READING_TIME
                + " times the middle in ArrayList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );


        time = System.nanoTime();
        
        for (int i = 1; i <= UseListsAndMaps.READING_TIME; i++) {
            veloce.get(idx);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);

        System.out.println(// NOPMD
            "Reading  "
                + READING_TIME
                + " times the middle in LinkedList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );



        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */

        Map<String,Long> mappa = new HashMap<>();
        mappa.put("Africa", 1110635000l);
        mappa.put("Americas", 972005000l);
        mappa.put("Antartica", 0l);
        mappa.put("Asia", 4298723000l);
        mappa.put("Europe", 742452000l);
        mappa.put("Oceania", 38304000l);

        /*
         * 8) Compute the population of the world
         */

        Long sum = 0l;
        final Set<String> keys = mappa.keySet();
        for(final String elem : keys) {
            sum += mappa.get(elem);
        }

        System.out.println(sum);
    }
}
