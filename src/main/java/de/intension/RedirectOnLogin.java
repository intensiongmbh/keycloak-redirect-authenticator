package de.intension;

import javax.ws.rs.core.Response;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.AuthenticatorConfigModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

import java.io.Serializable;

import static de.intension.RedirectOnLoginFactory.TIMESPAN_DEFAULT;

public class RedirectOnLogin
        implements Authenticator {

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        long timespan = getConfiguredTimespan(context);
        Response createForm = context.getSession().getProvider(LoginFormsProvider.class)
                .setAttribute("redirectUri", context.getAuthenticationSession().getRedirectUri())
                .setAttribute("seconds", timespan)
                .createForm("wait-for-redirect.ftl");
        context.challenge(createForm);
    }

    private long getConfiguredTimespan(AuthenticationFlowContext context) {
        var config = context.getAuthenticatorConfig();
        if (config == null || config.getConfig() == null) {
            return TIMESPAN_DEFAULT;
        }
        var timespan = config.getConfig().get(RedirectOnLoginFactory.TIMESPAN_CONFIG_PROPERTY);
        return Long.parseLong(timespan);
    }

    @Override
    public boolean requiresUser() {
        return true;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void close() {
        // nothing to do
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        // nothing to do
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
        // nothing to do
    }
}
