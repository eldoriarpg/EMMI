package de.eldoria.emmi.utilities;

import java.util.HashMap;

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
    private PlaceHolder(String placeholder, String replacement) {
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


    public static class Creator {

        private HashMap<String, String> placeholderHashMap = new HashMap<>();

        private PlaceHolder[] placeholder;
        private boolean changed = true;

        public PlaceHolder create(String placeholder, String value){
            PlaceHolder newPlaceholder = new PlaceHolder(placeholder, value);
            placeholderHashMap.put(placeholder,value);
            changed = true;
            return newPlaceholder;
        }

        public void changePlaceholderValue(String key, String value) {
            if (placeholderHashMap.containsKey(key)) {
                placeholderHashMap.replace(key, value);
            }
            changed = true;
        }

        public void removePlaceholder(String key){
            placeholderHashMap.remove(key);
            changed = true;
        }

        public PlaceHolder[] getPlaceholder(){
            if(changed){
                placeholder = new PlaceHolder[placeholderHashMap.size()];
                int i = 0;
                for (String key : placeholderHashMap.keySet()){
                    placeholder[i] = new PlaceHolder(key, placeholderHashMap.get(key));
                    i++;
                }
                changed = false;
            }
            return placeholder;
        }

        public void clearPlaceholder(){
            placeholderHashMap.clear();
            placeholder = new PlaceHolder[0];
            changed = true;
        }
    }
}
