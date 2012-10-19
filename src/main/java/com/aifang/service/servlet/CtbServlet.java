package com.aifang.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aifang.ajf.logging.Logger;
import com.aifang.ajf.logging.LoggerFactory;
import com.aifang.service.nlp.Constant;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.sequences.DocumentReaderAndWriter;

public class CtbServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static CRFClassifier<CoreLabel> classifier = null;
    private static DocumentReaderAndWriter<CoreLabel> readerAndWriter;
    private static final Logger logger = LoggerFactory
            .getLogger(CtbServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        String text = req.getParameter("text");

        logger.warn("queryText:%s", text);
        PrintWriter pw = resp.getWriter();
        StringBuilder tokenBuilder = new StringBuilder();
        if (text != null && !text.trim().isEmpty()) {
            List<String> tokenList = classifier.segmentString(text,
                    readerAndWriter);

            for (String token : tokenList) {
                pw.print(token + " ");
                tokenBuilder.append(token).append(" ");
            }
            logger.warn("segmentResult:%s", tokenBuilder.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        Properties props = new Properties();

        String sighanCorporaDict = config.getInitParameter("sighanCorporaDict");
        sighanCorporaDict = (sighanCorporaDict == null || sighanCorporaDict
                .trim().isEmpty()) ? "data" : sighanCorporaDict;
        props.setProperty("sighanCorporaDict", sighanCorporaDict);

        String serDictionary = config.getInitParameter("serDictionary");
        serDictionary = (serDictionary == null || serDictionary.trim()
                .isEmpty()) ? Constant.serDictionarys : serDictionary;
        props.setProperty("serDictionary", serDictionary);

        String inputEncoding = config.getInitParameter("inputEncoding");
        inputEncoding = (inputEncoding == null || inputEncoding.trim()
                .isEmpty()) ? "UTF-8" : inputEncoding;
        props.setProperty("inputEncoding", inputEncoding);

        String sighanPostProcessing = config
                .getInitParameter("sighanPostProcessing");
        sighanPostProcessing = (sighanPostProcessing == null || sighanPostProcessing
                .trim().isEmpty()) ? "true" : sighanPostProcessing;
        props.setProperty("sighanPostProcessing", sighanPostProcessing);

        String classifierFile = config.getInitParameter("classifierFile");
        classifierFile = (classifierFile == null || classifierFile.trim()
                .isEmpty()) ? Constant.CTBClassifier : classifierFile;

        classifier = new CRFClassifier<CoreLabel>(props);
        classifier.loadClassifierNoExceptions(classifierFile, props);
        classifier.flags.setProperties(props);

        readerAndWriter = classifier.makeReaderAndWriter();
        super.init(config);
    }

}
