package de.eldoria.emmi.converter.placeholder;

import java.util.HashMap;

public class Placeholder {
    private String placeholder;
    private String replacement;

    /**
     * creates a mew placeholder
     * Recommended: %placeholder%.
     *
     * @param placeholder the placeholder
     * @param replacement value with which the placeholder should be replaced.
     */
    private Placeholder(String placeholder, String replacement) {
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

    /**
     * The Creator object creates, modify and caches created placeholder.
     */
    public static class Creator {

        private HashMap<String, String> placeholderHashMap = new HashMap<>();

        private Placeholder[] placeholder;
        private boolean changed = true;

        /**
         * Creates a new placeholder.
         * @param placeholder value which should be replaced
         * @param value new value
         * @return Creator object to concat multiple creations.
         */
        public Creator create(String placeholder, String value){
            placeholderHashMap.put(placeholder,value);
            changed = true;
            return this;
        }

        /**
         * Changes the value of a placeholder, if the placeholder exists.
         * @param key placeholder which should be replaced
         * @param value new value of the placeholder
         */
        public void changePlaceholderValue(String key, String value) {
            if (placeholderHashMap.containsKey(key)) {
                placeholderHashMap.replace(key, value);
            }
            changed = true;
        }

        /**
         * Removes the placeholder from the collection.
         * @param key
         */
        public void removePlaceholder(String key){
            placeholderHashMap.remove(key);
            changed = true;
        }

        /**
         * Get an array of all existing placeholders.
         * @return array of placeholder
         */
        public Placeholder[] toArray(){
            if(changed){
                placeholder = new Placeholder[placeholderHashMap.size()];
                int i = 0;
                for (String key : placeholderHashMap.keySet()){
                    placeholder[i] = new Placeholder(key, placeholderHashMap.get(key));
                    i++;
                }
                changed = false;
            }
            return placeholder;
        }

        /**
         * Removes all cached placeholders.
         */
        public void clearPlaceholder(){
            placeholderHashMap.clear();
            placeholder = new Placeholder[0];
            changed = true;
        }
    }
}
