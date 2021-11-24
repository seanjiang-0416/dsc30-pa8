/*
 * Name: Zhixing Jiang
 * PID: A16400450
 */

import java.util.Arrays;
import java.util.ArrayList;

/**
 * HashTable class implements the IHashTable interface
 * It contains basic methods that help
 * 
 * @author Zhixing Jiang
 * @since November 22, 2021
 */
public class HashTable implements IHashTable {

    /* the bridge for lazy deletion */
    private static final String BRIDGE = new String("[BRIDGE]".toCharArray());

    /* instance variables */
    private int size; // number of elements stored
    private String[] table; // data table

    private ArrayList <String> valid_elements;//keep track of valid elements
    private int num_rehash = 1;
    private double load_factor;
    private int num_collision;
    private ArrayList<Integer> rehash_stats;
    private ArrayList<Double> load_factor_stats;
    private ArrayList<Integer> num_collision_stats;
    /**
     * HashTable Constructor that takes
     * in a default capacity of 15 and instantiate
     * several necessary instance variables
     *
     */
    public HashTable() {
        /* TODO */
        this(15);
    }
    /**
     * HashTable Constructor that takes
     * in a capacity input and instantiate several
     * necessary instance variables
     *
     * @param capacity the initial capacity of the hashtable
     */
    public HashTable(int capacity) {
        /* TODO */
        if(capacity < 5){
            throw new IllegalArgumentException();
        }
        table = new String[capacity];
        size = 0;
        valid_elements = new ArrayList<String>();
        rehash_stats = new ArrayList<Integer>();
        load_factor_stats = new ArrayList<Double>();
        num_collision_stats = new ArrayList<Integer>();
    }

    /**
     * Insert the input value to the hashtable
     * and update the related instance variables
     *
     * @param value the value inserted to the table
     *
     * @return True, if value did not exist in the hashtable
     *          False, if otherwise
     */
    @Override
    public boolean insert(String value) {
        /* TODO */
        if(value == null){
            throw new NullPointerException();
        }
        double load_factor_checking = (double)size/capacity();
        //call rehash method when load factor is lower than 0.55
        if(load_factor_checking >= 0.55){
            load_factor = load_factor_checking;
            rehash();
        }
        //check if the element is in the table already
        for(String element: table){
            if(element == value){
                return false;
            }
        }
        int idx = hashString(value);
        //the loop will break when it encounters
        //either BRIDGE or null
        while(table[idx] != null){
            if(table[idx].equals(BRIDGE)){
                break;
            }
            if(idx == table.length-1){
                idx = 0;
            }
            else{
                idx++;
            }
            num_collision++;
        }
        table[idx] = value;
        size++;
        valid_elements.add(value);
        return true;
    }
    /**
     * Delete the input value in the hashtable
     * if it exists in the hashtable
     *
     * @param value the value needed to be removed
     *
     * @return true if it was a successful removal
     *          false if it was not
     */
    @Override
    public boolean delete(String value) {
        /* TODO */
        if(value == null){
            throw new NullPointerException();
        }
        int idx = hashString(value);
        //Stop the loop when it encounters null
        //because there is no point to continue
        while(table[idx] != null){
            if(table[idx].equals(value)){
                table[idx] = BRIDGE;
                size--;
                valid_elements.remove(value);
                return true;
            }
            if(idx == table.length-1){
                idx = 0;
            }
            else{
                idx++;
            }
        }
        return false;
    }

    /**
     * Look up the input value and check whether it
     * exists in the hashtable
     *
     * @param value the value needed to be checked
     *
     * @return true if it exists, false if otherwise
     */
    @Override
    public boolean lookup(String value) {
        /* TODO */
        if(value == null){
            throw new NullPointerException();
        }
        int idx = hashString(value);
        //Stop the loop when it encounters null
        //because there is no point to continue
        while(table[idx] != null){
            if(table[idx].equals(value)){
                return true;
            }
            if(idx == table.length-1){
                idx = 0;
            }
            else{
                idx++;
            }
        }
        return false;
    }

    /**
     * Find the number of elements in the table
     *
     * @return the size instance variable
     */
    @Override
    public int size() {
        /* TODO */
        return this.size;
    }
    /**
     * Find the capacity of the table
     *
     * @return the length of the table
     */
    @Override
    public int capacity() {
        /* TODO */
        return table.length;
    }
    /**
     * Get the statistics of the number of
     * rehash, the load factor before rehash,
     * and the number of collision before rehashing
     *
     * @return the summary of all data in a string
     */
    public String getStatsLog() {
        /* TODO */
        String output = "";
        for (int i = 0; i < rehash_stats.size(); i++) {
            output += String.format("Before rehashing # %d: load factor %.2f, %d collision(s)\n"
                    , rehash_stats.get(i), load_factor_stats.get(i), num_collision_stats.get(i));
        }

        return output;
    }

    /**
     * Helper method that helps rehash the hashtable when
     * the load factor is over 0.55. The method would create a
     * table with twice capacity and reinsert every element
     * in the order it was inserted before.
     */
    private void rehash() {
        /* TODO */
        //add all the data to their corresponding
        //arraylist before rehashing
        rehash_stats.add(num_rehash);
        num_collision_stats.add(num_collision);
        load_factor_stats.add(load_factor);
        //set up new environment
        int new_capacity = 2 * capacity();
        num_collision = 0;
        table = new String[new_capacity];
        //reinsert all the element back into this new table
        for(String element: valid_elements){
            int idx = hashString(element);
            while(table[idx] != null){
                if(idx == table.length-1){
                    idx = 0;
                }
                else{
                    idx++;
                }
                num_collision++;
            }
            table[idx] = element;
        }
        //increment the number of rehash
        num_rehash++;
    }
    /**
     * Helper method that hash a string and
     * return its corresponding hash value
     *
     * @param value the input value
     *
     * @return the hash value of the input value in integer
     */
    private int hashString(String value) {
        /* TODO */
        //Lecture Slide 22
        int hashValue = 0;
        for(int i = 0; i <value.length(); i++){
            //left shift
            int leftShiftedValue = hashValue << 5;
            //right shift
            int rightShiftedValue = hashValue >>> 27;
            // | is bitwise OR, ^ is bitwise XOR
            hashValue = (leftShiftedValue | rightShiftedValue) ^ value.charAt(i);
        }
        return Math.abs(hashValue) % capacity();
    }

    /**
     * Returns the string representation of the hash table.
     * This method internally uses the string representation of the table array.
     * DO NOT MODIFY. You can use it to test your code.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
