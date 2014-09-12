package demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import utils.TF_IDFAlgorithm;

public class Demo {
    public static void main(String[] args) {
         HashMap<Integer,HashMap<String, Float>> result = null;
         result = TF_IDFAlgorithm.BUILD_ID_TFIDF_result();
         Iterator iter = result.entrySet().iterator();
         System.out.println(result);
//        HashMap<Integer, HashMap<String, Float>> result = new HashMap<Integer, HashMap<String, Float>>();
//        
//        result.putAll()
//        Iterator iter = result.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            System.out.println(entry.getValue().getClass());
//
//        }

    }
}
