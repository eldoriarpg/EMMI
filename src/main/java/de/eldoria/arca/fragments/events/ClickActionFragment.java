package de.eldoria.arca.fragments.events;

import de.eldoria.arca.utilities.enums.ClickActionType;

/**
 * Class to safe a click action.
 */
public class ClickActionFragment {
    private static ClickActionFragment empty;
    private ClickActionType action;
    private String value;
    private boolean isEmpty;

    /**
     * Creates a new click action with meta data.
     *
     * @param action type of the click action
     * @param value  value ot the click action, which should be executed on click
     */
    public ClickActionFragment(ClickActionType action, String value) {
        this.action = action;
        this.value = value;
        isEmpty = false;
    }

    /**
     * Creates a new empty click action.
     */
    private ClickActionFragment() {
        isEmpty = true;
    }

    /**
     * .
     * Get a empty click action
     *
     * @return a empty click action singleton.
     */
    public static ClickActionFragment getEmpty() {
        if (empty == null) {
            empty = new ClickActionFragment();
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
     * Get the click action type.
     *
     * @return action of the fragment
     */
    public ClickActionType getAction() {
        return action;
    }

    /**
     * Return the click action value.
     *
     * @return string with the action value
     */
    public String getValue() {
        return value;
    }
}
