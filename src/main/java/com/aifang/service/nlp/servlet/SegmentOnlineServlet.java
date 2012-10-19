package com.aifang.service.nlp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aifang.ajf.logging.Logger;
import com.aifang.ajf.logging.LoggerFactory;
import com.aifang.ajf.util.StringUtils;

/**
 * 分词servlet
 *
 * @author xuxinlong
 *
 */
public class SegmentOnlineServlet extends ApplicationContextServlet {

    private static final long serialVersionUID = 5305710504223268729L;
    private static final Logger logger = LoggerFactory
            .getLogger(SegmentOnlineServlet.class);

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
        String reload = req.getParameter("reload");
        if (StringUtils.isNotBlank(reload)) {
            if ("true".equals(reload)) {
                getOnlineSegmenter().reload();
            }
        }

        PrintWriter pw = resp.getWriter();
        if (StringUtils.isNotBlank(text)) {
            String result = getOnlineSegmenter().getSegmengString(text);
            pw.print(result);
            logger.info("Online segment origin:'%s',result:'%s'", text, result);
        }
    }

}
