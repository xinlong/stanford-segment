package com.xxl.service.nlp.demo.segment;

import java.util.Properties;

import com.xxl.service.nlp.Constant;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * 分詞demo
 * @author xuxinlong
 *
 */
public class SegOldFileDemo {

    public static void main(String[] args) throws Exception {

        /** 设置训练参数 */
        Properties props = new Properties();
        //单个汉字词典位置
        props.setProperty("sighanCorporaDict", "data");
        // props.setProperty("NormalizationTable", "data/norm.simp.utf8");
        props.setProperty("normTableEncoding", "UTF-8");
        // below is needed because CTBSegDocumentIteratorFactory accesses it

        // 分词的词典
        props.setProperty("serDictionary", Constant.serDictionary);

        //输入文本编码
        props.setProperty("inputEncoding", "UTF-8");
        //单个汉字是否处理
        props.setProperty("sighanPostProcessing", "true");


        /** 初始化分类器 */
        CRFClassifier<CoreLabel> classifier = new CRFClassifier<CoreLabel>(props);
        // 分类文件 使用CTB作为训练集，也可以使用pku.gz
        classifier.loadClassifierNoExceptions(Constant.CTBClassifier, props);
        classifier.flags.setProperties(props);

        classifier.classifyAndWriteAnswers(Constant.segFile);

    }

}
