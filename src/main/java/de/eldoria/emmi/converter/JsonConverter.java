package de.eldoria.emmi.converter;

import de.eldoria.emmi.converter.placeholder.PlaceHolder;
import de.eldoria.emmi.fragments.collections.EmmiFragmentCollection;
import de.eldoria.emmi.fragments.collections.JsonFragmentCollection;

/**
 * JsonConverter object converts a ARCA string in to an JSON string.
 */
public class JsonConverter {

    private JsonConverter(){}

    public static String convert(String message, PlaceHolder... placeHolders){
        String messageWithoutPlaceholder = applyPlaceholder(message, placeHolders);

        return new EmmiFragmentCollection(messageWithoutPlaceholder).toJsonFragmentCollection().toString();
    }

    /**
     * Applies the placeholder.
     *
     * @param message      The message where the placeholders should be applied
     * @param placeHolders One or more placeholders.
     * @return
     */
    private static String applyPlaceholder(String message, PlaceHolder... placeHolders) {
        String convertedMessage = message;

        for (PlaceHolder placeHolder : placeHolders) {
            convertedMessage = placeHolder.apply(message);
        }

        return convertedMessage;
    }
}