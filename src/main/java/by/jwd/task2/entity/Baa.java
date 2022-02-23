package by.jwd.task2.entity;

import java.util.List;
import java.util.StringJoiner;

public class Baa extends MedProduct {
    private List<String> composition;

    public Baa() {

    }

    public Baa(String medProductId, boolean outOfProduction, String name, String pharm, DrugGroup group, List<String> analogs, DrugVersion version, List<String> composition) {
        super(medProductId, outOfProduction, name, pharm, group, analogs, version);
        this.composition = composition;
    }

    public List<String> getComposition() {
        return composition;
    }

    public void setComposition(List<String> composition) {
        this.composition = composition;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((composition) == null ? 0 : composition.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        Baa other = (Baa) obj;
        if (composition == null) {
            if (other.composition != null) {
                return false;
            }
        } else if (!composition.equals(other.composition)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Baa.class.getSimpleName() + "[", "]")
                .add("composition = " + composition).toString();
    }
}
