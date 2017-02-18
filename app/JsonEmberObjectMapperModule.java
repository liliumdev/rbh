import com.google.inject.AbstractModule;

/**
 * This module binds the JsonEmberObjectMapper instead of Play's
 * default Jackson ObjectMapper.
 */
public class JsonEmberObjectMapperModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(JsonEmberObjectMapper.class).asEagerSingleton();
    }
}
