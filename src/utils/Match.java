package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Match {
    private static HashMap<Integer, HashMap<String, Float>> tf_idfs_Map = null;
    private static File file = new File("temp/tf_idfs_Map.xml");
    // 输入的问题与数据库中已存问题计算出的tiidf，与问题id对应
    private static HashMap<Integer, Float> id_rs_tfidf = new HashMap<Integer, Float>();

    public static void initFromDataBase() {
        tf_idfs_Map = TF_IDFAlgorithm.BUILD_ID_TFIDF_result();
        try {
            
            ObjectToXMLUtil.objectXmlEncoder(tf_idfs_Map, file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initFromFile() {
        try {
            tf_idfs_Map = ObjectToXMLUtil.objectXmlDecoder(file);
            if(tf_idfs_Map==null)
            {
                System.out.println("Match 初始化失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

         String s = "还有房吗";
         if(file.exists())
         {
             initFromFile();
         }else
         {
             initFromDataBase();
         }
         
         System.out.println(match(s));

    }

    public static String match(String str) {

        ArrayList<String> cutwords = WordSeg.parse(str);
        Iterator iter = tf_idfs_Map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            HashMap<String, Float> tf_idf_Map = (HashMap<String, Float>) entry.getValue();
            Integer key = Integer.valueOf(entry.getKey().toString());
            for (int i = 0; i < cutwords.size(); i++) {
                // 看现在处理的词是否在tf_idf_Map的可以key中，若在进行处理
                if (tf_idf_Map.get(cutwords.get(i)) != null) {
                    if (id_rs_tfidf.get(key) != null) {
                        Float lastValue = id_rs_tfidf.get(key);
                        Float value = lastValue + tf_idf_Map.get(cutwords.get(i));
                        id_rs_tfidf.put(key, value);
                    } else {
                        id_rs_tfidf.put(key, tf_idf_Map.get(cutwords.get(i)));
                    }
                }
            }
            // 新输入问题与此entry内hashMap内相同的词的tf_idf和
            if (id_rs_tfidf.get(key) != null) {
                float tf_idf_sum2 = id_rs_tfidf.get(key);
                // 此entry对应hashMap内tf_idf和
                float tf_idf_sum1 = 0.0f;
                Iterator iter1 = tf_idf_Map.keySet().iterator();
                while (iter1.hasNext()) {
                    tf_idf_sum1 += tf_idf_Map.get(iter1.next());
                }
                id_rs_tfidf.put(key, tf_idf_sum2 / tf_idf_sum1);
            }
        }
        ArrayList list = mapSortByValue(id_rs_tfidf);
        return (!list.isEmpty()) ? list.toString() : "没有相关匹配";
        // int id = findMax(id_rs_tfidf);
        // return id!=-1?String.valueOf(id):"没有相关匹配";
    }

    public static Integer findMax(HashMap<Integer, Float> id_rs_tfidf) {
        Integer max = -1;
        if (id_rs_tfidf.keySet().size() > 0) {
            Iterator iter = id_rs_tfidf.keySet().iterator();
            max = (Integer) iter.next();
            Float base = id_rs_tfidf.get(max);
            while (iter.hasNext()) {
                Float compare = id_rs_tfidf.get(iter.next());
                if (base < compare) {
                    base = compare;
                    max = (Integer) iter.next();
                }
            }
        }
        return max;
    }

    public static ArrayList<Entry<Integer, Float>> mapSortByValue(HashMap<Integer, Float> map) {
        ArrayList<Entry<Integer, Float>> list = new ArrayList<Entry<Integer, Float>>((Collection) map.entrySet());

        Collections.sort(list, new Comparator<Object>() {

            public int compare(Object e1, Object e2) {
                Float v1 = ((Entry<Integer, Float>) e1).getValue();
                Float v2 = ((Entry<Integer, Float>) e2).getValue();
                if (v1 >= v2) {
                    return -1;
                } else {
                    return 1;
                }

            }
        });

        for (Entry<Integer, Float> e : list) {
            System.out.println(e.getKey() + "  " + e.getValue());
        }
        return list;
    }

}
