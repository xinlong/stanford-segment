package com.aifang.service.nlp.segment;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * 斯坦福中文分词工具
 *
 * @author xuxinlong
 *
 */
public abstract class StanfordChineseSegmenter implements Segmenter {

    protected volatile CRFClassifier<CoreLabel> classifier;

    protected Properties props;

    public StanfordChineseSegmenter() {
        this.init();
    }

    @Override
    public void init() {

    }

    @Override
    public void destory() {
        classifier = null;
    }

    @Override
    public abstract  void reload() ;

    public CRFClassifier<CoreLabel> getClassifier() {
        if (classifier == null) {
            reload();
        }
        return classifier;
    }

    @Override
    public List<String> getSegmentList(String sentence) {
        List<String> list = null;
        if (sentence != null && !sentence.isEmpty()) {
            list = getClassifier().segmentString(sentence);
        }
        if (list == null) {
            list = Collections.emptyList();
        }
        return list;
    }

    @Override
    public String getSegmengString(String sentence) {
        StringBuilder sb = new StringBuilder();

        List<String> list = this.getSegmentList(sentence);
        for(String s:list){
            sb.append(s).append(" ");
        }
        return sb.toString().trim();
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

}
