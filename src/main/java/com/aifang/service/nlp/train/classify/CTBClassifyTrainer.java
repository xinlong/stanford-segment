package com.aifang.service.nlp.train.classify;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import com.aifang.ajf.logging.Logger;
import com.aifang.ajf.logging.LoggerFactory;
import com.aifang.ajf.util.StringUtils;
import com.aifang.service.nlp.train.AbstractTrainer;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * CTB语料库训练
 *
 * @author xuxinlong
 *
 */
public class CTBClassifyTrainer extends AbstractTrainer {

    private Logger logger = LoggerFactory.getLogger(CTBClassifyTrainer.class);

    private Properties props;


    @Override
    public void trainFile(String sentence,int trainTimes) {

        try {
             if(StringUtils.isBlank(sentence)){
                 return;
             }

            // 写入.train.seg文件
            String newTrainFile = props.getProperty("newTrainFile");

            appendFile(newTrainFile, sentence,trainTimes);

            // 训练
            CRFClassifier<CoreLabel> crf = new CRFClassifier<CoreLabel>(props);
            String serializeTo = crf.flags.serializeTo;
            String serializeToText = crf.flags.serializeToText;


//            String loadClassifier = crf.flags.loadClassifier;
//             TODO 加载原有ctb语料库 (90M多啊)
//            if(loadClassifier!=null){
//            	crf.loadClassifierNoExceptions(loadClassifier, props);
//            }

            // 加载训练文件

            if (crf.flags.trainFileList != null) {
                crf.flags.trainFileList = newTrainFile;
            }else{
                crf.flags.trainFileList = crf.flags.trainFileList + "," + newTrainFile;
            }

            crf.train();

            // 训练好的语料库，序列化对象
            if (serializeTo != null) {
                crf.serializeClassifier(serializeTo);
            }
            // 训练好的语料库，明文形式，线上可以关掉
            if (serializeToText != null) {
                crf.serializeTextClassifier(serializeToText);
            }
        } catch (Exception e) {
            logger.error("Error when train Classify", e);
        }

    }


    public void appendFile(String file, String sentence,int trainTimes) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            while(trainTimes>0){
                 bw.write(sentence);
                 bw.newLine();
                 trainTimes--;
            }


        } catch (Exception e) {
            logger.error("Error when write file", e);
        } finally {
            try {
                if (null != bw) {
                    bw.close();
                }
                if (null != osw) {
                    osw.close();
                }
                if (null != fos) {
                    fos.close();
                }

            } catch (IOException e) {
            }
        }

    }

    public void setProps(Properties props) {
        this.props = props;
    }

}
