package de.eldoria.arca.fragments;

import de.eldoria.arca.fragments.collections.enums.MarkerAction;
import de.eldoria.arca.fragments.collections.enums.MarkerType;
import de.eldoria.arca.fragments.collections.enums.ArcaFragmentType;

/**
 * Class to save a arca fragment.
 */
public class ArcaFragment {
    private static final String DIVIDER = "=";

    private ArcaFragmentType fragmentType;

    private String message;

    private MarkerType markerType;
    private MarkerAction action;
    private String marker;

    /**
     * Creates a new arca fragment with a text.
     *
     * @param message the message which should be saved
     */
    public ArcaFragment(String message) {
        fragmentType = ArcaFragmentType.TEXT;
        this.message = message;
    }

    /**
     * Creates a new arca fragment with a marker.
     *
     * @param type   type of the marker
     * @param action action of the marker
     * @param marker the whole marker as a string
     */
    public ArcaFragment(MarkerType type, MarkerAction action, String marker) {
        fragmentType = ArcaFragmentType.MARKER;
        this.markerType = type;
        this.action = action;
        this.marker = marker;
    }

    /**
     * Get the fragment type. {@link de.eldoria.arca.fragments.collections.enums.ArcaFragmentType}
     *
     * @return fragment type of the arca fragment
     */
    public ArcaFragmentType getFragmentType() {
        return fragmentType;
    }

    /**
     * Get the message of the acra fragment.
     * Only valid if the fragment type is a text.
     *
     * @return a message string
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the marker type {@link de.eldoria.arca.fragments.collections.enums.MarkerType}
     * Only valid it the fragment type is a marker.
     *
     * @return MarkerType of the fragment
     */
    public MarkerType getMarkerType() {
        return markerType;
    }

    /**
     * Get the action of the marker
     * Only valid if the fragment is a marker.
     *
     * @return MarkerAction of the fragment
     */
    public MarkerAction getAction() {
        return action;
    }

    /**
     * Get the current action as a bool.
     *
     * @return true if the marker opens a section
     */
    public boolean getActionBool() {
        return action == MarkerAction.OPEN;
    }

    /**
     * Get the current marker string.
     *
     * @return marker as string
     */
    public String getMarker() {
        return marker;
    }

    /**
     * Get the marker value.
     *
     * @return marker value as string
     */
    public String getMarkerValue() {
        String[] parts = marker.split(DIVIDER,2);
        if (parts.length == 2) {
            return parts[1];
        }
        return "";
    }
}

