package by.jwd.task2.entity;

public enum Pack {
    PILL,
    CAPSULE,
    MIXTURE;

    public static Pack valueOfXmlContent(String content) {
        return Pack.valueOf(content.toUpperCase());
    }
}
