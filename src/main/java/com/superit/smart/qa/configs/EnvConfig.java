package com.superit.smart.qa.configs;

import com.superit.smart.qa.api.enums.Language;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

import java.time.Duration;

/**
 * Created by R0manL.
 *
 * Description. Read environment configuration from *.properties file for defined environment.
 *
 * Environment (qa, ephemeral) can be defined from commandline (via sys props). Example: 'mvn -Denv=qa test'
 * If env has not defined, means that we run tests from IDE, default.properties will be used.
 *
 * Note. Please, note that load strategy = MERGE (more info http://owner.aeonbits.org/docs/loading-strategies/)
 *       In this case, for every property all the specified URLs will be queries, and the first resource defining
 *       the property will prevail. More in detail, this is what will happen:
 *       - First, it will try to load the given property from system properties (maven parameters);
 *         if the given property is found the associated value will be returned.
 *       - Then it will try to load the given property from ~/resources/${env}-env.properties;
 *         if the property is found the value associated will be returned.
 *       - Finally, it will try to load the given property from the ~/resources/default.properties;
 *
 * Note. If any property has missed in all *.property files, NullPointerException will be shown when you try to read it
 *       for the first time.
 */

@LoadPolicy(LoadType.MERGE)
@Sources({
        "system:properties",
        "classpath:${env}-env.properties",
        "classpath:default.properties"})
public interface EnvConfig extends Config {

   EnvConfig ENV_CONFIG = ConfigFactory.create(EnvConfig.class);

   @Key("lang")
   @DefaultValue("EN")
   Language lang();


   /* Users */
   @Key("user1.email")
   String user1Email();

   @Key("user1.password")
   String user1Password();

   @Key("user1.userId")
   int user1UserId();

   @Key("user2.email")
   String user2Email();

   @Key("user2.password")
   String user2Password();

   @Key("user2.userId")
   int user2UserId();

   @Key("user3.email")
   String user3Email();

   @Key("user3.password")
   String user3Password();

   @Key("user3.userId")
   int user3UserId();

   @Key("user4.email")
   String user4Email();

   @Key("user4.password")
   String user4Password();

   @Key("user4.userId")
   int user4UserId();

   @Key("client.id")
   int clientId();

   /* URls */
   @Key("smartbox.web.url")
   String smartboxWebUrl();

   @Key("smartbox.api.url")
   String smartboxApiUrl();


   /* DB connection */
   @Key("db.url")
   String dbUrl();

   @Key("db.login")
   String dbLogin();

   @Key("db.password")
   String dbPassword();

   @Key("api.client.id")
   String apiClientId();

   @Key("auth.url")
   String authUrl();

   @Key("auth.scope")
   String authScope();


   /* Timeouts */
   @Key("web.element.refresh.timeout")
   @ConverterClass(DurationConverter.class)
   Duration webElmRefreshDuration();

   @Key("web.element.load.timeout")
   @ConverterClass(DurationConverter.class)
   Duration webElmLoadDuration();

   @Key("page.load.timeout")
   @ConverterClass(DurationConverter.class)
   Duration pageLoadDuration();

   @Key("awaitility.timeout")
   long awaitilityTimeout();

   @Key("awaitility.poll.interval")
   long awaitilityPollInterval();

   @Key("http.socket.timeout")
   long httpSocketTimeout();

   @Key("http.connection.timeout")
   long httpConnectionTimeout();

   @Key("notification.wait.timeout")
   @ConverterClass(DurationConverter.class)
   Duration notificationWaitTimeout();


   /* Constants */
   @Key("test.prefix")
   String testPrefix();

   @Key("default.planning.top.filter.id")
   String defaultPlanningTopFilterId();

   @Key("topfilter.max.links.amount")
   int maxAmountOfLinks();

   @Key("threads")
   @DefaultValue("1")
   int threads();
}