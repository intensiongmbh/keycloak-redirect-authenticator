package de.intension;

import java.util.LinkedList;
import java.util.List;

import org.keycloak.Config.Scope;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

public class RedirectOnLoginFactory implements AuthenticatorFactory {

    private static final String PROVIDER_ID = "redirect-on-login";
    static final String TIMESPAN_CONFIG_PROPERTY = "timespan";
    static final long TIMESPAN_DEFAULT = 5;

    private static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {AuthenticationExecutionModel.Requirement.REQUIRED, AuthenticationExecutionModel.Requirement.DISABLED,};

    @Override
    public Authenticator create(KeycloakSession session) {
        return new RedirectOnLogin();
    }

    @Override
    public void init(Scope config) {
        // nothing to do
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // nothing to do
    }

    @Override
    public void close() {
        // nothing to do
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "Redirection page";
    }

    @Override
    public String getReferenceCategory() {
        return "info page";
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Displays an information page that automatically redirects the user after a configured timespan";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        List<ProviderConfigProperty> configProperties = new LinkedList<>();
        ProviderConfigProperty secondsProperty = new ProviderConfigProperty();
        secondsProperty.setName(TIMESPAN_CONFIG_PROPERTY);
        secondsProperty.setType(ProviderConfigProperty.STRING_TYPE);
        secondsProperty.setLabel("Timespan");
        secondsProperty.setHelpText("Amount of seconds to wait before redirecting");
        secondsProperty.setDefaultValue(String.valueOf(TIMESPAN_DEFAULT));
        configProperties.add(secondsProperty);
        return configProperties;
    }
}
