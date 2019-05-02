package de.eldoria.emmi.fragments.collections;

import de.eldoria.emmi.fragments.EmmiFragment;
import de.eldoria.emmi.fragments.collections.enums.MarkerAction;
import de.eldoria.emmi.fragments.collections.enums.MarkerType;
import de.eldoria.emmi.utilities.EmmiMarker;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Collection which cuts the emmi string in to emmi fragments.
 * Can be directly converted in a json fragment collection.
 */
public class EmmiFragmentCollection {
    private static final char LOCK_MARKER_OPEN = '{';
    private static final char LOCK_MARKER_CLOSE = '}';

    private static final char MARKDOWN_MARKER_OPEN = '[';
    private static final char MARKDOWN_MARKER_CLOSE = ']';
    private static final char MARKDOWN_MARKER_COMPLEX = '=';

    private Queue<EmmiFragment> messageFragments = new LinkedList<EmmiFragment>();
    private String emmiString;

    /**
     * Creates a new emmi fragment collection. Can be converted to json fragment collection
     *
     * @param emmiString Emmi string from which the collection should be created.
     */
    public EmmiFragmentCollection(String emmiString) {
        this.emmiString = emmiString;

        StringBuilder builder = new StringBuilder();

        MarkerAction action = MarkerAction.UNDEFINED;

        boolean markerState = false;

        boolean innerLocked = false;
        int lockedLevel = 0;

        for (int i = 0; i < emmiString.length(); i++) {
            char current = emmiString.charAt(i);

            if (innerLocked) {
                if (current == LOCK_MARKER_OPEN) {
                    lockedLevel++;
                }
                if (current == LOCK_MARKER_CLOSE) {
                    lockedLevel--;
                }

                if (lockedLevel == 0) {
                    innerLocked = false;
                    continue;
                }

                builder.append(current);
                continue;
            }


            //EmmiMarker open. Check if it's a close or open marker
            if (current == MARKDOWN_MARKER_OPEN) {
                if (builder.length() != 0) {
                    messageFragments.add(new EmmiFragment(builder.toString()));
                }
                builder.setLength(0);

                markerState = true;
                if (emmiString.charAt(i + 1) == '/') {
                    action = MarkerAction.CLOSE;
                    i++;
                    continue;
                }
                action = MarkerAction.OPEN;
                continue;
            }

            if (markerState && current == MARKDOWN_MARKER_COMPLEX && emmiString.charAt(i + 1) == LOCK_MARKER_OPEN) {
                builder.append(current);
                innerLocked = true;
                i++;
                lockedLevel++;
                continue;
            }


            //EmmiMarker close. Cache in Queue
            if (markerState && current == MARKDOWN_MARKER_CLOSE) {
                markerState = false;
                MarkerType type = EmmiMarker.getMarkerType(builder.toString());
                messageFragments.add(new EmmiFragment(type, action, builder.toString()));
                builder.setLength(0);
                continue;
            }


            builder.append(current);
        }

        if (!builder.toString().isEmpty()) {
            messageFragments.add(new EmmiFragment(builder.toString()));
        }
    }

    /**
     * Get all emmi fragments in a queue.
     *
     * @return acra fragment Queue
     */
    public Queue<EmmiFragment> getMessageFragments() {
        return messageFragments;
    }

    /**
     * Converts the emmi fragment collection to a json fragment collection.
     *
     * @return the emmi fragments collection as a converted json fragment collection
     */
    public JsonFragmentCollection toJsonFragmentCollection() {
        return new JsonFragmentCollection(this);
    }
}
