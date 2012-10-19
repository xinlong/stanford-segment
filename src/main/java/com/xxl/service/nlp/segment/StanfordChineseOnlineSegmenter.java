package com.xxl.service.nlp.segment;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;


/**
 * 斯坦福中文分词工具
 *
 * @author xuxinlong
 *
 */
public class StanfordChineseOnlineSegmenter extends StanfordChineseSegmenter {

    @Override
    public synchronized void reload() {
        CRFClassifier<CoreLabel> newClassifier = new CRFClassifier<CoreLabel>(props);
        newClassifier.loadClassifierNoExceptions(props.getProperty("aifangOnlineCtbClassifier"),props);
        newClassifier.flags.setProperties(props);
        classifier = newClassifier;
    }

}
