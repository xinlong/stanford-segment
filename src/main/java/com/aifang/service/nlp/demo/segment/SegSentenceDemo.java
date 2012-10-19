package com.aifang.service.nlp.demo.segment;

import java.util.List;
import java.util.Properties;

import com.aifang.service.nlp.Constant;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * 分詞demo
 *
 * @author xuxinlong
 *
 */
public class SegSentenceDemo {

    public static void main(String[] args) throws Exception {
        /** 设置训练参数 */
        Properties props = new Properties();
        // 单个汉字词典位置
        props.setProperty("sighanCorporaDict", "data");
        props.setProperty("NormalizationTable", "data/norm.simp.utf8");
        props.setProperty("normTableEncoding", "UTF-8");
        // 分词的词典
        props.setProperty("serDictionary", Constant.serDictionarys);
//		props.setProperty("serDictionary", Constant.aifangSerDictionary);
        // 输入文本编码
        props.setProperty("inputEncoding", "UTF-8");
        // 单个汉字是否处理
        props.setProperty("sighanPostProcessing", "true");
//		 props.setProperty("printFeatures", "true");

        /** 初始化分类器 */
        CRFClassifier<CoreLabel> classifier = new CRFClassifier<CoreLabel>(props);
        // 分类文件 使用CTB作为训练集，也可以使用pku.gz
        classifier.loadClassifierNoExceptions(Constant.aifangCTBClassifier,props);
//		classifier.loadClassifierNoExceptions(Constant.CTBClassifier,props);
        classifier.flags.setProperties(props);

        List<String> result = classifier.segmentString("房地产有哪些新政策");

        for (String s : result) {
            System.out.print(s + " ");
        }
    }

}
