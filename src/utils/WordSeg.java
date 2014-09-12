package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.util.FilterModifWord;

public class WordSeg {
    static {
        initStopWordsAndStopNatures();
    }

    public static ArrayList<String> parse(String str) {
        List<Term> parse = NlpAnalysis.parse(str);
        List<Term> terms = FilterModifWord.modifResult(parse);
        ArrayList<String> words = new ArrayList<String>();
        for (Term t : terms) {
            if (t.getNatureStr() != null) {
                words.add(t.getName());
            }
        }
        return words;
    }

    public static void initStopWordsAndStopNatures() {

        List<String> stopWord = new ArrayList<String>();

        System.out.println("init stopwords and natures");
        // 停词表
        String stopWordFile = "stopword.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(stopWordFile), "UTF-8"));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                stopWord.add(line);
            }
        } catch (Exception e) {
            System.out.println("创建停词表失败");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FilterModifWord.insertStopWords(stopWord);
        stopWord.clear();
        FilterModifWord.insertStopNatures(" ");
        FilterModifWord.insertStopNatures("t");
        FilterModifWord.insertStopNatures("tg");
        FilterModifWord.insertStopNatures("s");
        FilterModifWord.insertStopNatures("f");
        FilterModifWord.insertStopNatures("vshi");
        FilterModifWord.insertStopNatures("vyou");
        FilterModifWord.insertStopNatures("a");
        FilterModifWord.insertStopNatures("ad");
        FilterModifWord.insertStopNatures("an");
        FilterModifWord.insertStopNatures("ag");
        FilterModifWord.insertStopNatures("al");
        FilterModifWord.insertStopNatures("b");
        FilterModifWord.insertStopNatures("bl");
        FilterModifWord.insertStopNatures("z");
        FilterModifWord.insertStopNatures("r");
        FilterModifWord.insertStopNatures("rr");
        FilterModifWord.insertStopNatures("rz");
        FilterModifWord.insertStopNatures("rzt");
        FilterModifWord.insertStopNatures("rzs");
        FilterModifWord.insertStopNatures("rzv");
        FilterModifWord.insertStopNatures("ry");
        FilterModifWord.insertStopNatures("ryt");
        FilterModifWord.insertStopNatures("rys");
        FilterModifWord.insertStopNatures("ryv");
        FilterModifWord.insertStopNatures("rg");
        FilterModifWord.insertStopNatures("m");
        FilterModifWord.insertStopNatures("mq");
        FilterModifWord.insertStopNatures("q");
        FilterModifWord.insertStopNatures("qv");
        FilterModifWord.insertStopNatures("qt");
        FilterModifWord.insertStopNatures("d");
        FilterModifWord.insertStopNatures("p");
        FilterModifWord.insertStopNatures("pba");
        FilterModifWord.insertStopNatures("pbei");
        FilterModifWord.insertStopNatures("c");
        FilterModifWord.insertStopNatures("cc");
        FilterModifWord.insertStopNatures("u");
        FilterModifWord.insertStopNatures("uzhe");
        FilterModifWord.insertStopNatures("ule");
        FilterModifWord.insertStopNatures("uguo");
        FilterModifWord.insertStopNatures("ude1");
        FilterModifWord.insertStopNatures("ude2");
        FilterModifWord.insertStopNatures("ude3");
        FilterModifWord.insertStopNatures("usuo");
        FilterModifWord.insertStopNatures("udeng");
        FilterModifWord.insertStopNatures("uyy");
        FilterModifWord.insertStopNatures("udh");
        FilterModifWord.insertStopNatures("uls");
        FilterModifWord.insertStopNatures("uzhi");
        FilterModifWord.insertStopNatures("ulian");
        FilterModifWord.insertStopNatures("e");
        FilterModifWord.insertStopNatures("y");
        FilterModifWord.insertStopNatures("o");
        FilterModifWord.insertStopNatures("h");
        FilterModifWord.insertStopNatures("k");
        FilterModifWord.insertStopNatures("x");
        FilterModifWord.insertStopNatures("xx");
        FilterModifWord.insertStopNatures("xu");
        FilterModifWord.insertStopNatures("w");
        FilterModifWord.insertStopNatures("wkz");
        FilterModifWord.insertStopNatures("wky");
        FilterModifWord.insertStopNatures("wyz");
        FilterModifWord.insertStopNatures("wyy");
        FilterModifWord.insertStopNatures("wj");
        FilterModifWord.insertStopNatures("ww");
        FilterModifWord.insertStopNatures("wt");
        FilterModifWord.insertStopNatures("wd");
        FilterModifWord.insertStopNatures("wf");
        FilterModifWord.insertStopNatures("wn");
        FilterModifWord.insertStopNatures("wm");
        FilterModifWord.insertStopNatures("ws");
        FilterModifWord.insertStopNatures("wp");
        FilterModifWord.insertStopNatures("wb");
        FilterModifWord.insertStopNatures("wh");
        FilterModifWord.insertStopNatures("en");
        FilterModifWord.insertStopNatures("nr1");
        FilterModifWord.insertStopNatures("vd");
        FilterModifWord.insertStopNatures("vf");
        FilterModifWord.insertStopNatures("vx");
        FilterModifWord.insertStopNatures("vi");
        System.out.println("init stopwords and natures success!!");
    }

    public static void main(String[] args) {
        String s = "洁面仪配合洁面深层清洁毛孔 清洁,,鼻孔面膜碎" +
        		",,,,,,，，，，觉使劲挤才能出一点点皱纹 脸颊毛孔修复的看不见啦 草莓鼻历史遗留问题没辙 脸和脖子差不多颜色的皮肤才是健康的 长期使用安全健康的比同龄人显小五到十岁 28岁的妹子看看你们的鱼尾纹";
        System.out.println(parse(s));
        System.out.println(NlpAnalysis.parse(s));
    }

}
