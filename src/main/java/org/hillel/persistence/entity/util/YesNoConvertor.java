package org.hillel.persistence.entity.util;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class YesNoConvertor implements AttributeConverter<Boolean, String> {
    private enum YesNoTypeValue {
        YES("yes", true), NO("no", false);
        private final String DB_VALUE;
        private final boolean ENTITY_VALUE;

        YesNoTypeValue(String db_value, boolean entity_value) {
            this.DB_VALUE = db_value;
            this.ENTITY_VALUE = entity_value;
        }

        private static final YesNoTypeValue getByDBValue(String value){
            if (StringUtils.isEmpty(value)) return NO;
            for (YesNoTypeValue type: values()) {
                if(Objects.equals(type.DB_VALUE, value)) return type;
            }
            return NO;
        }

        private static final YesNoTypeValue getByEntityValue(Boolean value){
            if (Objects.isNull(value)) return NO;
            for (YesNoTypeValue type: values()) {
                if(Objects.equals(type.ENTITY_VALUE, value)) return type;
            }
            return NO;
        }
    }

    @Override
    public String convertToDatabaseColumn(Boolean entityValue) {
        return YesNoTypeValue.getByEntityValue(entityValue).DB_VALUE;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbValue) {
        return YesNoTypeValue.getByDBValue(dbValue).ENTITY_VALUE;
    }
}
