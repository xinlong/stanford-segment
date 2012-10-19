package com.xxl.service.nlp.train;

import javax.annotation.Resource;

import com.xxl.logging.Logger;
import com.xxl.logging.LoggerFactory;
import com.xxl.service.nlp.segment.Segmenter;

public abstract class AbstractTrainer implements Trainer {

    private static final Logger logger = LoggerFactory
            .getLogger(AbstractTrainer.class);

    @Resource(name = "offlineSegmenter")
    private Segmenter segmenter;

    @Override
    public void reload() {
        logger.warn(this +" reload...");
        segmenter.reload();
    }

    @Override
    public void train(String sentence){
        train(sentence, 1, true);
    }

    public abstract void trainFile(String sentence,int trainTimes) ;

    @Override
    public void train(String sentence,int trainTimes, boolean reloadSegmenter) {

        this.trainFile(sentence, trainTimes);
        // 重新加载词典及语料库
        if (reloadSegmenter) {
            reload();
        }
    }

}
