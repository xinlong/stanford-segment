package com.aifang.service.nlp.segment;

import java.util.List;

/**
 * 分词器
 *
 * @author xuxinlong
 *
 */
public interface Segmenter {

    void init();

    void reload();

    void destory();

    List<String> getSegmentList(String sentence);

    /**
     * 已用空格隔开
     */
    String getSegmengString(String sentence);

}
