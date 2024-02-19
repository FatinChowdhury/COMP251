import java.io.*;
import java.util.*;

public class Open_Addressing {  // linear probing
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

    protected Open_Addressing(int w, int seed, int A) {

        this.w = w;
        this.r = (int) (w-1)/2 +1;
        this.m = power2(r);
        if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
        }
        else{
            this.A = A;
        }
        this.Table = new int[m];
        for (int i =0; i<m; i++) {
            Table[i] = -1;
        } 
    }
     
    /** Calculate 2^w*/
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }
    public static int generateRandom(int min, int max, int seed) {     
        Random generator = new Random(); 
            if(seed>=0){
                generator.setSeed(seed);
            }
        int i = generator.nextInt(max-min-1);
        return i+min+1;
    }
        /**Implements the hash function g(k)*/
    public int probe(int key, int i) {
        //TODO: implement this function and change the return statement.
        int hash = ((A*key) % power2(w)) >> (w-r);
        int probeHash = (hash + i) % m;
        return probeHash;
    }
    
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
    public int insertKey(int key){
        //TODO : implement this and change the return statement.
        if (key < 0) {
            return -1;
        }
        int i = 0;
        int collisions = 0;
        while (true) {

            int pos = probe(key, i);

            if (Table[pos] < 0 || Table[pos] == key) {          // empty slot or key is there
                                                                // still have to consider if slot is deleted
                Table[pos] = key;                               // either adds or updates 
                return collisions;
            }
                
            collisions++;                                       
            i++;                                                // next probe position


            if (i >= m) {return -3;}                            // table is full
        } 
    }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
    public int insertKeyArray (int[] keyArray) {
        int collision = 0;
        for (int key: keyArray) {
            collision += insertKey(key);
        }
        return collision;
    }
            
         /**Removes key k from the hash table. Returns the number of collisions encountered*/
    public int removeKey(int key) {
        //TODO: implement this and change the return statement
        if (key < 0) {
            return -1;
        }

        int collisions = 0;
        int slotsVisited = 0;
        boolean keyFound = false;


        for (int slots = 0; slots < m; slots++) {
        
            int pos = probe(key, slots);
            slotsVisited++;

            if (Table[pos] == -1) {                                     // empty slot
                return slots + 1;                                       // should return number of slots visited

            } else if (Table[pos] == key) {
                Table[pos] = -2;                                   // removes key by setting it to -1
                keyFound = true;
                break;                           // returns number of collisions plus number of sl
            } else {
                collisions++;
            }
        }
        
        if (keyFound) {
            return collisions;
        }

        return slotsVisited;                                              
    }
}
