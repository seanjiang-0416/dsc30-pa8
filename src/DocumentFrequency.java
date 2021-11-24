/*
 * NAME: Zhixing Jiang
 * PID: A16400450
 */

import java.io.*;
import java.util.Scanner;
import java.util.*;
/**
 * DocumentFrequency class contains a constructor and
 * two methods -- numDocuments and query. This class
 * is built to find the frequency of a certain string
 * in all the documents within the file
 *
 * @author Zhixing Jiang
 * @since November 23, 2021
 */
public class DocumentFrequency {
    ArrayList <HashTable> document = new ArrayList<>();
    int numDocument = 0;
    /**
     * DocumentFrequency Constructor that converts documents in
     * the file on the path to an Arraylist of HashTable
     *
     * @throws IOException if file is not found
     */
    public DocumentFrequency(String path) throws IOException {
        /* TODO */
        File myObj = new File(path);
        String data = "";
        String [] arrOfStr;
        HashTable temp;
        Scanner myReader = new Scanner(myObj);
        while(myReader.hasNextLine()){
            temp = new HashTable();
            data = myReader.nextLine();
            arrOfStr = data.split(" ");
            for(String value: arrOfStr){
                temp.insert(value.toLowerCase());
            }
            document.add(temp);
            numDocument++;
        }
        myReader.close();
    }
    /**
     * Return the number of documents/lines
     *
     * @return the number of documents
     */
    public int numDocuments() {
        /* TODO */
        return numDocument;
    }
    /**
     * Return the number of documents/lines that
     * contains the word
     *
     * @return the number of documents that
     * contains word
     */
    public int query(String word) {
        /* TODO */
        int output = 0;
        for(HashTable table: document){
            if(table.lookup(word)){
                output++;
            }
        }
        return output;
    }

    public static void main(String args[]) throws IOException {
        DocumentFrequency test = new DocumentFrequency("src/files/test.txt");
        System.out.println(test.query("dsc30"));
    }

}
