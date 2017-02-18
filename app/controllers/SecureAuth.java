package controllers;

/**
 * Created by Lilium on 28.1.2017.
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import play.Logger;
import play.Play;
import play.inject.Injector;
import play.mvc.Action;
import play.mvc.Http.*;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.With;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.*;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;


public class SecureAuth {
    @With(AuthenticatedAction.class)
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Authenticated {
        String[] roles() default "NORMAL";
    }

    public static class AuthenticatedAction extends Action<Authenticated> {

        private final Injector injector;

        @Inject
        public AuthenticatedAction(Injector injector) {
            this.injector = injector;
        }

        public CompletionStage<Result> call(final Context ctx) {
            Authenticator authenticator = injector.instanceOf(Authenticator.class);
            String username = authenticator.getUsername(ctx, configuration.roles());
            if(username == null) {
                Result unauthorized = authenticator.onUnauthorized(ctx);
                return CompletableFuture.completedFuture(unauthorized);
            } else {
                try {
                    ctx.request().setUsername(username);
                    return delegate.call(ctx).whenComplete(
                            (result, error) -> ctx.request().setUsername(null)
                    );
                } catch (Exception e) {
                    ctx.request().setUsername(null);
                    throw e;
                }
            }
        }

    }

    public static class Authenticator extends Results {
        public String getUsername(Context ctx, String[] requiredRoles) {
            String token = ctx.request().getHeader("Authorization");
            if(token != null) {
                try {
                    // Remove "Bearer " from our token
                    if(token.length() < 8) return null; // no token
                    token = token.substring(7);
                    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Play.application().configuration().getString("play.crypto.secret")))
                            .build();
                    verifier.verify(token);

                    // It's verified
                    JWT jwt = JWT.decode(token);
                    String username = jwt.getClaim("email").asString();
                    String role = jwt.getClaim("role").asString();
                    if(Arrays.asList(requiredRoles).contains(role))
                        return username;

                    return null;
                } catch (JWTVerificationException e){
                    // The token is invalid, has been tampered with because
                    // the signature is not proper; or the syntax is wrong
                    return null;
                } catch (UnsupportedEncodingException e) {
                    // This shouldn't happen
                    Logger.debug("Unsupported encoding error in Authenticator class");
                    return null;
                }
            }

            return null;
        }

        public Result onUnauthorized(Context ctx) {
            return unauthorized("Not authorized.");
        }

    }


}