import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import play.libs.Json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This class overwrites the default Play's Jackson object mapper,
 * so it's easily configurable with custom (De)SerializationFeatures.
 * Originally intended to be configured with WRAP_ROOT_VALUE as
 * Ember's RESTAdapter expects responses with a root element.
 */
public class JsonEmberObjectMapper {
    JsonEmberObjectMapper() {
        ObjectMapper mapper = Json.newDefaultMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true);
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mapper.setDateFormat(df);
        mapper.registerModule(new JtsModule());

        Json.setObjectMapper(mapper);
    }
}
