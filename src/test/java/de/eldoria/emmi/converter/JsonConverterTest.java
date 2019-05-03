package de.eldoria.emmi.converter;

import de.eldoria.emmi.converter.placeholder.Placeholder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class JsonConverterTest {

    @Test
    void testPlaceholder(){
        String myEmmiMessage = "My name is %player% and I am playing on %servername%.";

        Placeholder.Creator c = new Placeholder.Creator();
        c.create("%player%", "Hadde").create("%servername%", "eldoria").toArray();

        String myTellrawMessage = JsonConverter.convert(myEmmiMessage, c.toArray());

        assertEquals("{\"text\":\"My name is Hadde and I am playing on eldoria.\"}", myTellrawMessage);
    }

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

    @Test
    void testMixedMarker2(){
        String converted = JsonConverter.convert("Small [b]bold and [i]italic and [c=4]colored test[c=r][/b][/i] and maybe a small [htext={[b]bold and colored [c=1]hover[c=r] text[/b]}]hover test[/htext]");
        assertEquals("[\"\",{\"text\":\"Small \"},{\"text\":\"bold and \",\"bold\":true},{\"text\":\"italic and \",\"bold\":true,\"italic\":true},{\"text\":\"colored test\",\"bold\":true,\"italic\":true,\"color\":\"dark_red\"},{\"text\":\" and maybe a small \"},{\"text\":\"hover test\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"bold and colored \",\"bold\":true},{\"text\":\"hover\",\"bold\":true,\"color\":\"dark_blue\"},{\"text\":\" text\",\"bold\":true}]}}]",converted);
    }
}
