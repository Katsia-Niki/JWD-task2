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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((analogs == null) ? 0 : analogs.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((medProductId == null) ? 0 : medProductId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (outOfProduction ? 1231 : 1237);
        result = prime * result + ((pharm == null) ? 0 : pharm.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MedProduct other = (MedProduct) obj;
        if(analogs == null) {
            if(other.analogs != null) {
                return false;
            }
        } else if (!analogs.equals(other.analogs)) {
            return false;
        }
        if(!group.equals(other.group)) {
            return false;
        }
        if(medProductId == null) {
            if(other.medProductId != null) {
                return false;
            }
        } else if (!medProductId.equals(other.medProductId)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if(outOfProduction != other.outOfProduction) {
            return false;
        }
        if(pharm == null) {
            if(other.pharm != null) {
                return false;
            }
        } else if (!pharm.equals(other.pharm)) {
            return false;
        }
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        } else if (!version.equals(other.version)) {
            return false;
        }
        return true;
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
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((country == null) ? 0 : country.hashCode());
            result = prime * result + ((certificate == null) ? 0 : certificate.hashCode());
            result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
            result = prime * result + ((pack == null) ? 0 : pack.hashCode());
            result = prime * result + ((dosage == null) ? 0 : dosage.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            DrugVersion other = (DrugVersion) obj;

            if (country != other.country) {
                return false;
            }
            if (certificate == null) {
                if (other.certificate != null) {
                    return false;
                }
            } else if (!certificate.equals(other.certificate)) {
                return false;
            }
            if (expirationDate == null) {
                if (other.expirationDate != null) {
                    return false;
                }
            } else if (!expirationDate.equals(other.expirationDate)) {
                return false;
            }
            if (pack != other.pack) {
                return false;
            }
            if (dosage == null) {
                if (other.dosage != null) {
                    return false;
                }
            } else if (!dosage.equals(other.dosage)) {
                return false;
            }
            return true;
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