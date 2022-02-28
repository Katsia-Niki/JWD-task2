package by.jwd.task2.builder;

import by.jwd.task2.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class MedProductHandler extends DefaultHandler {
    static Logger logger = LogManager.getLogger();

    private static final String SPACE_SEPARATOR = " ";
    private Set<MedProduct> medCatalog;
    private MedProduct currentMedProduct;
    private EnumSet<MedProductXmlTag> possibleXmlTag;
    private MedProductXmlTag currentXmlTag;

    public MedProductHandler() {
        medCatalog = new HashSet<MedProduct>();
        possibleXmlTag = EnumSet.range(MedProductXmlTag.NAME, MedProductXmlTag.DOSAGE);
    }

    public Set<MedProduct> getMedCatalog() {
        return medCatalog;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String medicineTag = MedProductXmlTag.MEDICINE.toString();
        String baaTag = MedProductXmlTag.BAA.toString();

        if (medicineTag.equals(qName) || baaTag.equals(qName)) {
            currentMedProduct = medicineTag.equals(qName) ? new Medicine() : new Baa();
            defineAttributes(attributes);
        } else {
            MedProductXmlTag temp = MedProductXmlTag.valueOfXmlTag(qName);
            if (possibleXmlTag.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String medicineTag = MedProductXmlTag.MEDICINE.toString();
        String baaTag = MedProductXmlTag.BAA.toString();

        if (medicineTag.equals(qName) || baaTag.equals(qName)) {
            medCatalog.add(currentMedProduct);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();

        if(currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME -> currentMedProduct.setName(data);
                case PHARM -> currentMedProduct.setPharm(data);
                case GROUP -> currentMedProduct.setGroup(DrugGroup.valueOfXmlContent(data));
                case ANALOGS -> currentMedProduct.setAnalogs(Arrays.asList(data.split(SPACE_SEPARATOR)));
                case ACTIVE_SUBSTANCE -> {
                    Medicine temp = (Medicine) currentMedProduct;
                    temp.setActiveSubstance(data);
                    currentMedProduct = temp;
                }
                case NEED_RECIPE -> {
                    Medicine temp = (Medicine) currentMedProduct;
                    temp.setNeedRecipe(Boolean.parseBoolean(data));
                    currentMedProduct = temp;
                }
                case COMPOSITION -> {
                    Baa temp = (Baa) currentMedProduct;
                    temp.setComposition(Arrays.asList(data.split(SPACE_SEPARATOR)));
                    currentMedProduct = temp;
                }
                case COUNTRY -> currentMedProduct.getVersion().setCountry(Country.valueOfXmlContent(data));
                case CERTIFICATE -> currentMedProduct.getVersion().setCertificate(data);
                case EXPIRATION_DATE -> currentMedProduct.getVersion().setExpirationDate(YearMonth.parse(data));
                case PACK -> currentMedProduct.getVersion().setPack(Pack.valueOfXmlContent(data));
                case DOSAGE -> currentMedProduct.getVersion().setDosage(data);

                default -> throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

    private void defineAttributes(Attributes attributes) {
        String medProductId = attributes.getValue(MedProductXmlAttribute.ID.toString());
        currentMedProduct.setMedProductId(medProductId);

        String outOfProduction = attributes.getValue(MedProductXmlAttribute.OUT_OF_PRODACTION.toString());
        if (outOfProduction != null) {
            currentMedProduct.setOutOfProduction(Boolean.parseBoolean(outOfProduction));
        } else {
            currentMedProduct.setOutOfProduction(MedProduct.DEFAULT_OUT_OF_PRODUCTION);
        }
    }

}
