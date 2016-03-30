package com.github.fmpinheiro.productflow;

import com.github.fmpinheiro.productflow.pages.home.HomePage;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.RequestCycleSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@EnableAutoConfiguration
@Component
@ComponentScan
public class ProductFlowApplication extends WebApplication {

    @Autowired
    private ApplicationContext applicationContext;


    public static void main(String[] args) {
        SpringApplication.run(ProductFlowApplication.class, args);
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    /**
     * Wicket Integration based on https://github.com/Pentadrago/spring-boot-example-wicket
     */
    @Override
    protected void init() {
        super.init();
        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));

        // security
//        this.getSecuritySettings().setAuthorizationStrategy(new WicketAuthorization());

        // remove wicket tags, spaces and comments
        this.getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        this.getMarkupSettings().setStripWicketTags(true);
        this.getMarkupSettings().setStripComments(true);
        this.getMarkupSettings().setCompressWhitespace(true);

        // resource caching
        this.getResourceSettings().setDefaultCacheDuration(Duration.days(30));
        this.getResourceSettings().setResourcePollFrequency(RuntimeConfigurationType.DEPLOYMENT == getConfigurationType() ? Duration.ONE_SECOND : null);

        // prevent page redirection
        this.getRequestCycleSettings().setRenderStrategy(RequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
    }

    @Override
    public RuntimeConfigurationType getConfigurationType() {
        return RuntimeConfigurationType.DEVELOPMENT;
    }
}
