# EMMI - Eldoria Message Markup Interpreter for Minecraft
EMMI is a easy way to generate complex JSON strings for the Minecraft chat with a easy marker system. It even supports Placeholder if u want.

## Overview
* [Why should I use EMMI?](https://github.com/eldoriarpg/EMMI/tree/master#why-should-i-use-emmi)
  * [Example](https://github.com/eldoriarpg/EMMI/tree/master#example)
* [Implementation](https://github.com/eldoriarpg/EMMI/tree/master#implementation)
  * [Basic Implementation](https://github.com/eldoriarpg/EMMI/tree/master#basic-implementation)
  * [Implementation with Placeholdern](https://github.com/eldoriarpg/EMMI/tree/master#implementation-with-placeholdern)
* [Marker](https://github.com/eldoriarpg/EMMI/tree/master#marker)

## Why should I use EMMI?
EMMI is a easy way to generate complex JSON strings using config text. It's easy to read, customizable and saves a lot of time.

EMMI is designed to be as intuitive as possible without loosing the power of JSON.

It mostly used a markup system similar to BB codes combines with known minecraft formatting techniques.

A bold text is pretty easy just `[b]bold[/b]`. Here is a small change we made compared to minecraft styles. We changed the letters of the marker to more intuitive letters.

Or the hover text, which is kinda cringy to write in json. With EMMI it's just `[htext={I am a hover text}]hover me[/htext]`. Easy right?

So EMMI is a good way to write long complex JSON strings even shorter and more readable. Take a look at our example.

### Example
Let's say we want to write this text in a config:\
![alt text](https://cdn.discordapp.com/attachments/484383037178642442/573273908849934368/unknown.png "")

In JSON notation it would be a long string, which isn't really readable without routine.\
**JSON Notation**:\
`[{"text":"red ","color":"red"},{"text":"bold","bold":true,"color":"red"},{"text":" italic","italic":true,"color":"red"},{"text":" green","italic":true,"color":"dark_green"},{"text":" blue ","color":"dark_blue"},{"text":"strike","strikethrough":true,"color":"dark_blue"}]`

With EMMI notation its way shorter and more readable:\
**EMMI Notation**:\
`[c=c]red [b]bold[/b][i] italic[c=2] green[/i][c=1] blue [s]strike[/s]`\
Way better right?

#### But why should I use EMMI? I can even make this with the normal formatting codes?
I knew that this question would comes up.\
EMMI doesn't support only the common formatting codes of Minecraft. It also supports hover and clickable text and this is a big plus.\
Let's say you want to let a player execute a command via click on a text. But you want to write this in your config. How do you make this?\
With EMMI it's pretty easy:\
`You need help? Click [runcmd={/help}]here[/runcmd]`\
Did i told ya right? Just implement the EMMI to JSON converter in your plugin and send the received message via tellraw to the player. That's all the magic.

Not convinced yet?

Maybe i should show you a more complex example:\
A text with some colors and some style elements:\
![message image](https://cdn.discordapp.com/attachments/232204409306742784/573893966630486036/unknown.png "")\
and a text hover:\
![hover image](https://cdn.discordapp.com/attachments/232204409306742784/573894051116482564/unknown.png "")

**JSON Notation**:\
`["",{"text":"Small "},{"text":"bold and ","bold":true},{"text":"italic and ","bold":true,"italic":true},{"text":"colored test","bold":true,"italic":true,"color":"dark_red"},{"text":" and maybe a small "},{"text":"hover test","hoverEvent":{"action":"show_text","value":["",{"text":"bold and colored ","bold":true},{"text":"hover","bold":true,"color":"dark_blue"},{"text":" text","bold":true}]}}]`\
Can you still read this in a easy way? Or even write this quick?

**EMMI Notation**:\
`Small [b]bold and [i]italic and [c=4]colored test[c=r][/b][/i] and maybe a small [htext={[b]bold and colored [c=1]hover[c=r] text[/b]}]hover test[/htext]`\
This is way easier to understand and pretty neat. As you can see, it is also possible to use marker in the hover text.

## Implementation

That looks nice, but how difficult is the implementation?\
Getting started is realy easy. It is just one line for the basic EMMI implementation. Let's have a look.

### Basic Implementation
The basic implementation is pretty easy and self explaining:

Just import: `de.eldoria.emmi.converter`

Then call the static method convert() on JsonConverter and pass the EMMI message.

`String myTellrawMessage = JsonConverter.convert(myEmmiMessage);`

Yes thats all. I think this is pretty easy. You can do it :3

### Implementation with Placeholdern
Placeholder are one more nice feature of EMMI. They are simple but useful.

First comes the import:\
`de.eldoria.emmi.converter.placeholder`

Now we can start with the creation.

**Creation**\
To create Placeholders you will need a placeholder creator. It first seems a bit weird, but in the end it will be useful.\
Creation is simple:\
`Placeholder.Creator c = new Placeholder.Creator();`

After this you can create placeholder. As many as you want.\
The creator caches your placeholder and keep them save and warm:\
`c.create("%player%", "Hadde").create("%servername%", "eldoria")`

After that you can use them everytime. I recommend to cache the creator at a good and reachable place.\
Let's have a look at a example implementation:

        String myEmmiMessage = "My name is %player% and I am playing on %servername%.";

        Placeholder.Creator c = new Placeholder.Creator();
        c.create("%player%", "Hadde").create("%servername%", "eldoria");

        String myTellrawMessage = JsonConverter.convert(myEmmiMessage, c.toArray());

Now your myTellrawMessage looks like this:\
`{"text":"My name is Hadde and I am playing on eldoria."}`

But yes I hear you saying "But not every player has the same name."\
Of course and that's why you can change the value of a placeholder every time.\

        String myEmmiMessage = "My name is %player% and I am playing on %servername%.";

        Placeholder.Creator c = new Placeholder.Creator();
        
        c.create("%player%", "Hadde").create("%servername%", "eldoria");

        String myTellrawMessage1 = JsonConverter.convert(myEmmiMessage, c.toArray());
        
        c.changePlaceholderValue("%player%", "SirYwell");  //Changes the player name to "SirYwell"
        
        String myTellrawMessage2 = JsonConverter.convert(myEmmiMessage, c.toArray());

So your first Message will look like the previous. But your second is slightly different:\
`{"text":"My name is SirYwell and I am playing on eldoria."}`


Now there are two more small feature. Of course you can also delete a placeholder with:\
`c.removePlaceholder("%player%");`\
or all placeholders if you don't like them anymore or if you need a fresh start:\
`c.clearPlaceholder();`

## Marker
EMMI uses a marker system to format text. \
Marker can start or end a section.\
A sections starts with a start marker and ends with a end marker.

Start Marker: `[marker]`\
End Marker: `[/marker]`

A end section is not always needed. If you want to mark the whole text after the marker, dont end it end on the marker will be applied to the whole text after the marker.

Here is a list of marker and the usage:

#### Bold
Description: Makes the text bold\
Start Marker: `[b]`\
End Marker: `[/b]`\
Examples:
* `[b]this text is bold[/b]` =>  **this text is bold**

#### Italic
Description: Makes the text italic\
Start Marker: `[i]`\
End Marker: `[/i]`\
Examples:
* `[i]this text is italic[/i]` => _this text is italic_

#### Underlined
Description: Makes the text underlined\
Start Marker: `[u]`\
End Marker: `[/u]`\
Examples:
* `[u]this text is underlined[/u]`

#### Strikethrough
Description: Makes the text struck out\
Start Marker: `[s]`\
End Marker: `[/s]`\
Examples:
* `[s]this text is struck out[/s]` => ~~this text is struck out~~

#### Obfuscated
Description: Makes the text obfuscated\
Start Marker: `[o]`\
End Marker: `[/o]`\
Examples:
* `[o]this text is obfuscated[/o]`

#### Color
Description: Changes the color of the text. Color is set by a char.\
To reset the color use the 'r' char.\
The other colors are the common minecraft colors. You can find them [here](https://wiki.vg/Chat#Colors). \
Start Marker: `[c=<ColorChar>]`\
End Marker: The color doesn't have an end marker. It just overrides the last set color.\
Examples:
* `[c=4]this text is red[c=r]this has the default color`

#### Open URL
Description: Creates a clickable text, which opens a url on click.\
Start Marker: `[url={<url>}]`\
End Marker: `[/url]`\
Examples:
* `[url={eldoria.de}]open the website of eldoria[/url}` => Creates a clickable text, which open the url "eldoria.de"


#### Run Command / Write Text
Description: Creates a clickable text, which runs a command or sends a message to the chat\
Start Marker: `[runcmd={<command/text>}]`\
End Marker: `[/runcmd]`\
Examples:
* `[runcmd={/help}]click here for help[/runcmd]` => creates a clickable text, which executes the "/help" command on click.
* `[rundmc={I am awesome}]Are you awesome? Click to find it out![/runcmd]` => creates a clickable text, which sends the message "I am awesome" to the chat on click.

#### Suggest Command / Text
Description: Creates a clickable text, which suggests a command or text to the player. \
Start Marker: `[sugcmd={<command/text>}]`\
End Marker: `[/sugcmd]`\
Examples:
* `[sugcmd={/msg eldoria}]Click here to send a message to eldoria[/sugcmd]` => suggests a "/msg eldoria" command to the player.
* `[sugcmd={I am groot}]Click here for some wise words to send[/sugcmd]` => suggests the player to send "I am groot" in the chat.

#### Change Page
Description: Changes the page of a book. Obviously only works in books.\
Start Marker: `[gotopage={<pagecount>]`\
End Marker: `[/gotopage]`\
Examples:
* `[gotopage={2}]Click here to go to site 2[/gotopage]` => Changes to site 2 in the book

#### Hover Text
Description: Displays a text on Hover.\
Markers can used for the hover Text. Except the hover or click Marker ;)\
Start Marker: `[htext={hovertext}]`\
End Marker: `[/htext]`\
Examples:
* `[htext={this text is shown when you over over "hover me"}]hover me[/htext]` => Shows the text, when you hover over "hover me"
* `[htext={[b]this is a nice bold text[/b]}]show me some nice bold text[/htext]`=> Shows a bold text on hover.

#### Hover Item
Description: Displays a item on hover.\
The Item input is as a NBT-JSON string.\
Start Marker: `[hitem={<json-nbt string>}]`\
End Marker: `[/hitem]`\
Examples:
* `[hitem={id:"minecraft:stone",Damage:5,Count:2,tag:{display:{Name:Testing}}}]display test item[/hitem]`

#### Hover Entity
Description: Displays an entity on hover\
The entity input is as a NBT-JSON string.\
Start Marker: `[hentity={<json-nbt string>}]`\
End Marker: `[/hentity]`\
Examples:
* `[hentity={id:7e4a61cc-83fa-4441-a299-bf69786e610a,type:minecraft:zombie,name:Zombie}]show me a zombie[/hentity]`
