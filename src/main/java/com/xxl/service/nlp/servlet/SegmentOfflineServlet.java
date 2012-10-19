package com.xxl.service.nlp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxl.logging.Logger;
import com.xxl.logging.LoggerFactory;


/**
 * 分词servlet
 *
 * @author xuxinlong
 *
 */
public class SegmentOfflineServlet extends ApplicationContextServlet {

    private static final long serialVersionUID = 5305710504223268729L;
    private static final Logger logger = LoggerFactory
            .getLogger(SegmentOfflineServlet.class);

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
        String text = req.getParameter("text");

        PrintWriter pw = resp.getWriter();
        if (text!=null && text.trim()!="") {

            String result = getOfflineSegmenter().getSegmengString(text);
            pw.print(result);
            logger.info("Offline segment origin:'%s',result:'%s'", text, result);
        }

    }

}
