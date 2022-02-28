package by.jwd.task2.builder;

import by.jwd.task2.entity.*;
import by.jwd.task2.exception.MedProductException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.Arrays;

public class MedProductDomBuilder extends MedProductBuilder {
    static Logger logger = LogManager.getLogger();

    private static final String SPACE_SEPARATOR = " ";
    private DocumentBuilder documentBuilder;

    public MedProductDomBuilder() {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Ошибка конфигурации парсера ", e);
        }
    }

    @Override
    public void buildMedicationCatalog(String xmlPath) throws MedProductException {
        Document document;

        try {
            document = documentBuilder.parse(xmlPath);
            Element root = document.getDocumentElement();
            NodeList medProductList;

            medProductList = root.getElementsByTagName(MedProductXmlTag.MEDICINE.toString());
            for (int i = 0; i < medProductList.getLength(); i++) {
                Element medicineElement = (Element) medProductList.item(i);
                MedProduct newMedicine = buildMedProduct(medicineElement);
                medicationCatalog.add(newMedicine);
            }

            medProductList = root.getElementsByTagName(MedProductXmlTag.BAA.toString());
            for (int i = 0; i < medProductList.getLength(); i++) {
                Element baaElement = (Element) medProductList.item(i);
                MedProduct newBaa = buildMedProduct(baaElement);
                medicationCatalog.add(newBaa);
            }
        } catch (IOException e) {
            logger.error(e);
            throw new MedProductException(e);
        } catch (SAXException e) {
            logger.error(e);
            throw new MedProductException(e);
        }
    }

    private MedProduct buildMedProduct(Element MedProductElement) {
        boolean isMedicine = MedProductElement.getTagName().equals(MedProductXmlTag.MEDICINE.toString());
        MedProduct currentMedProduct = isMedicine ? new Medicine() : new Baa();

        String content;

        content = MedProductElement.getAttribute(MedProductXmlAttribute.ID.toString());
        currentMedProduct.setMedProductId(content);
        content = MedProductElement.getAttribute(MedProductXmlAttribute.OUT_OF_PRODACTION.toString());
        if (!content.isBlank()) {
            currentMedProduct.setOutOfProduction(Boolean.parseBoolean(content));
        } else {
            currentMedProduct.setOutOfProduction(MedProduct.DEFAULT_OUT_OF_PRODUCTION);
        }

        content = getElementTextContent(MedProductElement, MedProductXmlTag.NAME.toString());
        currentMedProduct.setName(content);
        content = getElementTextContent(MedProductElement, MedProductXmlTag.PHARM.toString());
        currentMedProduct.setPharm(content);
        content = getElementTextContent(MedProductElement, MedProductXmlTag.GROUP.toString());
        currentMedProduct.setGroup(DrugGroup.valueOfXmlContent(content));
        content = getElementTextContent(MedProductElement, MedProductXmlTag.ANALOGS.toString());
        currentMedProduct.setAnalogs(Arrays.asList(content.split(SPACE_SEPARATOR)));

        MedProduct.DrugVersion currentVersion = currentMedProduct.getVersion();
        content = getElementTextContent(MedProductElement, MedProductXmlTag.COUNTRY.toString());
        currentVersion.setCountry(Country.valueOfXmlContent(content));
        content = getElementTextContent(MedProductElement, MedProductXmlTag.CERTIFICATE.toString());
        currentVersion.setCertificate(content);
        content = getElementTextContent(MedProductElement, MedProductXmlTag.EXPIRATION_DATE.toString());
        currentVersion.setExpirationDate(YearMonth.parse(content));
        content = getElementTextContent(MedProductElement, MedProductXmlTag.PACK.toString());
        currentVersion.setPack(Pack.valueOfXmlContent(content));
        content = getElementTextContent(MedProductElement, MedProductXmlTag.DOSAGE.toString());
        currentVersion.setDosage(content);

        if(currentMedProduct instanceof Medicine) {
            Medicine temp = (Medicine) currentMedProduct;
            content = getElementTextContent(MedProductElement, MedProductXmlTag.ACTIVE_SUBSTANCE.toString());
            temp.setActiveSubstance(content);
            content = getElementTextContent(MedProductElement, MedProductXmlTag.NEED_RECIPE.toString());
            temp.setNeedRecipe(Boolean.parseBoolean(content));

            currentMedProduct = temp;
        } else {
            Baa temp = (Baa) currentMedProduct;
            content = getElementTextContent(MedProductElement, MedProductXmlTag.COMPOSITION.toString());
            temp.setComposition(Arrays.asList(content.split(SPACE_SEPARATOR)));
            currentMedProduct = temp;
        }
        return currentMedProduct;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String textContent = node.getTextContent();
        return  textContent;
    }
}
