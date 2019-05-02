package de.eldoria.emmi.fragments.events;

import de.eldoria.emmi.converter.JsonConverter;
import de.eldoria.emmi.utilities.enums.HoverActionType;

/**
 * Class to save a hover action.
 */
public class HoverActionFragment {
    private static HoverActionFragment empty;
    private HoverActionType action;
    private String value;
    private boolean isEmpty;

    /**
     * Create a new hover action with meta data.
     *
     * @param action type of the hover action
     * @param value  value ot the hover action, which should be showed on hover
     */
    public HoverActionFragment(HoverActionType action, String value) {
        this.action = action;
        if (action == HoverActionType.SHOW_TEXT) {
            this.value = new JsonConverter(value).toString();
        } else {
            this.value = "{" + value + "}";
        }
        isEmpty = false;
    }

    /**
     * Creates a new empty hover action.
     */
    private HoverActionFragment() {
        isEmpty = true;
    }

    /**
     * Get a empty hover action.
     *
     * @return a empty hover action singleton.
     */
    public static HoverActionFragment getEmpty() {
        if (empty == null) {
            empty = new HoverActionFragment();
        }
        return empty;
    }

    /**
     * Checks if a action is empty.
     *
     * @return true when the action is empty
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Get the hover action type.
     *
     * @return action of the fragment
     */
    public HoverActionType getAction() {
        return action;
    }

    /**
     * Return the hover action value.
     *
     * @return string with the action value
     */
    public String getValue() {
        return value;
    }
}
