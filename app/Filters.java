/**
 * Created by Lilium on 29.1.2017.
 */
import play.mvc.EssentialFilter;
import play.filters.cors.CORSFilter;
import play.http.HttpFilters;
import javax.inject.Inject;

public class Filters implements HttpFilters {

    @Inject
    CORSFilter corsFilter;

    public EssentialFilter[] filters() {
        return new EssentialFilter[] { corsFilter.asJava() };
    }
}