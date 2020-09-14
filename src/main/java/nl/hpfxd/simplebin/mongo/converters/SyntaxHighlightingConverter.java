package nl.hpfxd.simplebin.mongo.converters;

import dev.morphia.converters.SimpleValueConverter;
import dev.morphia.converters.TypeConverter;
import dev.morphia.mapping.MappedField;
import nl.hpfxd.simplebin.SyntaxHighlighting;

public class SyntaxHighlightingConverter extends TypeConverter implements SimpleValueConverter {
    public SyntaxHighlightingConverter() {
        super(SyntaxHighlighting.class);
    }

    @Override
    public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
        if (fromDBObject == null) return null;
        return SyntaxHighlighting.findFromId((int) fromDBObject);
    }

    @Override
    public Object encode(Object value, MappedField optionalExtraInfo) {
        return ((SyntaxHighlighting) value).getId();
    }
}
