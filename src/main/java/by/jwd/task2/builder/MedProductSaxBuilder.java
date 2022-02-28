package by.jwd.task2.builder;

import by.jwd.task2.exception.MedProductException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class MedProductSaxBuilder extends MedProductBuilder {
    static Logger logger = LogManager.getLogger();

    private MedProductHandler handler = new MedProductHandler();
    private XMLReader reader;

    public MedProductSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            logger.error(e);
        } catch (SAXException e) {
            logger.error(e);
        }
        reader.setErrorHandler(new MedProductErrorHandler());
        reader.setContentHandler(handler);
    }

    @Override
    public void buildMedicationCatalog(String xmlPath) throws MedProductException {
        try {
            reader.parse(xmlPath);
        } catch (IOException e) {
            logger.error("IOException during work with file " + xmlPath);
            throw new MedProductException("IOException during work with file " + xmlPath, e);
        } catch (SAXException e) {
            logger.error("SAXException while building MedProduct ");
            throw new MedProductException("SAXException while building MedProduct ", e);
        }
        medicationCatalog = handler.getMedCatalog();
    }
}
