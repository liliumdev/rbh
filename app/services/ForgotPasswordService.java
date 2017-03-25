package services;

import models.Account;
import models.ForgotPassword;
import org.hibernate.criterion.Restrictions;
import repositories.ForgotPasswordRepository;
import services.exceptions.ServiceException;

import javax.inject.Inject;

public class ForgotPasswordService extends BaseService<ForgotPassword, ForgotPasswordRepository> {
    private AccountService accountService;

    @Inject
    public void setAccountService(AccountService accountService)
    {
        this.accountService = accountService;
    }

    public ForgotPassword create(String email) throws ServiceException {
        Account account = accountService.getByEmail(email);
        ForgotPassword forgotPassword = new ForgotPassword();
        forgotPassword.setAccount(account);
        forgotPassword.setToken(java.util.UUID.randomUUID().toString());
        return create(forgotPassword);
    }

    public ForgotPassword getByToken(String token)
    {
        return (ForgotPassword)(repository.getSession().createCriteria(ForgotPassword.class)
                .add(Restrictions.eq("token", token))
                .uniqueResult());
    }
}
