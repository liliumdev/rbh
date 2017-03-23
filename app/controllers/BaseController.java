package controllers;

import models.BaseModel;
import play.Configuration;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.ValidationError;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.BaseService;
import services.exceptions.ServiceException;
import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;

public class BaseController<M extends BaseModel<M>, S extends BaseService> extends Controller {
    protected S service;
    protected FormFactory formFactory;
    protected Configuration configuration;

    @Inject
    public void setService(S service) {
        this.service = service;
    }

    @Inject
    public void setFormFactory(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    @Inject
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Transactional
    public Result all() {
        List<M> cs = service.all();
        return ok(toJson(cs));
    }

    @Transactional
    public Result get(Long id) {
        try {
            return ok(toJson(service.get(id)));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@get");
        } catch(Exception e) {
            return internalServerError("Internal server error in BaseController@get");
        }
    }

    @Transactional
    public Result create() {
        try {
            Form<M> form = formFactory.form(getModelClassParameterType()).bindFromRequest();
            if(form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }

            return created(toJson(service.create(form.get())));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@create");
        } catch(Exception e) {
            return internalServerError("Internal server error in BaseController@create");
        }
    }

    @Transactional
    public Result update(Long id) {
        try {
            Form<M> form = formFactory.form(getModelClassParameterType()).bindFromRequest();
            if(form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }

            return ok(toJson(service.update(id, form.get())));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@update");
        } catch(Exception e) {
            return internalServerError("Internal server error in BaseController@update");
        }
    }

    @Transactional
    public Result update(Long id, M model) {
        try {
            return ok(toJson(service.update(id, model)));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@update");
        } catch(Exception e) {
            return internalServerError("Internal server error in BaseController@update");
        }
    }

    @Transactional
    public Result delete(Long id) {
        try {
            service.delete(id);
            return ok(toJson("success"));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@delete");
        } catch(Exception e) {
            return internalServerError("Internal server error in BaseController@delete");
        }
    }

    // Helper functions and classes
    public static int getQueryInt(String key, int defaultVal) {
        String s = request().getQueryString(key);
        if(s != null) {
            try {
                return Integer.parseInt(s);
            } catch(Exception e) {
            }
        }
        return defaultVal;
    }

    public static String getQueryString(String key, String defaultValue) {
        String s = request().getQueryString(key);
        if(s == null) {
            return defaultValue;
        }
        return s;
    }


    private Class<M> getModelClassParameterType() {
        return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public static class ValidationHelper {
        public static ValidationError error(String key, String value) {
            return new ValidationError(key, value);
        }

        public static List<ValidationError> singleError(String key, String value) {
            ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
            errors.add(error(key, value));
            return errors;
        }
    }
}
