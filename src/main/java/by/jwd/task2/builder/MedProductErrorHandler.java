package by.jwd.task2.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class MedProductErrorHandler implements ErrorHandler {
    static Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.warn(getLineColumnNumber(exception) + " - " + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.error(getLineColumnNumber(exception) + " - " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.fatal(getLineColumnNumber(exception) + " : " + exception.getMessage());
    }

    private String getLineColumnNumber(SAXParseException exception) {
        return exception.getLineNumber() + " : " + exception.getColumnNumber();
    }


}
