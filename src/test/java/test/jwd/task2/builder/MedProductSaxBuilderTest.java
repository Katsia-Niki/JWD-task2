package test.jwd.task2.builder;

import by.jwd.task2.builder.MedProductSaxBuilder;
import by.jwd.task2.entity.*;
import by.jwd.task2.exception.MedProductException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class MedProductSaxBuilderTest {
    private static final String XML_FILE = "src\\test\\resources\\data\\medicationCatalogTest.xml";
    private MedProductSaxBuilder saxBuilder;

    @BeforeClass
    public void setUp() {
        saxBuilder = new MedProductSaxBuilder();
    }

    @Test
    public void buildMedicationCatalog() throws MedProductException {
        Medicine medProduct1 = new Medicine();
        medProduct1.setMedProductId("med1");
        medProduct1.setOutOfProduction(MedProduct.DEFAULT_OUT_OF_PRODUCTION);
        medProduct1.setName("Paracetamol");
        medProduct1.setPharm("BelMed");
        medProduct1.setGroup(DrugGroup.ANTIPYRETIC);
        medProduct1.setAnalogs(Arrays.asList("Ibufen", "Aspirin"));
        medProduct1.setActiveSubstance("paracetamol");
        medProduct1.setNeedRecipe(false);
        medProduct1.getVersion().setCountry(Country.BELARUS);
        medProduct1.getVersion().setDosage("100mg");
        medProduct1.getVersion().setCertificate("45-08c");
        medProduct1.getVersion().setExpirationDate(YearMonth.parse("2023-04"));
        medProduct1.getVersion().setPack(Pack.PILL);

        Baa medProduct2 = new Baa();
        medProduct2.setMedProductId("med2");
        medProduct2.setOutOfProduction(MedProduct.DEFAULT_OUT_OF_PRODUCTION);
        medProduct2.setName("Vitrum");
        medProduct2.setPharm("Pharmlend");
        medProduct2.setGroup(DrugGroup.VITAMIN);
        medProduct2.setAnalogs(Arrays.asList("Vita", "Alphabet"));
        medProduct2.setComposition(Arrays.asList("C", "A", "E", "Na", "Cl", "K", "Mg", "Ca"));
        medProduct2.getVersion().setCountry(Country.POLAND);
        medProduct2.getVersion().setCertificate("29-23v");
        medProduct2.getVersion().setDosage("300mg");
        medProduct2.getVersion().setPack(Pack.CAPSULE);
        medProduct2.getVersion().setExpirationDate(YearMonth.parse("2022-11"));

        Set<MedProduct> expected = new HashSet<MedProduct>();
        expected.add(medProduct1);
        expected.add(medProduct2);

        saxBuilder.buildMedicationCatalog(XML_FILE);
        Set<MedProduct> actual = saxBuilder.getMedicationCatalog();

        assertEquals(actual, expected);
    }

}