package services;

import controllers.forms.RegisterForm;
import controllers.forms.admin.AccountCreationForm;
import models.Account;
import models.City;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;
import repositories.AccountRepository;
import repositories.exceptions.RepositoryException;
import services.exceptions.ServiceException;

import javax.inject.Inject;

/**
 * Created by Lilium on 14.1.2017.
 */

public class AccountService extends BaseService<Account, AccountRepository>
{
    private CityService cityService;

    @Inject
    public void setCityService(CityService cityService)
    {
        this.cityService = cityService;
    }

    public enum USER_TYPE {
        NORMAL(0), ADMIN(1);

        private final int value;

        USER_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public Account create(Long cityId, Account model, int userType) throws ServiceException {
        try {
            City city = cityService.get(cityId);
            if (city == null) {
                throw new ServiceException("Service couldn't find city [" + cityId + "].");
            }
            model.setCity(city);
            model.setRole(userType);
            repository.save(model);
            return model;
        } catch (RepositoryException e) {
            throw new ServiceException("Service couldn't create model.", e);
        }
    }

    public Account giveRole(Long id, Integer role) throws ServiceException {
        try {
            Account account = get(id);
            if (account == null) {
                throw new ServiceException("Service couldn't find account [" + id + "].");
            }

            account.setRole(role);
            return update(id, account, false);
        } catch (Exception e) {
            throw new ServiceException("Service couldn't demote an account.", e);
        }
    }

    // Default create method which creates a normal account
    public Account create(AccountCreationForm form) throws ServiceException {
        return create(form, USER_TYPE.NORMAL.getValue());
    }

    // Create method which creates an account of a given type
    public Account create(AccountCreationForm form, int userType) throws ServiceException {
        Account account = new Account();
        account.setFirstName(form.getFirstName());
        account.setLastName(form.getLastName());
        account.setEmail(form.getEmail());
        account.setPassword(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt()));
        return create(form.getCityId(), account, userType);
    }

    public Account update(Long id, Account data) throws ServiceException {
        return update(id, data, true);
    }

    public Account update(Long id, Account data, Boolean hashPw) throws ServiceException {
        if(data.getPassword() != null && hashPw) {
            data.setPassword(BCrypt.hashpw(data.getPassword(), BCrypt.gensalt()));
        }
        return super.update(id, data);
    }

    public Account getByEmail(String email)
    {
        return (Account)(repository.getSession().createCriteria(Account.class)
                                                .add(Restrictions.eq("email", email))
                                                .uniqueResult());
    }

    public Account getByEmailAndPassword(String email, String password)
    {
        Account account = (Account)(repository.getSession().createCriteria(Account.class)
                          .add(Restrictions.eq("email", email))
                          .uniqueResult());

        if(account != null && BCrypt.checkpw(password, account.getPassword()))
            return account;

        return null;
    }

    public USER_TYPE getUserRole(Long id) throws ServiceException
    {
        try
        {
            return USER_TYPE.values()[get(id).getRole()];
        }
        catch(ServiceException e)
        {
            throw new ServiceException("Service couldn't get user role");
        }
    }

    public USER_TYPE getUserRole(Account account)
    {
        return USER_TYPE.values()[account.getRole()];
    }
}
