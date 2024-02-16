import java.util.*;

public class A1_Q3a {

	public static int protocol_3a(int[] arrangement) {

        // given an array of integers, find the minimum distance between two same integers
        // integers are classes
        // this problem seems to require HashMap

        // strat: whenever u see a course, put it in a hashmap so that u know the last time u saw it
        // you also have the position of the last time u saw it
        
        // [202, 204, 250, 251, 202]

        Map<Integer, Integer> lastSeenPos = new HashMap<>();        // courses : positions
        int minDistance = arrangement.length;


        for (int i =  0; i < arrangement.length; i++) {
            int classes = arrangement[i];                   
            if (lastSeenPos.containsKey(classes)) {

                int distance = i - lastSeenPos.get(classes);
                minDistance = Math.min(minDistance, distance);
            }
            
            lastSeenPos.put(classes, i);        // update the last seen position
        }

        return minDistance;
    }
}

