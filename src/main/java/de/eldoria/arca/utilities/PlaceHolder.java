package de.eldoria.arca.utilities;

public class PlaceHolder {
    private String placeholder;
    private String replacement;

    /**
     * creates a mew placeholder
     * Recommended: %placeholder%.
     *
     * @param placeholder the placeholder
     * @param replacement value with which the placeholder should be replaced.
     */
    public PlaceHolder(String placeholder, String replacement) {
        this.placeholder = placeholder;
        this.replacement = replacement;
    }

    /**
     * Applies the placeholder to the string.
     *
     * @param s string on which the placeholder should be applied
     * @return the string with the placeholder replaced.
     */
    public String apply(String s) {
        return s.replace(placeholder, replacement);
    }
}
