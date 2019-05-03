package de.eldoria.emmi.fragments.collections;

import de.eldoria.emmi.fragments.EmmiFragment;
import de.eldoria.emmi.fragments.JsonFragment;
import de.eldoria.emmi.fragments.collections.enums.MarkerType;
import de.eldoria.emmi.fragments.collections.enums.EmmiFragmentType;
import de.eldoria.emmi.fragments.events.ClickActionFragment;
import de.eldoria.emmi.fragments.events.HoverActionFragment;
import de.eldoria.emmi.utilities.enums.ClickActionType;
import de.eldoria.emmi.utilities.enums.Colors;
import de.eldoria.emmi.utilities.enums.HoverActionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Class to generate a JSON fragment collection, which can be serialized to a json string.
 * Can be serialized to a JSON string with the to string method.
 * Needs a {@link de.eldoria.emmi.fragments.collections.EmmiFragmentCollection} to be generated.
 */
public class JsonFragmentCollection {
    private static final String ARRAY_OPEN = "[\"\",";
    private static final char ARRAY_CLOSE = ']';
    private static final char ARRAY_DIVIDER = ',';

    private List<JsonFragment> jsonFragments = new ArrayList<>();

    /**
     * Creates a json fragment collection from a emmi fragment collection.
     *
     * @param collection Emmi fragment collection to build the json fragments
     */
    JsonFragmentCollection(EmmiFragmentCollection collection) {
        build(collection.getMessageFragments());
    }

    /**
     * Builds an array list of json fragments.
     *
     * @param messageFragments Queue of emmi fragments
     */
    private void build(Queue<EmmiFragment> messageFragments) {
        boolean bold = false;
        boolean italic = false;
        boolean underlined = false;
        boolean strikethrought = false;
        boolean obfuscated = false;
        Colors color = Colors.DEFAULT;
        ClickActionFragment clickEvent = ClickActionFragment.getEmpty();
        HoverActionFragment hoverEvent = HoverActionFragment.getEmpty();

        for (EmmiFragment frag : messageFragments) {
            if (frag.getFragmentType() == EmmiFragmentType.TEXT) {

                jsonFragments.add(new JsonFragment.Builder(frag.getMessage()).isBold(bold).isItalic(italic)
                        .isUnderlined(underlined).isStruckOut(strikethrought).isObfuscated(obfuscated)
                        .withColor(color).withClickAction(clickEvent).withHoverAction(hoverEvent).build());

            } else if (frag.getFragmentType() == EmmiFragmentType.MARKER) {
                MarkerType type = frag.getMarkerType();
                switch (type) {

                    case NONE:
                        break;
                    case BOLD:
                        bold = frag.getActionBool();
                        break;
                    case ITALIC:
                        italic = frag.getActionBool();
                        break;
                    case UNDERLINED:
                        underlined = frag.getActionBool();
                        break;
                    case STRIKETHROUGH:
                        strikethrought = frag.getActionBool();
                        break;
                    case OBFUSCATED:
                        obfuscated = frag.getActionBool();
                        break;
                    case COLOR:
                        color = Colors.getColor(frag.getMarkerValue());
                        break;
                    case C_URL:
                        if (frag.getActionBool()) {
                            clickEvent = new ClickActionFragment(ClickActionType.OPEN_URL,
                                    frag.getMarkerValue());
                        } else {
                            clickEvent = ClickActionFragment.getEmpty();
                        }
                        break;
                    case C_RUN_CMD:
                        if (frag.getActionBool()) {

                            clickEvent = new ClickActionFragment(ClickActionType.RUN_COMMAND,
                                    frag.getMarkerValue());
                        } else {
                            clickEvent = ClickActionFragment.getEmpty();
                        }
                        break;
                    case C_SUG_CMD:
                        if (frag.getActionBool()) {
                            clickEvent = new ClickActionFragment(ClickActionType.SUGGEST_COMMAND,
                                    frag.getMarkerValue());
                        } else {
                            clickEvent = ClickActionFragment.getEmpty();
                        }
                        break;
                    case C_GO_TO_PAGE:
                        if (frag.getActionBool()) {
                            clickEvent = new ClickActionFragment(ClickActionType.CHANGE_PAGE,
                                    frag.getMarkerValue());
                        } else {
                            clickEvent = ClickActionFragment.getEmpty();
                        }
                        break;
                    case H_TEXT:
                        if (frag.getActionBool()) {
                            hoverEvent = new HoverActionFragment(HoverActionType.SHOW_TEXT,
                                    frag.getMarkerValue());
                        } else {
                            hoverEvent = HoverActionFragment.getEmpty();
                        }
                        break;
                    case H_ITEM:
                        if (frag.getActionBool()) {
                            hoverEvent = new HoverActionFragment(HoverActionType.SHOW_ITEM,
                                    frag.getMarkerValue());
                        } else {
                            hoverEvent = HoverActionFragment.getEmpty();
                        }
                        break;
                    case H_ENTITY:
                        if (frag.getActionBool()) {
                            hoverEvent = new HoverActionFragment(HoverActionType.SHOW_ENTITY,
                                    frag.getMarkerValue());
                        } else {
                            hoverEvent = HoverActionFragment.getEmpty();
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + type);
                }
            }
        }
    }

    /**
     * Serialize the json fragments in to an json string.
     *
     * @return a serialized json string. ready for use.
     */
    @Override
    public String toString() {
        boolean array = jsonFragments.size() > 1;

        StringBuilder builder = new StringBuilder();

        if (array) {
            builder.append(ARRAY_OPEN);
        }
        for (int i = 0; i < jsonFragments.size(); i++) {
            if (i == 0) {
                builder.append(jsonFragments.get(i).toString());
                continue;
            }

            builder.append(ARRAY_DIVIDER).append(jsonFragments.get(i).toString());
        }
        if (array) {
            builder.append(ARRAY_CLOSE);
        }
        return builder.toString();

    }
}
