package by.jwd.task2.entity;

import java.time.YearMonth;
import java.util.List;
import java.util.StringJoiner;

public abstract class MedProduct {
    public static final boolean DEFAULT_OUT_OF_PRODUCTION = false;

    private String medProductId;
    private boolean outOfProduction;
    private String name;
    private String pharm;
    private DrugGroup group;
    private List<String> analogs;
    private DrugVersion version = new DrugVersion();

    public MedProduct() {

    }

    public MedProduct(String medProductId, boolean outOfProduction, String name, String pharm, DrugGroup group,
                      List<String> analogs, DrugVersion version) {
        this.medProductId = medProductId;
        this.outOfProduction = outOfProduction;
        this.name = name;
        this.pharm = pharm;
        this.group = group;
        this.analogs = analogs;
        this.version = version;
    }

    public String getMedProductId() {
        return medProductId;
    }

    public void setMedProductId(String medProductId) {
        this.medProductId = medProductId;
    }

    public boolean isOutOfProduction() {
        return outOfProduction;
    }

    public void setOutOfProduction(boolean outOfProduction) {
        this.outOfProduction = outOfProduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public DrugGroup getGroup() {
        return group;
    }

    public void setGroup(DrugGroup group) {
        this.group = group;
    }

    public List<String> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(List<String> analogs) {
        this.analogs = analogs;
    }

    public DrugVersion getVersion() {
        return version;
    }

    public void setVersion(DrugVersion version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedProduct that = (MedProduct) o;

        if (outOfProduction != that.outOfProduction) return false;
        if (medProductId != null ? !medProductId.equals(that.medProductId) : that.medProductId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pharm != null ? !pharm.equals(that.pharm) : that.pharm != null) return false;
        if (group != that.group) return false;
        if (analogs != null ? !analogs.equals(that.analogs) : that.analogs != null) return false;
        return version != null ? version.equals(that.version) : that.version == null;
    }

    @Override
    public int hashCode() {
        int result = medProductId != null ? medProductId.hashCode() : 0;
        result = 31 * result + (outOfProduction ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pharm != null ? pharm.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (analogs != null ? analogs.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MedProduct.class.getSimpleName() + "[", "]")
                .add("medProductId = " + medProductId)
                .add("outOfProduction = " + outOfProduction)
                .add("name = " + name)
                .add("pharm = " + pharm)
                .add("group = " + group)
                .add("analogs = " + analogs)
                .add("version = " + version).toString();
    }

    public class DrugVersion {
        private Country country;
        private String certificate;
        private YearMonth expirationDate;
        private Pack pack;
        private String dosage;

        public DrugVersion() {

        }

        public DrugVersion(Country country, String certificate, YearMonth expirationDate, Pack pack, String dosage) {
            this.country = country;
            this.certificate = certificate;
            this.expirationDate = expirationDate;
            this.pack = pack;
            this.dosage = dosage;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public YearMonth getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(YearMonth expirationDate) {
            this.expirationDate = expirationDate;
        }

        public Pack getPack() {
            return pack;
        }

        public void setPack(Pack pack) {
            this.pack = pack;
        }

        public String getDosage() {
            return dosage;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DrugVersion that = (DrugVersion) o;

            if (country != that.country) return false;
            if (certificate != null ? !certificate.equals(that.certificate) : that.certificate != null) return false;
            if (expirationDate != null ? !expirationDate.equals(that.expirationDate) : that.expirationDate != null)
                return false;
            if (pack != that.pack) return false;
            return dosage != null ? dosage.equals(that.dosage) : that.dosage == null;
        }

        @Override
        public int hashCode() {
            int result = country != null ? country.hashCode() : 0;
            result = 31 * result + (certificate != null ? certificate.hashCode() : 0);
            result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
            result = 31 * result + (pack != null ? pack.hashCode() : 0);
            result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", DrugVersion.class.getSimpleName() + "[", "]")
                    .add("country = " + country)
                    .add("certificate = " + certificate)
                    .add("expirationDate = " + expirationDate)
                    .add("pack = " + pack)
                    .add("dosage = = " + dosage).toString();
        }
    }
}