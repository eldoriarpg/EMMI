package de.eldoria.emmi.converter;

import de.eldoria.emmi.converter.placeholder.Placeholder;
import de.eldoria.emmi.fragments.collections.EmmiFragmentCollection;

/**
 * JsonConverter object converts a ARCA string in to an JSON string.
 */
public class JsonConverter {

    private JsonConverter(){}

    public static String convert(String message, Placeholder... placeHolders){
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
    private static String applyPlaceholder(String message, Placeholder... placeHolders) {
        String convertedMessage = message;

        for (Placeholder placeHolder : placeHolders) {
            convertedMessage = placeHolder.apply(convertedMessage);
        }

        return convertedMessage;
    }
}