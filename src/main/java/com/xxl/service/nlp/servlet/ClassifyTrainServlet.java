package com.xxl.service.nlp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxl.logging.Logger;
import com.xxl.logging.LoggerFactory;
import com.xxl.util.ObjectUtils;


/**
 * 语料库训练
 *
 * @author xuxinlong
 *
 */
public class ClassifyTrainServlet extends ApplicationContextServlet {

    private static final long serialVersionUID = 5305710504223268729L;
    private static final Logger logger = LoggerFactory
            .getLogger(ClassifyTrainServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        String sentence = req.getParameter("sentence");
        int trainTimes = ObjectUtils.toInteger(req.getParameter("trainTimes"));

        String offlineReload = req.getParameter("reload");
        boolean reload = true;
        if("false".equals(offlineReload)){
            reload = false;
        }

        if (trainTimes < 1) {
            trainTimes = 1;
        }
        logger.info("train sentence:%s times:%s", sentence, trainTimes);
        getClassifyTrainer().train(sentence, trainTimes, reload);

    }

}
