package commands;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import algorithms.BasedOnIdAlgorithm;

/**
 * 
 * Commands class
 * 
 * Класс для работы с командной строкой
 * 
 * 
 * @author Balukova Elena
 * @created 26 окт. 2014 г.
 * 
 */
@Component
public class Commands implements CommandMarker {

    @CliCommand(value = "decode", help = "Decode short link into original value")
    public String decode(

            @CliOption(key = { "value" }, mandatory = true, help = "Decode short link into original value") String shortLink) {

        String result = BasedOnIdAlgorithm.decode(shortLink);

        return null == result ? "Sorry, this link doesn't exists!" : result;
    }

    @CliCommand(value = "encode", help = "Encode oruginal url into short link")
    public String encode(

            @CliOption(key = { "value" }, mandatory = true, help = "Encode oruginal url into short link") String url) {

        UrlValidator urlValidator = new UrlValidator();

        return urlValidator.isValid(url) ? BasedOnIdAlgorithm.encode(url)
                : "Sorry, url isn't valid!";
    }
}
