package by.jwd.task2.entity;

public enum Country {
    BELARUS,
    HUNGARY,
    GERMANY,
    POLAND;

    public static Country valueOfXmlContent(String content) {
        return Country.valueOf(content.toUpperCase());
    }
}
