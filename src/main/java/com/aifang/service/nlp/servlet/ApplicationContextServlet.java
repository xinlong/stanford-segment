package com.aifang.service.nlp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.aifang.service.nlp.segment.StanfordChineseOfflineSegmenter;
import com.aifang.service.nlp.segment.StanfordChineseOnlineSegmenter;
import com.aifang.service.nlp.train.classify.CTBClassifyTrainer;
import com.aifang.service.nlp.train.dict.ChineseDictTrainer;

@SuppressWarnings("serial")
public class ApplicationContextServlet extends HttpServlet {

    private ApplicationContext applicationContext = null;

    private StanfordChineseOnlineSegmenter onlineSegmenter;
    private StanfordChineseOfflineSegmenter offlineSegmenter;

    private ChineseDictTrainer dictTrainer;
    private CTBClassifyTrainer classifyTrainer;

    @Override
    public void init() throws ServletException {
        super.init();

        applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(this.getServletContext());
        this.onlineSegmenter = (StanfordChineseOnlineSegmenter) applicationContext
                .getBean("onlineSegmenter");

        this.offlineSegmenter = (StanfordChineseOfflineSegmenter) applicationContext
                .getBean("offlineSegmenter");

        this.dictTrainer = (ChineseDictTrainer) applicationContext.getBean("chineseDictTrainer");
        this.classifyTrainer = (CTBClassifyTrainer) applicationContext.getBean("ctbClassifyTrainer");

    }


    public StanfordChineseOnlineSegmenter getOnlineSegmenter() {
        return onlineSegmenter;
    }

    public StanfordChineseOfflineSegmenter getOfflineSegmenter() {
        return offlineSegmenter;
    }

    public ChineseDictTrainer getDictTrainer() {
        return dictTrainer;
    }

    public CTBClassifyTrainer getClassifyTrainer() {
        return classifyTrainer;
    }


}
