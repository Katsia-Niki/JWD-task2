package test.jwd.task2.validator;

import by.jwd.task2.exception.MedProductException;
import by.jwd.task2.validator.MedProductXmlValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MedProductXmlValidatorTest {
    private static final String XML_FILE = "src\\test\\resources\\data\\medicationCatalogTest.xml";
    private static final String XML_FILE_WRONG = "src\\test\\resources\\data\\medicationCatalogTestWrong.xml";
    private static final String SCHEMA = "src\\test\\resources\\data\\schemaTest.xsd";
    private MedProductXmlValidator validator;

    @BeforeClass
    public void setUp() {
        validator = MedProductXmlValidator.getInstance();
    }

    @Test
    public void validateXmlTestPositive() throws MedProductException {
        boolean actual = MedProductXmlValidator.validateXml(XML_FILE, SCHEMA);
        assertTrue(actual);
    }

    @Test
    public void validateXmlTestNegative() throws MedProductException {
        boolean actual = MedProductXmlValidator.validateXml(XML_FILE_WRONG, SCHEMA);
        assertFalse(actual);
    }


}