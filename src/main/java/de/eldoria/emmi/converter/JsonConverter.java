package de.eldoria.emmi.converter;

import de.eldoria.emmi.converter.placeholder.PlaceHolder;
import de.eldoria.emmi.fragments.collections.EmmiFragmentCollection;
import de.eldoria.emmi.fragments.collections.JsonFragmentCollection;

/**
 * JsonConverter object converts a ARCA string in to an JSON string.
 */
public class JsonConverter {

    private JsonFragmentCollection fragmentCollection;

    /**
     * Generates a new JsonConverter object, which directly converts the message in to a JSON string.
     * The JSON string can be directly received via the toString() method.
     *
     * @param message      The message which should be formatted to JSON
     * @param placeHolders One or more placeholders.
     * @see PlaceHolder
     */
    public JsonConverter(String message, PlaceHolder... placeHolders) {
        String messageWithoutPlaceholder = applyPlaceholder(message, placeHolders);

        fragmentCollection = new EmmiFragmentCollection(messageWithoutPlaceholder).toJsonFragmentCollection();

    }

    /**
     * To string method to receive the formatted string.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return fragmentCollection.toString();
    }

    /**
     * Applies the placeholder.
     *
     * @param message      The message where the placeholders should be applied
     * @param placeHolders One or more placeholders.
     * @return
     */
    private String applyPlaceholder(String message, PlaceHolder... placeHolders) {
        String convertedMessage = message;

        for (PlaceHolder placeHolder : placeHolders) {
            convertedMessage = placeHolder.apply(message);
        }

        return convertedMessage;
    }
}