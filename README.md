# EMMI - Eldoria Message Markup Interpreter
EMMI is a easy way to generate complex JSON strings with a easy marker system.

## Marker
EMMI uses a marker system to format text. \
Marker can start or end a section.\
A sections starts with a start marker and ends with a end marker.

Start Marker: `[marker]`\
End Marker: `[/marker]`

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
Start Marker: `[k]`\
End Marker: `[/k]`\
Examples:
* `[k]this text is obfuscated[/k]`

#### Color
Description: Changes the color of the text. Color is set by a char.\
To reset the color use the 'r' char\
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
Start Marker: `[runcmd={<command>}]`\
End Marker: `[/runcmd]`\
Examples:
* `[runcmd={/help}]click here for help[/runcmd]` => creates a clickable text, which executes the "/help" command on click.
* `[rundmc={I am awesome}]Are you awesome? Click to find it out![/runcmd]` => creates a clickable text, which sends the message "I am awesome" to the chat on click.

#### Bold
Description: \
Start Marker: `[]`\
End Marker: `[/]`\
Examples:
* 
*
*
