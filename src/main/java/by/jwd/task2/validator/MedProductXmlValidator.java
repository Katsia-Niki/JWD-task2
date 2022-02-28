package by.jwd.task2.validator;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import by.jwd.task2.builder.MedProductErrorHandler;
import by.jwd.task2.exception.MedProductException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;


public class MedProductXmlValidator {
    static Logger logger = LogManager.getLogger();
    private static final MedProductXmlValidator instance = new MedProductXmlValidator();


    private MedProductXmlValidator() {

    }

    public static MedProductXmlValidator getInstance() {
        return instance;
    }

    public static boolean validateXml(String xmlPath, String schemaName) throws MedProductException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlPath);
            MedProductErrorHandler errorHandler = new MedProductErrorHandler();
            validator.setErrorHandler(errorHandler);
            validator.validate(source);

        } catch (IOException e) {
            logger.error("File can't be open " + xmlPath, e);
            throw new MedProductException("File can't be open " + xmlPath, e);
        }  catch (SAXException e) {
            logger.error("File " + xmlPath + " is not valid.", e);
            return false;
        }
        return true;
    }
}
