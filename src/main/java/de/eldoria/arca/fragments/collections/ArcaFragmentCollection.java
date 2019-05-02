package de.eldoria.arca.fragments.collections;

import de.eldoria.arca.fragments.ArcaFragment;
import de.eldoria.arca.fragments.collections.enums.MarkerAction;
import de.eldoria.arca.fragments.collections.enums.MarkerType;
import de.eldoria.arca.utilities.ArcaMarker;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Collection which cuts the arca string in to arca fragments.
 * Can be directly converted in a json fragment collection.
 */
public class ArcaFragmentCollection {
    private static final char LOCK_MARKER_OPEN = '{';
    private static final char LOCK_MARKER_CLOSE = '}';

    private static final char MARKDOWN_MARKER_OPEN = '[';
    private static final char MARKDOWN_MARKER_CLOSE = ']';
    private static final char MARKDOWN_MARKER_COMPLEX = '=';

    private Queue<ArcaFragment> messageFragments = new LinkedList<ArcaFragment>();
    private String arcaString;

    /**
     * Creates a new arca fragment collection. Can be converted to json fragment collection
     *
     * @param arcaString Arca string from which the collection should be created.
     */
    public ArcaFragmentCollection(String arcaString) {
        this.arcaString = arcaString;

        StringBuilder builder = new StringBuilder();

        MarkerAction action = MarkerAction.UNDEFINED;

        boolean markerState = false;

        boolean innerLocked = false;
        int lockedLevel = 0;

        for (int i = 0; i < arcaString.length(); i++) {
            char current = arcaString.charAt(i);

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


            //ArcaMarker open. Check if it's a close or open marker
            if (current == MARKDOWN_MARKER_OPEN) {
                if (builder.length() != 0) {
                    messageFragments.add(new ArcaFragment(builder.toString()));
                }
                builder.setLength(0);

                markerState = true;
                if (arcaString.charAt(i + 1) == '/') {
                    action = MarkerAction.CLOSE;
                    i++;
                    continue;
                }
                action = MarkerAction.OPEN;
                continue;
            }

            if (markerState && current == MARKDOWN_MARKER_COMPLEX && arcaString.charAt(i + 1) == LOCK_MARKER_OPEN) {
                builder.append(current);
                innerLocked = true;
                i++;
                lockedLevel++;
                continue;
            }


            //ArcaMarker close. Cache in Queue
            if (markerState && current == MARKDOWN_MARKER_CLOSE) {
                markerState = false;
                MarkerType type = ArcaMarker.getMarkerType(builder.toString());
                messageFragments.add(new ArcaFragment(type, action, builder.toString()));
                builder.setLength(0);
                continue;
            }


            builder.append(current);
        }

        if (!builder.toString().isEmpty()) {
            messageFragments.add(new ArcaFragment(builder.toString()));
        }
    }

    /**
     * Get all arca fragments in a queue.
     *
     * @return acra fragment Queue
     */
    public Queue<ArcaFragment> getMessageFragments() {
        return messageFragments;
    }

    /**
     * Converts the arca fragment collection to a json fragment collection.
     *
     * @return the arca fragments collection as a converted json fragment collection
     */
    public JsonFragmentCollection toJsonFragmentCollection() {
        return new JsonFragmentCollection(this);
    }
}
