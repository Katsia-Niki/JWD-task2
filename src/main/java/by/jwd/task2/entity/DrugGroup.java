package by.jwd.task2.entity;

public enum DrugGroup {
    ANTIPYRETIC,
    ANTIVIRAL,
    ANTIBIOTIC,
    VITAMIN;

    public static DrugGroup valueOfXmlContent(String content) {
        return DrugGroup.valueOf(content.toUpperCase());
    }
}
