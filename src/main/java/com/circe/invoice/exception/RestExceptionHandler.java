package com.circe.invoice.exception;

import com.circe.invoice.configuration.CirceConfiguration;
import com.circe.invoice.exception.badrequest.ClientBadRequestException;
import com.circe.invoice.exception.badrequest.DesignationBadRequestException;
import com.circe.invoice.exception.notfound.UserNotFoundException;
import com.circe.invoice.exception.notfound.DesignationNotFoundException;
import com.circe.invoice.exception.notfound.FactureNotFoundException;
import com.circe.invoice.exception.notfound.PropertyKeyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CirceConfiguration configuration;

    /**
     * Get a message from a property file given a key and a language
     * @param messageKey Message key
     * @param language Message language
     * @return Localized message
     * @throws PropertyKeyNotFoundException Exception thrown if the message key is not found in the associated property file
     */
    private String getMessageInLanguage(String messageKey, String language) throws PropertyKeyNotFoundException {
        Locale locale = Locale.forLanguageTag(language);
        try {
            return messageSource.getMessage(messageKey, null, locale);
        } catch (NoSuchMessageException e) {
            throw new PropertyKeyNotFoundException("Property " + messageKey + " not found in messages_" + locale.toLanguageTag() + ".properties (or messages.properties if locale is French)");
        }
    }

    /**
     * Get a map containing, for each supported language, the localized associated message given a messageKey
     * @param messageKey Key of the message to get in property files
     * @return LocalizedMessageMap
     */
    private Map<String, String> getLocalizedMessageMap(String messageKey) {

        Map<String, String> localizedMessageMap = new HashMap<>();
        String message;

        for(String language : Arrays.asList("fr", "en")) {
            try {
                message = getMessageInLanguage(messageKey, language);
                localizedMessageMap.put(language, message);
            } catch (PropertyKeyNotFoundException e) {
                // If the message couldn't be loaded in a particular language, set the message to null for that language
                localizedMessageMap.put(language, null);
            }
        }

        return localizedMessageMap;
    }

    /**
     * Build the ResponseEntity sent to the client
     * @param localizedMessageMap Response body containing a map, with the message to display in each supported language
     * @param httpStatus Response status
     * @return ResponseEntity
     */
    private ResponseEntity<Object> buildResponseEntity(Map<String, String> localizedMessageMap, HttpStatus httpStatus) {
        return new ResponseEntity<>(localizedMessageMap, httpStatus);
    }

    /* ---------------------- NOT FOUND EXCEPTIONS ---------------------- */

    @ExceptionHandler(FactureNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(FactureNotFoundException ex) {

        String messageKey = "exception.not.found.facture";
        return buildResponseEntity(getLocalizedMessageMap(messageKey), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PropertyKeyNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(PropertyKeyNotFoundException ex) {

        String messageKey = "exception.not.found.propertykey";
        return buildResponseEntity(getLocalizedMessageMap(messageKey), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DesignationNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(DesignationNotFoundException ex) {

        String messageKey = "exception.not.found.designation";
        return buildResponseEntity(getLocalizedMessageMap(messageKey), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(UserNotFoundException ex) {

        String messageKey = "exception.not.found.client";
        return buildResponseEntity(getLocalizedMessageMap(messageKey), HttpStatus.NOT_FOUND);
    }

    /* ----------------------- BAD REQUEST  EXCEPTIONS ---------------------- */

    @ExceptionHandler(ClientBadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(ClientBadRequestException ex) {

        String messageKey = "exception.bad.request.client";
        return buildResponseEntity(getLocalizedMessageMap(messageKey), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DesignationBadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(DesignationBadRequestException ex) {

        String messageKey = "exception.bad.request.designation";
        return buildResponseEntity(getLocalizedMessageMap(messageKey), HttpStatus.BAD_REQUEST);
    }

}
