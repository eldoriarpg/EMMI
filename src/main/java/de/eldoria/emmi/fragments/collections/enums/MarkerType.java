package de.eldoria.emmi.fragments.collections.enums;

/**
 * Defines the type of a marker.
 */
public enum MarkerType {
    /**
     * Marker type none. Should not used normaly.
     */
    NONE,
    /**
     * Marker type for bold text.
     */
    BOLD,
    /**
     * Marker type for italic text.
     */
    ITALIC,
    /**
     * Marker type for underlined text.
     */
    UNDERLINED,
    /**
     * Marker type for struck out text.
     */
    STRIKETHROUGH,
    /**
     * Marker type for obfuscated text.
     */
    OBFUSCATED,
    /**
     * Marker type for colored text.
     */
    COLOR,
    /**
     * Marker type for clickable url.
     */
    C_URL,
    /**
     * Marker type for clickable command text.
     */
    C_RUN_CMD,
    /**
     * Marker type for clickable suggestion text.
     */
    C_SUG_CMD,
    /**
     * Marker type for clickable page changing text.
     */
    C_GO_TO_PAGE,
    /**
     * Marker type for hoverable text with text display.
     */
    H_TEXT,
    /**
     * Marker type for hoverable text with item display.
     */
    H_ITEM,
    /**
     * Marker type for hoverable text with entity display.
     */
    H_ENTITY
}