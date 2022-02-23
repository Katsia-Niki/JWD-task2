package by.jwd.task2.entity;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Medicine extends MedProduct{
    private String activeSubstance;
    private boolean needRecipe;

    public Medicine() {

    }

    public Medicine(String activeSubstance, boolean needRecipe) {
        this.activeSubstance = activeSubstance;
        this.needRecipe = needRecipe;
    }

    public Medicine(String medProductId, boolean outOfProduction, String name, String pharm, DrugGroup group,
                    List<String> analogs, DrugVersion version, String activeSubstance, boolean needRecipe) {
        super(medProductId, outOfProduction, name, pharm, group, analogs, version);
        this.activeSubstance = activeSubstance;
        this.needRecipe = needRecipe;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public boolean isNeedRecipe() {
        return needRecipe;
    }

    public void setNeedRecipe(boolean needRecipe) {
        this.needRecipe = needRecipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Medicine medicine = (Medicine) o;
        return needRecipe == medicine.needRecipe && Objects.equals(activeSubstance, medicine.activeSubstance);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((activeSubstance == null) ? 0 : activeSubstance.hashCode());
        result = prime * result + (needRecipe ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
         return new StringJoiner(", ", DrugVersion.class.getSimpleName() + "[", "]")
                    .add("activeSubstance = " + activeSubstance)
                    .add("needRecipe = = " + needRecipe).toString();
    }
}
