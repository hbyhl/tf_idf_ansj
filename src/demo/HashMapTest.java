package demo;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, Integer> m = new HashMap<String, Integer>();
        m.put("第一个", Integer.valueOf(1));
        m.put("第二个", Integer.valueOf(2));
        m.put("第三个", Integer.valueOf(3));
        m.put("第四个", Integer.valueOf(4));
        ArrayList<String> mList = new ArrayList(m.keySet());
        System.out.println(mList);
        System.out.println(mList);
        System.out.println(mList);

        HashMap<String, Integer> m1 = new HashMap<String, Integer>();
        m1.put("第一个", Integer.valueOf(1));
        m1.put("第二个", Integer.valueOf(2));
        m1.put("第三个", Integer.valueOf(3));
        m1.put("第四个", Integer.valueOf(4));
        ArrayList<String> m1List = new ArrayList(m1.keySet());
        for (String s : m1List) {
            System.out.println(m1List + ",");
        }
        System.out.println(m1List);
        System.out.println(m1List);
    }
}
