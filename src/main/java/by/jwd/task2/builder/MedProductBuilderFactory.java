package by.jwd.task2.builder;

import by.jwd.task2.exception.MedProductException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class MedProductBuilderFactory {

    static Logger logger = LogManager.getLogger();

    private enum TypeParser {
        DOM, SAX, STAX
    }
    private MedProductBuilderFactory() {

    }

    public static MedProductBuilder createBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());

        switch (type) {
            case DOM -> {
                return new MedProductDomBuilder();
            }
            case SAX -> {
                return new MedProductSaxBuilder();
            }
            case STAX -> {
                return new MedProductStaxBuilder();
            }
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }

    }
}
