package com.aifang.service.nlp.demo.train;

import com.aifang.service.nlp.Constant;

import edu.stanford.nlp.wordseg.ChineseDictionary;

/**
 * 词典训练
 * @author xuxinlong
 *
 */
public class ChineseDictTrainDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
//        String[] properties = { "-inputDicts", Constant.inputSerDictionarys, "-output", Constant.aifangSerDictionary };
        String[] properties = { "-inputDicts", "data/dict/aifang-new.dict", "-output", Constant.aifangSerDictionary };
        ChineseDictionary.main(properties);
    }

}
