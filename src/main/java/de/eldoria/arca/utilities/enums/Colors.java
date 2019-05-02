package de.eldoria.arca.utilities.enums;

/**
 * enum to define the color and parse them.
 */
public enum Colors {
    /**
     * Default color. Removes Color.
     */
    DEFAULT,
    /**
     * Dark Red color.
     */
    DARK_RED,
    /**
     * Red color.
     */
    RED,
    /**
     * Gold color.
     */
    GOLD,
    /**
     * Yellow color.
     */
    YELLOW,
    /**
     * Dark green color.
     */
    DARK_GREEN,
    /**
     * Green color.
     */
    GREEN,
    /**
     * Aqua color.
     */
    AQUA,
    /**
     * Dark aqua color.
     */
    DARK_AQUA,
    /**
     * Dark blue color.
     */
    DARK_BLUE,
    /**
     * blue color.
     */
    BLUE,
    /**
     * Light Purple color.
     */
    LIGHT_PURPLE,
    /**
     * Dark purple color.
     */
    DARK_PURPLE,
    /**
     *  White color. Not the default color.
     */
    WHITE,
    /**
     * Gray color.
     */
    GRAY,
    /**
     * Dark gray color.
     */
    DARK_GRAY,
    /**
     * Black color.
     */
    BLACK;

    /**
     * Get the color as a char from a enum.
     *
     * @param color as Colors
     * @return char of the color
     */
    public static char getColorChar(Colors color) {
        switch (color) {
            case BLACK:
                return '0';
            case DARK_BLUE:
                return '1';
            case DARK_GREEN:
                return '2';
            case DARK_AQUA:
                return '3';
            case DEFAULT:
                return 'r';
            case DARK_RED:
                return '4';
            case DARK_PURPLE:
                return '5';
            case GOLD:
                return '6';
            case GRAY:
                return '7';
            case DARK_GRAY:
                return '8';
            case BLUE:
                return '9';
            case GREEN:
                return 'a';
            case AQUA:
                return 'b';
            case RED:
                return 'c';
            case LIGHT_PURPLE:
                return 'd';
            case YELLOW:
                return 'e';
            case WHITE:
                return 'f';
            default:
                throw new IllegalStateException("Unexpected value: " + color);
        }
    }

    /**
     * Get the color from a char.
     *
     * @param c char of the color
     * @return color as Colors enum
     */
    public static Colors getColor(char c) {
        switch (c) {
            case '0':
                return BLACK;
            case '1':
                return DARK_BLUE;
            case '2':
                return DARK_GREEN;
            case '3':
                return DARK_AQUA;
            case '4':
                return DARK_RED;
            case '5':
                return DARK_PURPLE;
            case '6':
                return GOLD;
            case '7':
                return GRAY;
            case '8':
                return DARK_GRAY;
            case '9':
                return BLUE;
            case 'a':
                return GREEN;
            case 'b':
                return AQUA;
            case 'c':
                return RED;
            case 'd':
                return LIGHT_PURPLE;
            case 'e':
                return YELLOW;
            case 'f':
                return WHITE;
            case 'r':
                return DEFAULT;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
    }

    /**
     * get a color from a string.
     *
     * @param markerValue string with a size of one
     * @return color as Colors enum
     */
    public static Colors getColor(String markerValue) {
        char[] chars = markerValue.toCharArray();
        if (chars.length > 0) {
            return getColor(chars[0]);
        }
        return DEFAULT;
    }

    /**
     * Get name of the color in lower case.
     *
     * @return name of the color as lower case
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
