package com.xxl.service.nlp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aifang.ajf.logging.Logger;
import com.aifang.ajf.logging.LoggerFactory;

/**
 * 词典训练
 *
 * @author xuxinlong
 *
 */
public class DictTrainServlet extends ApplicationContextServlet {

    private static final long serialVersionUID = 5305710504223268729L;
    private static final Logger logger = LoggerFactory
            .getLogger(DictTrainServlet.class);

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
        String dicts = req.getParameter("dicts");

        String offlineReload = req.getParameter("reload");
        boolean reload = true;
        if("false".equals(offlineReload)){
            reload = false;
        }

        logger.info("train dictionarys:%s", dicts);

        getDictTrainer().train(dicts,1,reload);
    }

}
