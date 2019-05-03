package de.eldoria.emmi.utilities;

import de.eldoria.emmi.fragments.events.ClickActionFragment;
import de.eldoria.emmi.fragments.events.HoverActionFragment;
import de.eldoria.emmi.utilities.enums.Colors;
import de.eldoria.emmi.utilities.enums.HoverActionType;

public class JsonFragmentConverter {
    /**
     * Get the text as JSON part.
     *
     * @param text which should be converted to json
     * @return parameter as json
     */
    public static String getText(String text) {
        return String.format("\"text\":\"%s\"", text);
    }

    /**
     * Get the bold parameter as JSON part.
     *
     * @param state which should be converted to json
     * @return parameter as json
     */
    public static String getBold(boolean state) {
        return String.format("\"bold\":%s", state);
    }

    /**
     * Get the italic parameter as JSON part.
     *
     * @param state which should be converted to json
     * @return parameter as json
     */
    public static String getItalic(boolean state) {
        return String.format("\"italic\":%s", state);
    }

    /**
     * Get the underlined parameter as JSON part.
     *
     * @param state which should be converted to json
     * @return parameter as json
     */
    public static String getUnderlined(boolean state) {
        return String.format("\"underlined\":%s", state);
    }

    /**
     * Get the strikethrough parameter as JSON part.
     *
     * @param state which should be converted to json
     * @return parameter as json
     */
    public static String getStrikethrough(boolean state) {
        return String.format("\"strikethrough\":%s", state);
    }


    /**
     * Get the obfuscated parameter as JSON part.
     *
     * @param state which should be converted to json
     * @return parameter as json
     */
    public static String getObfuscated(boolean state) {
        return String.format("\"obfuscated\":%s", state);
    }

    /**
     * Get the color parameter as JSON part from a color enum.
     *
     * @param color which should be converted to json
     * @return parameter as json
     */
    public static String getColor(Colors color) {
        return String.format("\"color\":\"%s\"", color.toString().toLowerCase());
    }

    /**
     * Get the color parameter as JSON part from a color char.
     *
     * @param colorCode which should be converted to json
     * @return parameter as json
     */
    public static String getColor(char colorCode) {
        return String.format("\"color\":\"%s\"", Colors.getColor(colorCode).toString().toLowerCase());
    }

    /**
     * Get the click parameter as JSON part.
     *
     * @param eventFragment which should be converted to json
     * @return parameter as json
     */
    public static String getClick(ClickActionFragment eventFragment) {
        String eventName = eventFragment.getAction().toString().toLowerCase();
        return String.format("\"clickEvent\":{\"action\":\"%s\",\"value\":\"%s\"}",
                eventName, eventFragment.getValue());
    }

    /**
     * Get the hover parameter as JSON part.
     *
     * @param eventFragment which should be converted to json
     * @return parameter as json
     */
    public static String getHover(HoverActionFragment eventFragment) {
        String eventName = eventFragment.getAction().toString().toLowerCase();
        if(eventFragment.getAction() == HoverActionType.SHOW_ITEM){
            return String.format("\"hoverEvent\":{\"action\":\"%s\",\"value\":\"%s\"}",
                    eventName, eventFragment.getValue())
        }
        return String.format("\"hoverEvent\":{\"action\":\"%s\",\"value\":%s}",
                eventName, eventFragment.getValue());
    }
}
