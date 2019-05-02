package de.eldoria.emmi.utilities;

import de.eldoria.emmi.fragments.collections.enums.MarkerType;

/**
 * Class to define the emmi markers.
 */
public class EmmiMarker {
    //Makes the text BOLD
    //[b]<BOLD text>[/b]
    private static final String BOLD = "b";
    //Makes the text ITALIC
    //[i]<ITALIC test>[/i]
    private static final String ITALIC = "i";
    //Underlines the text
    //[u]<UNDERLINED text>[/u]
    private static final String UNDERLINED = "u";
    //Struck out the Text
    //[s]<struck out text>[/s]
    private static final String STRIKETHROUGH = "s";
    //Obfuscates the text
    //[k]<OBFUSCATED text>[/k]
    private static final String OBFUSCATED = "k";
    //Changes the COLOR of the Text. Doesn't have a end condition
    //Alternative would be [c=<ColorLetter>] without ending condition. Is just overwritten when is should change
    private static final String COLOR = "c";
    //Creates a clickable text, which opens a url on click
    //[url="www.eldoria.de"]<displayed text>[/url]
    private static final String CLICK_OPEN_URL = "url";
    //Creates a clickable text, which executes a command or sends a text on click.
    //[runcmd="/help"]Click here for Help[/runcmd]
    //Commands without / are also possible. Ist just send as a normal chat message.
    private static final String CLICK_RUN_COMMAND = "runcmd";
    //Creates a clickable text, which writes the commwith &and or text in the chat window. Doesn't send.
    //[sugcmd=/help]Click here for Help[/sugcmd]
    private static final String CLICK_SUGGEST_COMMAND = "sugcmd";
    //Only usable in books. Creates a clickable text, which changes the current site of the book.
    //[gotopage="2"]Change to page 2[/gotopage]
    private static final String CLICK_CHANGE_PAGE = "gotopage";
    //Creates a hoverable object, which shows a hover text. The Hover Text also Supports formatting.
    // If formatting is used. The text must be in "..."
    //[htext={[b]Now you see more BOLD text owo[/b]}]Hover over this text to see more[/]
    private static final String HOVER_SHOW_TEXT = "htext";
    //Creates a new hoverable object, which shows a item on hover. The hover is a item serialised as a string
    //[hitem={id:1,Count:2,tag:{Display:{Name:I am a stone}}}]Show me a stone[/]
    private static final String HOVER_SHOW_ITEM = "hitem";
    //Creates a new hoverable object, which shows a entity on Hover. The hover is a item serialised as a string
    //[hentity={id:uuid, type:minecraft_player, name:Me}]Show me an entity[/]
    private static final String HOVER_SHOW_ENTITY = "hentity";


    /**
     * Get the marker type from a string.
     *
     * @param marker as string.
     * @return MarkerType of the string
     */
    public static MarkerType getMarkerType(String marker) {
        String markerPart = marker.split("=")[0];

        switch (markerPart) {
            case BOLD:
                return MarkerType.BOLD;
            case ITALIC:
                return MarkerType.ITALIC;
            case UNDERLINED:
                return MarkerType.UNDERLINED;
            case STRIKETHROUGH:
                return MarkerType.STRIKETHROUGH;
            case OBFUSCATED:
                return MarkerType.OBFUSCATED;
            case COLOR:
                return MarkerType.COLOR;
            case CLICK_OPEN_URL:
                return MarkerType.C_URL;
            case CLICK_RUN_COMMAND:
                return MarkerType.C_RUN_CMD;
            case CLICK_SUGGEST_COMMAND:
                return MarkerType.C_SUG_CMD;
            case CLICK_CHANGE_PAGE:
                return MarkerType.C_GO_TO_PAGE;
            case HOVER_SHOW_TEXT:
                return MarkerType.H_TEXT;
            case HOVER_SHOW_ITEM:
                return MarkerType.H_ITEM;
            case HOVER_SHOW_ENTITY:
                return MarkerType.H_ENTITY;
            default:
                return MarkerType.NONE;
        }
    }
}
