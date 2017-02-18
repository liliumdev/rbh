package services;

import models.City;
import models.Country;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import repositories.CityRepository;
import repositories.exceptions.RepositoryException;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import javax.xml.ws.Service;
import java.util.List;

/**
 * Created by Lilium on 14.1.2017.
 */

public class CityService extends BaseService<City, CityRepository>
{
    private CountryService countryService;

    @Inject
    public void setCountryService(CountryService countryService)
{
    this.countryService = countryService;
}

    public City create(Long countryId, City model) throws ServiceException {
        try {
            Country country = countryService.get(countryId);
            if (country == null) {
                throw new ServiceException("Service couldn't find country [" + countryId + "].");
            }
            model.setCountry(country);
            repository.save(model);
            return model;
        } catch (RepositoryException e) {
            throw new ServiceException("Service couldn't create model.", e);
        }
    }

    public List<City> allFromCountry(Long countryId) throws ServiceException {
        try {
            Country country = countryService.get(countryId);
            if (country == null) {
                throw new ServiceException("Service couldn't find country [" + countryId + "].");
            }
            return country.getCities();
        } catch (ServiceException e) {
            throw new ServiceException("Service couldn't return all cities from the country.", e);
        }
    }

    public List<Object[]> allWithCount() throws ServiceException {
        try {
            Criteria criteria = repository.getSession().createCriteria(City.class);

            ProjectionList projList = Projections.projectionList();
            projList.add(Property.forName("name").group());
            projList.add(Projections.countDistinct("restaurants.id"));
            criteria.createAlias("restaurants", "restaurants", CriteriaSpecification.LEFT_JOIN);
            criteria.setProjection(projList);

            return criteria.list();
        } catch (Exception e) {
            throw new ServiceException("Service couldn't return all cities with count.", e);
        }
    }
}
