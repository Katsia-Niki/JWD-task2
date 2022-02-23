package by.jwd.task2.builder;

public enum MedProductXmlTag {
    MED_CATALOG,
    MEDICINE,
    BAA,
    NAME,
    PHARM,
    GROUP,
    ANALOGS,
    ACTIVE_SUBSTANCE,
    NEED_RECIPE,
    COMPOSITION,
    COUNTRY,
    CERTIFICATE,
    EXPIRATION_DATE,
    PACK,
    DOSAGE,
    VERSION;

    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    @Override
    public String toString() {
        return this.name().toLowerCase().replace(UNDERSCORE, HYPHEN);
    }

    public static MedProductXmlTag valueOfXmlTag(String tag) {
        return MedProductXmlTag.valueOf(tag.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
}
