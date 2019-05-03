package de.eldoria.emmi.converter;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class JsonConverterTest {

    @Test
    void testBoldMarker() {
        String converted = JsonConverter.convert("[b]bold[/b]");
        assertEquals("{\"text\":\"bold\",\"bold\":true}", converted);
    }

    @Test
    void testItalicMarker() {
        String converter = JsonConverter.convert("[i]italic[/i]");
        assertEquals("{\"text\":\"italic\",\"italic\":true}", converter);
    }

    @Test
    void testUnderlinedMarker() {
        String converter = JsonConverter.convert("[u]underlined[/u]");
        assertEquals("{\"text\":\"underlined\",\"underlined\":true}", converter);
    }

    @Test
    void testStrikethroughMarker() {
        String converter = JsonConverter.convert("[s]strikethrough[/s]");
        assertEquals("{\"text\":\"strikethrough\",\"strikethrough\":true}", converter);
    }

    @Test
    void testObfuscatedMarker() {
        String converter = JsonConverter.convert("[o]obfuscated[/o]");
        assertEquals("{\"text\":\"obfuscated\",\"obfuscated\":true}", converter);
    }

    @Test
    void testBoldItalicMarker() {
        String converter = JsonConverter.convert("[b][i]bolditalic[/b][/i]");
        assertEquals("{\"text\":\"bolditalic\",\"bold\":true,\"italic\":true}", converter);
    }

    @Test
    void testBoldItalicMixedMarker() {
        String converter = JsonConverter.convert("[b][i]bold[/b]italic[/i]");
        assertEquals("[\"\",{\"text\":\"bold\",\"bold\":true,\"italic\":true},{\"text\":\"italic\",\"italic\":true}]", converter);
    }

    @Test
    void testBoldHoverMarker() {
        String converter = JsonConverter.convert("[htext={[b]bold[/b]}]hover me[/htext]");
        assertEquals("{\"text\":\"hover me\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"bold\",\"bold\":true}}}", converter);
    }

    @Test
    void testClickUrlMarker(){
        String converter = JsonConverter.convert("[url={eldoria.de}]text[/htext]");
        assertEquals("{\"text\":\"text\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"eldoria.de\"}}", converter);
    }

    @Test
    void testClickSuggestCommandMarker(){
        String converter = JsonConverter.convert("[sugcmd={/help}]test[/htext]");
        assertEquals("{\"text\":\"test\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/help\"}}", converter);
    }

    @Test
    void testClickRunCommandMarker(){
        String converter = JsonConverter.convert("[runcmd={/help}]test[/htext]");
        assertEquals("{\"text\":\"test\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/help\"}}", converter);
    }

    @Test
    void testMixedMarker(){
        String converted = JsonConverter.convert("[c=c]red [b]bold[/b][i] italic[c=2] green[/i][c=1] blue [s]strike[/s]");
        assertEquals("[\"\",{\"text\":\"red \",\"color\":\"red\"},{\"text\":\"bold\",\"bold\":true,\"color\":\"red\"},{\"text\":\" italic\",\"italic\":true,\"color\":\"red\"},{\"text\":\" green\",\"italic\":true,\"color\":\"dark_green\"},{\"text\":\" blue \",\"color\":\"dark_blue\"},{\"text\":\"strike\",\"strikethrough\":true,\"color\":\"dark_blue\"}]", converted);
    }
}
