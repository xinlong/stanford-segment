package com.xxl.service.nlp.train.dict;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import com.xxl.logging.Logger;
import com.xxl.logging.LoggerFactory;
import com.xxl.service.nlp.train.AbstractTrainer;
import com.xxl.util.ObjectUtils;
import com.xxl.util.StringUtils;

import edu.stanford.nlp.wordseg.ChineseDictionary;

/**
 * 中文词典训练
 *
 * @author xuxinlong
 *
 */
public class ChineseDictTrainer extends AbstractTrainer {

    private Logger logger = LoggerFactory.getLogger(ChineseDictTrainer.class);

    private Properties props;

    @Override
    public void trainFile(String dicts,int trainTimes) {
        // 新增加的词条，待训练进output文件中
        String newInputDict = props.getProperty("newInputDict");
        String inputDicts = props.getProperty("inputDicts");
        String output = props.getProperty("aifangSerDictionary");

        try {
            if (StringUtils.isBlank(dicts)) {
                return;
            }
            String[] dictArr = dicts.split("\r\n");
            if (ObjectUtils.isEmpty(dictArr)) {
                return;
            }
            // 写入文件
            appendFile(newInputDict, dictArr);

            inputDicts = inputDicts + "," + newInputDict;
            String[] properties = { "-inputDicts", inputDicts, "-output",
                    output };
            // 重新训练文件
            ChineseDictionary.main(properties);
        } catch (Exception e) {
            logger.error("Error when train dictionary", e);
        }
    }

    private void appendFile(String file, String[] dicts) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            for (String s : dicts) {
                if(s!=null && s.trim()!="" && s.trim().length()<=10){
                    bw.write(s.trim());
                    bw.newLine();
                }
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

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

}
