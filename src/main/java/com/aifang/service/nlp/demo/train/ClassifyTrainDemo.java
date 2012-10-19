package com.aifang.service.nlp.demo.train;

import com.aifang.service.nlp.Constant;

import edu.stanford.nlp.ie.crf.CRFClassifier;

public class ClassifyTrainDemo {

    public static void main(String[] args) throws Exception {

        /**
         * To train with multiple files:
         * java -mx1000m edu.stanford.nlp.ie.crf.CRFClassifier
         * -trainFileList file1,file2,... -testFile testFile -macro &gt; output
         */
        /**
         * java -cp /home/john/NTNUProj/stanford-chinese-segmenter-2008-05-21/seg.jar
         * -mx10g edu.stanford.nlp.ie.crf.CRFClassifier -prop ctb6-chris6.prop
         * -serDictionary /home/john/NTNUProj/stanford-chinese-segmenter-2008-05-21//data/dict-chris6.ser.gz
         * -sighanCorporaDict ctb6-train -trainFile data/ctb6.train.seg -serializeTo models/ctb6.ser.gz > logs/ctb6.log 2> logs/ctb6.err
         */

        /**
         * java -cp /home/john/NTNUProj/stanford-chinese-segmenter-2008-05-21//seg.jar
         * -mx10g edu.stanford.nlp.ie.crf.CRFClassifier
         * -prop ctb6-chris6.prop
         * -serDictionary /home/john/NTNUProj/stanford-chinese-segmenter-2008-05-21/data/dict-chris6.ser.gz
         * -sighanCorporaDict ctb6-train
         * -trainFile data/ctb6.train.seg
         * -serializeTo models/ctb6.ser.gz
         * > logs/ctb6.log 2> logs/ctb6.err
         */
        long time = System.currentTimeMillis();
        String[] properties = {
                "-sighanCorporaDict", "data",
                "-trainFileList",Constant.trainFiles,
//                "-trainFileList","data/train/aifang-new.train.seg",

                "-serDictionary",Constant.serDictionarys,
//				"-loadClassifier",Constant.CTBClassifier,
                "-serializeTo",Constant.aifangCTBClassifier,
                "-serializeToText",Constant.aifangCTBClassifierText,
                "-featureFactory","edu.stanford.nlp.wordseg.Gale2007ChineseSegmenterFeatureFactory",
                "-readerAndWriter","edu.stanford.nlp.wordseg.Sighan2005DocumentReaderAndWriter",
                "-plainTextDocumentReaderAndWriter","edu.stanford.nlp.wordseg.Sighan2005DocumentReaderAndWriter",

//                "-exportFeatures","data/train/aifang-features",

                "-testFiles",Constant.testFiles,

                "-sigma","3.0",
                "-useUnicodeBlock","true",
                "-useRule2","true",
                "-useDictionaryConjunctions","true",
                "-useQN","true",
                "-useWordn","true",
                "-useShapeStrings5","true",
                "-useShapeStrings4","true",
                "-useShapeStrings3","true",
                "-useShapeStrings1","true",
                "-outputEncoding","UTF-8",
                "-maxLeft","1",
                "-backgroundSymbol","1",
                "-map","char=0,answer=1",
                "-useShapeStrings","true",
                "-useFeaturesCpC4gram","true",
                "-useUnicodeType4gram","true",
                "-QNsize","15",
                "-removeBackgroundSingletonFeatures","true",
                "-keepEnglishWhitespaces","true",
                "-saveFeatureIndexToDisk","true",
                "-inputEncoding","UTF-8",
                "-keepAllWhitespaces","true",
                "-useUnicodeType","true",
                "-useCTBChar2","true",
                "-useWord2","true",
                "-useFeaturesC4gram","true",
                "-useWord1","true",
                "-sighanPostProcessing","true",
                "-expandMidDot","true",
                };
        CRFClassifier.main(properties);

    }

}
