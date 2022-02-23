package by.jwd.task2.builder;

import by.jwd.task2.entity.MedProduct;
import by.jwd.task2.exception.MedProductException;

import java.util.HashSet;
import java.util.Set;

public abstract class MedProductBuilder{

    protected Set<MedProduct> medicationCatalog;

    public MedProductBuilder() {
        medicationCatalog = new HashSet<MedProduct>();
    }

    public MedProductBuilder(Set<MedProduct> medicationCatalog) {
        this.medicationCatalog = medicationCatalog;
    }

    public Set<MedProduct> getMedicationCatalog() {
        return medicationCatalog;
    }

    public void setMedicationCatalog(Set<MedProduct> medicationCatalog) {
        this.medicationCatalog = medicationCatalog;
    }

    public abstract void buildMedicationCatalog(String xmlPath) throws MedProductException;
}
