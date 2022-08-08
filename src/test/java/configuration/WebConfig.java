package configuration;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:properties/credential.properties"})
public interface WebConfig extends Config {

    @Key("userLogin")
    String login();

    @Key("userPassword")
    String password();

    @Key("baseUrl")
    String basebUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    String browser();

    @Key("browser.version")
    @DefaultValue("104.0")
    String browserVersion();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("browser.position")
    @DefaultValue("0x0")
    String browserPosition();
}
