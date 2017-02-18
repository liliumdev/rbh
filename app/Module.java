import com.google.inject.AbstractModule;
import repositories.BaseRepository;
import repositories.BaseRepositoryImpl;

public class Module extends AbstractModule {

    @Override
    public void configure() {
        bind(BaseRepository.class).to(BaseRepositoryImpl.class);
    }

}
