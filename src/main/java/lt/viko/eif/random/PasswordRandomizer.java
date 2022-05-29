package lt.viko.eif.random;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PasswordRandomizer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordRandomizer.class);
    private static final String CHARACTERS
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";

    public String getRandomPassword() {
        String password = RandomStringUtils.random(15, CHARACTERS);

        LOGGER.info("Generated password: {}", password);
        return password;
    }
}
