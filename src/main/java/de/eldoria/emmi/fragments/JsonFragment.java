package de.eldoria.emmi.fragments;

import de.eldoria.emmi.fragments.events.ClickActionFragment;
import de.eldoria.emmi.fragments.events.HoverActionFragment;
import de.eldoria.emmi.utilities.JsonFragmentConverter;
import de.eldoria.emmi.utilities.enums.Colors;

/**
 * Class to save JSON fragment.
 */
public final class JsonFragment {

    private String text;

    private boolean bold;
    private boolean italic;
    private boolean underlined;
    private boolean strikethrough;
    private boolean obfuscated;
    private Colors color;
    private ClickActionFragment clickActionFragment;
    private HoverActionFragment hoverActionFragment;

    /**
     * Creates a new JSON fragment with values.
     *
     * @param text string of the message
     */
    private JsonFragment(String text) {
        this.text = text;
    }

    /**
     * Serializes the fragment to a json string.
     *
     * @return a json fragment as string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(JsonFragmentConverter.getText(text));

        if (bold) {
            builder.append(",").append(JsonFragmentConverter.getBold(bold));
        }
        if (italic) {
            builder.append(",").append(JsonFragmentConverter.getItalic(italic));
        }
        if (underlined) {
            builder.append(",").append(JsonFragmentConverter.getUnderlined(underlined));
        }
        if (strikethrough) {
            builder.append(",").append(JsonFragmentConverter.getStrikethrough(strikethrough));
        }
        if (obfuscated) {
            builder.append(",").append(JsonFragmentConverter.getObfuscated(obfuscated));
        }
        if (!clickActionFragment.isEmpty()) {
            builder.append(",").append(JsonFragmentConverter.getClick(clickActionFragment));
        }
        if (!hoverActionFragment.isEmpty()) {
            builder.append(",").append(JsonFragmentConverter.getHover(hoverActionFragment));
        }

        if (color != Colors.DEFAULT) {
            builder.append(",").append(JsonFragmentConverter.getColor(color));
        }

        builder.append("}");
        return builder.toString();
    }

    public static class Builder {
        private String text;
        private boolean bold = false;
        private boolean italic = false;
        private boolean underlined = false;
        private boolean strikethrough = false;
        private boolean obfuscated = false;
        private Colors color = Colors.DEFAULT;
        private ClickActionFragment clickActionFragment = ClickActionFragment.getEmpty();
        private HoverActionFragment hoverActionFragment = HoverActionFragment.getEmpty();

        /**
         * Creates a new JsonFragment builder.
         *
         * @param text of the JsonFragment
         */
        public Builder(String text) {
            this.text = text;
        }

        /**
         * Makes the text bold.
         *
         * @return the builder object
         */
        public Builder makeBold() {
            this.bold = true;
            return this;
        }

        /**
         * Sets if the text ist bold or not.
         *
         * @param state true when the modifier should be applied.
         * @return the builder object
         */
        public Builder isBold(boolean state) {
            this.bold = state;
            return this;
        }

        /**
         * Makes the text italic.
         *
         * @return the builder object
         */
        public Builder makeItalic() {
            this.italic = true;
            return this;
        }

        /**
         * Sets if the text ist italic or not.
         *
         * @param state true when the modifier should be applied.
         * @return the builder object
         */
        public Builder isItalic(boolean state) {
            this.italic = state;
            return this;
        }

        /**
         * Makes the text underlined.
         *
         * @return the builder object
         */
        public Builder makeUnderlined() {
            this.underlined = true;
            return this;
        }

        /**
         * Sets if the text ist underlined or not.
         *
         * @param state true when the modifier should be applied.
         * @return the builder object
         */
        public Builder isUnderlined(boolean state) {
            this.underlined = state;
            return this;
        }

        /**
         * Makes the text struck out.
         *
         * @return the builder object
         */
        public Builder makeStruckOut() {
            this.strikethrough = true;
            return this;
        }

        /**
         * Sets if the text ist struck out or not.
         *
         * @param state true when the modifier should be applied.
         * @return the builder object
         */
        public Builder isStruckOut(boolean state) {
            this.strikethrough = state;
            return this;
        }

        /**
         * Makes the text obfuscated.
         *
         * @return the builder object
         */
        public Builder makeObfuscated() {
            this.obfuscated = true;
            return this;
        }

        /**
         * Sets if the text ist obfuscated or not.
         *
         * @param state true when the modifier should be applied.
         * @return the builder object
         */
        public Builder isObfuscated(boolean state) {
            this.obfuscated = state;
            return this;
        }

        /**
         * Makes the text colored.
         *
         * @param color of the text. Default if no color should be set
         * @return the builder object
         */
        public Builder withColor(Colors color) {
            this.color = color;
            return this;
        }

        /**
         * Gives the Text a click Action.
         *
         * @param fragment which defines the action
         * @return the builder object
         */
        public Builder withClickAction(ClickActionFragment fragment) {
            clickActionFragment = fragment;
            return this;
        }

        /**
         * Gives the Text a hover action.
         *
         * @param fragment which defines the action
         * @return the builder object
         */
        public Builder withHoverAction(HoverActionFragment fragment) {
            hoverActionFragment = fragment;
            return this;
        }

        /**
         * Builds the JsonFragment.
         *
         * @return a new JsonFragment object.
         */
        public JsonFragment build() {
            JsonFragment fragment = new JsonFragment(text);
            fragment.bold = bold;
            fragment.italic = italic;
            fragment.underlined = underlined;
            fragment.strikethrough = strikethrough;
            fragment.obfuscated = obfuscated;
            fragment.color = color;
            fragment.clickActionFragment = clickActionFragment;
            fragment.hoverActionFragment = hoverActionFragment;

            return fragment;
        }

    }
}
