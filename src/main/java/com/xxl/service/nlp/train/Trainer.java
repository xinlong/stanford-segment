package com.xxl.service.nlp.train;

/**
 * 训练词库
 *
 * @author xuxinlong
 *
 */
public interface Trainer {

    void reload();

    void train(String sentence);


    /**
     * 詞庫訓練
     */
    void train(String sentence,int trainTimes, boolean reloadSegmenter);

}
