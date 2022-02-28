package by.jwd.task2.builder;

import by.jwd.task2.entity.*;
import by.jwd.task2.exception.MedProductException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.Arrays;

public class MedProductStaxBuilder extends MedProductBuilder {
    static Logger logger = LogManager.getLogger();
    private static final String SPACE_SEPARATOR = " ";
    XMLInputFactory inputFactory;

    public MedProductStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildMedicationCatalog(String xmlPath) throws MedProductException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(xmlPath)) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if ((name.equals(MedProductXmlTag.MEDICINE.toString())) || (name.equals(MedProductXmlTag.BAA.toString()))) {
                        MedProduct medProduct = buildMedProduct(reader);
                        medicationCatalog.add(medProduct);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found " + xmlPath, e);
            throw new MedProductException("File not found " + xmlPath, e);
        } catch (IOException e) {
            logger.error("IOException during work with file " + xmlPath, e);
            throw new MedProductException("IOException during work with file " + xmlPath, e);
        } catch (XMLStreamException e) {
            logger.error("XMLStreamException during work with file", e);
            throw new MedProductException("XMLStreamException while building Set<MedProduct>", e);
        }
    }

    public MedProduct buildMedProduct(XMLStreamReader reader) throws XMLStreamException {
        MedProduct currentMedProduct = reader.getLocalName().equals(MedProductXmlTag.MEDICINE.toString()) ?
                new Medicine() : new Baa();
        currentMedProduct.setMedProductId(reader.getAttributeValue(null, MedProductXmlAttribute.ID.toString()));

        String content = reader.getAttributeValue(null, MedProductXmlAttribute.OUT_OF_PRODACTION.toString());
        if (content != null) {
            currentMedProduct.setOutOfProduction(Boolean.parseBoolean(content));
        } else {
            currentMedProduct.setOutOfProduction(MedProduct.DEFAULT_OUT_OF_PRODUCTION);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (MedProductXmlTag.valueOfXmlTag(name)) {
                        case NAME -> currentMedProduct.setName(getXMLText(reader));
                        case PHARM -> currentMedProduct.setPharm(getXMLText(reader));
                        case GROUP -> currentMedProduct.setGroup(DrugGroup.valueOfXmlContent(getXMLText(reader)));
                        case ANALOGS -> currentMedProduct.setAnalogs(Arrays.asList(getXMLText(reader).split(SPACE_SEPARATOR)));
                        case VERSION -> currentMedProduct.setVersion(buildXMLMedProductVersion(reader));
                        case ACTIVE_SUBSTANCE -> {
                            Medicine temp = (Medicine) currentMedProduct;
                            temp.setActiveSubstance(getXMLText(reader));
                            currentMedProduct = temp;
                        }
                        case NEED_RECIPE -> {
                            Medicine temp = (Medicine) currentMedProduct;
                            temp.setNeedRecipe(Boolean.parseBoolean(getXMLText(reader)));
                            currentMedProduct = temp;
                        }
                        case COMPOSITION -> {
                            Baa temp = (Baa) currentMedProduct;
                            temp.setComposition(Arrays.asList(getXMLText(reader).split(SPACE_SEPARATOR)));
                            currentMedProduct = temp;
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals(MedProductXmlTag.MEDICINE.toString()) || name.equals(MedProductXmlTag.BAA.toString())) {
                        return currentMedProduct;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <medicine> or <baa>");
    }

    private MedProduct.DrugVersion buildXMLMedProductVersion(XMLStreamReader reader) throws XMLStreamException {
        MedProduct.DrugVersion version = new Medicine().getVersion();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (MedProductXmlTag.valueOfXmlTag(name)) {
                        case COUNTRY -> version.setCountry(Country.valueOfXmlContent(getXMLText(reader)));
                        case CERTIFICATE -> version.setCertificate(getXMLText(reader));
                        case EXPIRATION_DATE -> version.setExpirationDate(YearMonth.parse(getXMLText(reader)));
                        case PACK -> version.setPack(Pack.valueOfXmlContent(getXMLText(reader)));
                        case DOSAGE -> version.setDosage(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals(MedProductXmlTag.VERSION.toString())) {
                        return version;
                    }
            }
        }
        return version;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
