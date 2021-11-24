import org.junit.*;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

public class HashTableTest {

    HashTable test_1;
    HashTable test_2;
    HashTable test_3;
    @Before
    public void testConstructor_1(){
        test_1 = new HashTable();
        assertEquals(15,test_1.capacity());
        assertEquals(0, test_1.size());

    }
    @Test
    public void testConstructor_2(){
        test_2 = new HashTable(10);
        assertEquals(10,test_2.capacity());
        assertEquals(0, test_2.size());
    }

    @Test
    public void testConstructor_3(){
        test_3 = new HashTable(5);
        assertEquals(5,test_3.capacity());
        assertEquals(0, test_3.size());
    }

    @Test
    public void testConstructor_4(){
        HashTable test_4 = new HashTable(520);
        assertEquals(520,test_4.capacity());
        assertEquals(0, test_4.size());
    }
    @Test(expected = IllegalArgumentException.class)
    public void exception_1(){
        HashTable test_4 = new HashTable(4);
    }
    @Test
    public void test_insert1(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("cat");
        test_3.insert("man");
        test_3.insert("funny");
        String[] expected = {"man", "Nice", null, null, null,
                "funny", null, null, "cat", "Dang"};
        assertEquals(Arrays.toString(expected), test_3.toString());
    }

    @Test
    public void test_insert2(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("Dang");
        String[] expected = {null, "Nice", null, null, "Dang"};
        assertEquals(Arrays.toString(expected), test_3.toString());
    }

    @Test
    public void test_insert3(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("cat");
        assertFalse(test_3.insert("Nice"));
        test_3.insert("man");
        test_3.insert("funny");
        test_3.delete("man");
        test_3.insert("man");
        String[] expected = {"man", "Nice", null, null, null,
                "funny", null, null, "cat", "Dang"};
        assertEquals(Arrays.toString(expected), test_3.toString());
    }

    @Test
    public void test_delete_1(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("cat");
        test_3.insert("man");
        test_3.insert("funny");
        test_3.delete("man");
        String[] expected = {"[BRIDGE]", "Nice", null, null, null,
                "funny", null, null, "cat", "Dang"};
        assertEquals(Arrays.toString(expected), test_3.toString());

    }
    @Test
    public void test_delete_2(){
        test_3 = new HashTable(10);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("Bang");
        test_3.insert("Sad");
        assertFalse(test_3.delete("nothing"));
        test_3.delete("Nice");
        test_3.delete("Dang");
        test_3.delete("Bang");
        test_3.delete("Sad");
        String[] expected = {null, "[BRIDGE]", "[BRIDGE]", null,
                null, null, null, null, "[BRIDGE]", "[BRIDGE]"};
        assertEquals(Arrays.toString(expected), test_3.toString());
        test_3.insert("Nice");
        String[] expected_1 = {null, "Nice", "[BRIDGE]", null,
                null, null, null, null, "[BRIDGE]", "[BRIDGE]"};
        assertEquals(Arrays.toString(expected_1), test_3.toString());
    }

    @Test
    public void test_delete_3(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.delete("Nice");
        String[] expected = {null, "[BRIDGE]", null, null, null};
        assertEquals(Arrays.toString(expected), test_3.toString());
        test_3.insert("Nice");
        String[] expected_1 = {null,"Nice",null,null,null};
        assertEquals(Arrays.toString(expected_1), test_3.toString());
    }

    @Test
    public void test_lookup1(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        assertTrue(test_3.lookup("Nice"));
    }

    @Test
    public void test_lookup2(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("cat");
        test_3.insert("man");
        test_3.insert("funny");
        test_3.delete("man");
        assertTrue(test_3.lookup("funny"));
    }

    @Test
    public void test_lookup3(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("cat");
        test_3.insert("man");
        test_3.insert("funny");
        test_3.delete("man");
        assertFalse(test_3.lookup("man"));
    }

    @Test
    public void test_size1(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("cat");
        test_3.insert("man");
        test_3.insert("funny");
        test_3.delete("man");
        assertEquals(4,test_3.size());
    }

    @Test
    public void test_size2(){
        test_3 = new HashTable(5);
        test_3.insert("man");
        test_3.delete("man");
        assertEquals(0,test_3.size());
    }

    @Test
    public void test_size3(){
        test_3 = new HashTable(5);
        test_3.insert("man");
        test_3.insert("man");
        assertEquals(1,test_3.size());
    }

    @Test
    public void test_capacity1(){
        test_3 = new HashTable(5);
        assertEquals(5,test_3.capacity());
    }

    @Test
    public void test_capacity2(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("cat");
        test_3.insert("man");
        test_3.insert("funny");
        assertEquals(10,test_3.capacity());
    }

    @Test
    public void test_capacity3(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("cat");
        test_3.insert("man");
        test_3.insert("funny");
        test_3.delete("Nice");
        test_3.delete("Dang");
        test_3.delete("cat");
        test_3.delete("man");
        test_3.delete("funny");
        assertEquals(10,test_3.capacity());
    }

    @Test
    public void test_getStatsLog1(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("cat");
        test_3.insert("man");
        test_3.insert("funny");
        test_3.insert("bunny");
        test_3.insert("sad");
        test_3.insert("cheese");
        String expected = "Before rehashing # 1: load factor 0.60, 0 collision(s)\n" +
                "Before rehashing # 2: load factor 0.60, 3 collision(s)\n";
        assertEquals(expected, test_3.getStatsLog());
    }

    @Test
    public void test_getStatsLog2(){
        test_3 = new HashTable(10);
        test_3.insert("Nice");
        test_3.insert("Dang");
        test_3.insert("cat");
        test_3.insert("man");
        test_3.insert("funny");
        test_3.insert("jam");
        test_3.insert("pig");
        String expected = "Before rehashing # 1: load factor 0.60, 2 collision(s)\n";
        assertEquals(expected, test_3.getStatsLog());
    }

    @Test
    public void test_getStatsLog3(){
        test_3 = new HashTable(5);
        test_3.insert("Nice");
        test_3.insert("bottle");
        test_3.insert("opiums");
        test_3.insert("cool");
        String expected = "Before rehashing # 1: load factor 0.60, 1 collision(s)\n";
        assertEquals(expected, test_3.getStatsLog());
    }

    @Test(expected = NullPointerException.class)
    public void test_exception2(){
        test_3 = new HashTable();
        test_3.insert(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_exception3(){
        test_3 = new HashTable();
        test_3.delete(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_exception4(){
        test_3 = new HashTable();
        test_3.lookup(null);
    }
}
