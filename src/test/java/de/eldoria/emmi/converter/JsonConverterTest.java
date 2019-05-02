package de.eldoria.emmi.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonConverterTest {

    @Test
    void testBoldMarker() {
        JsonConverter converter = new JsonConverter("[b]bold[/b]");
        assertEquals("{\"text\":\"bold\",\"bold\":true}", converter.toString());
    }

    @Test
    void testItalicMarker() {
        JsonConverter converter = new JsonConverter("[i]italic[/i]");
        assertEquals("{\"text\":\"italic\",\"italic\":true}", converter.toString());
    }

    @Test
    void testUnderlinedMarker() {
        JsonConverter converter = new JsonConverter("[u]underlined[/u]");
        assertEquals("{\"text\":\"underlined\",\"underlined\":true}", converter.toString());
    }

    @Test
    void testStrikethroughMarker() {
        JsonConverter converter = new JsonConverter("[s]strikethrough[/s]");
        assertEquals("{\"text\":\"strikethrough\",\"strikethrough\":true}", converter.toString());
    }

    @Test
    void testObfuscatedMarker() {
        JsonConverter converter = new JsonConverter("[k]obfuscated[/k]");
        assertEquals("{\"text\":\"obfuscated\",\"obfuscated\":true}", converter.toString());
    }

    @Test
    void testBoldItalicMarker() {
        JsonConverter converter = new JsonConverter("[b][i]bolditalic[/b][/i]");
        assertEquals("{\"text\":\"bolditalic\",\"bold\":true,\"italic\":true}", converter.toString());
    }

    @Test
    void testBoldItalicMixedMarker() {
        JsonConverter converter = new JsonConverter("[b][i]bold[/b]italic[/i]");
        assertEquals("[\"\",{\"text\":\"bold\",\"bold\":true,\"italic\":true},{\"text\":\"italic\",\"italic\":true}]", converter.toString());
    }

    @Test
    void testBoldHoverMarker() {
        JsonConverter converter = new JsonConverter("[htext={[b]bold[/b]}]hover me[/htext]");
        assertEquals("{\"text\":\"hover me\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"bold\",\"bold\":true}}}", converter.toString());
    }

    @Test
    void testClickUrlMarker(){
        JsonConverter converter = new JsonConverter("[url={eldoria.de}]text[/htext]");
        assertEquals("{\"text\":\"text\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"eldoria.de\"}}", converter.toString());
    }

    @Test
    void testClickSuggestCommandMarker(){
        JsonConverter converter = new JsonConverter("[sugcmd={/help}]test[/htext]");
        assertEquals("{\"text\":\"test\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/help\"}}", converter.toString());
    }

    @Test
    void testClickRunCommandMarker(){
        JsonConverter converter = new JsonConverter("[runcmd={/help}]test[/htext]");
        assertEquals("{\"text\":\"test\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/help\"}}", converter.toString());
    }

    @Test
    void testMixedMarker(){
        JsonConverter converter = new JsonConverter("[c=c]red [b]bold[/b][i] italic[c=2] green[/i][c=1] blue [s]strike[/s]");
        assertEquals("[\"\",{\"text\":\"red \",\"color\":\"red\"},{\"text\":\"bold\",\"bold\":true,\"color\":\"red\"},{\"text\":\" italic\",\"italic\":true,\"color\":\"red\"},{\"text\":\" green\",\"italic\":true,\"color\":\"dark_green\"},{\"text\":\" blue \",\"color\":\"dark_blue\"},{\"text\":\"strike\",\"strikethrough\":true,\"color\":\"dark_blue\"}]", converter.toString());
    }



}
