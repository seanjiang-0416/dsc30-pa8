/*
 * NAME: Zhixing Jiang
 * PID: A16400450
 */

import java.io.IOException;

/**
 * BloomFilterJunior class contains insert and lookup
 * methods and three other hash functions. It is much more
 * efficient, but it may contain false positive results.
 *
 * @author Zhixing
 * @since November 23, 2021
 */
public class BloomFilterJunior {

    /* Constants */
    private static final int MIN_INIT_CAPACITY = 50;
    private static final int BASE256_LEFT_SHIFT = 8;
    private static final int HORNERS_BASE = 27;
    private static final int HASH_NUM = 3;
    /* Instance variables */
    private boolean[] table;

    /**
     * BloomFilterJunior constructor that initialize
     * the table with the input capacity
     *
     */
    public BloomFilterJunior(int capacity) {
        /* TODO */
        if(capacity < MIN_INIT_CAPACITY){
            throw new IllegalArgumentException();
        }
        table = new boolean[capacity];
    }
    /**
     * Insert the value into the BloomFilterJunior
     */
    public void insert(String value) {
        /* TODO */
        if(value == null){
            throw new NullPointerException();
        }
        table[hashBase256(value)] = true;
        table[hashCRC(value)] = true;
        table[hashHorners(value)] = true;
    }
    /**
     * Check if the value exists in the BloomFilterJunior table
     *
     * @return true if three hash function all return true at the
     * corresponding index, false, if otherwise
     */
    public boolean lookup(String value) {
        /* TODO */
        return table[hashBase256(value)] && table[hashCRC(value)] &&
                table[hashHorners(value)];
    }

    /**
     * Base-256 hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashBase256(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = ((hash << BASE256_LEFT_SHIFT) + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }

    /**
     * Simplified CRC hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashCRC(String value) {
        /* TODO: Copy and paste from your HashTable */
        //Lecture Slide 22
        int hashValue = 0;
        for(int i = 0; i <value.length(); i++){
            int leftShiftedValue = hashValue << 5;

            int rightShiftedValue = hashValue >>> 27;

            hashValue = (leftShiftedValue | rightShiftedValue) ^ value.charAt(i);
        }
        return Math.abs(hashValue) % table.length;
    }

    /**
     * Horner's hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashHorners(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = (hash * HORNERS_BASE + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }

    public static void main(String args[]) throws IOException {
        BloomFilterJunior test = new BloomFilterJunior(60);
        test.insert("nice");
        System.out.println(test.lookup("nice"));

    }



}
