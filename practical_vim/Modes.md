### Normal Mode
Vim's Operator Mode

| Keystrokes | Effect                                            |
|------------|--------------------------------------------------:|
| c          | Change                                            |
| d          | Delete                                            |
| y          | Yank into register                                |
| g~         | Swap case                                         |
| gu         | Make lowercase                                    |
| gU         | Make uppercase                                    |
| >          | Shift right                                       |
| <          | Shift left                                        |
| =          | Autoindent                                        |
| !          | Filter {motion} lines through an external program |

> gUgU/gUU : act upon the current line.

### Insert Mode

| Keystrokes | Effect                                |
|------------|--------------------------------------:|
| <C-h>      | Delete back one character (backspace) |
| <C-w>      | Delete back one word                  |
| <C-u>      | Delete back to start of line          |

##### Switch Mode
| Keystrokes | Effect                       |
|------------|-----------------------------:|
| <Esc>      | Switch to Normal mode        |
| <C-[>      | Switch to Normal mode        |
| <C-o>      | Switch to Insert Normal mode |

> Insert Normal mode: switch from Insert Mode, run only one Normal command and then continue where we left off in Insert mode. 
> Insert Normal mode, common used case: <C-o>zz
> zz : redraws the screen with the current line in the middle of the window, which allows us to read half a screen above and below the line we’re working on.

| Keystrokes           | Effect                                                       |
|----------------------|-------------------------------------------------------------:|
| <C-r>{register}      | insert with indent.                                          |
| <C-r><C-p>{register} | inserts text literally and fixes any unintended indentation. |
| <C-r>=6\*35<CR>       | do the math in vim.                                          |
| <C-v>{code}          | insert character with unicode, ex: <C-v>065                  |
| ga                   | show numeric code under current cursor.                      |

###### Inserting Unusual Characters
| Keystrokes          | Effect                                                 |
|---------------------|-------------------------------------------------------:|
| <C-v>{123}          | Insert character by decimal code                       |
| <C-v>u{1234}        | Insert character by hexadecimal code                   |
| <C-v>{nondigit}     | Insert nondigit literally                              |
| <C-k>{char1}{char2} | Insert character represented by {char1}{char2} digraph |

> Virtual Replace mode : gR treats the tab character as though it consisted of spaces.
> r{char} and gr{char} : overwrite a single character before switching straight back to Normal mode.

### Visual Mode
| Keystrokes    | Effect                                                                                           |
|---------------|-------------------------------------------------------------------------------------------------:|
| <Esc> / <C-[> | Switch to Normal mode                                                                            |
| v / V / <C-v> | Switch to Normal mode (when used from character-, line- or block-wise Visual mode, respectively) |
| v             | Switch to character-wise Visual mode                                                             |
| V             | Switch to line-wise Visual mode                                                                  |
| <C-v>         | Switch to block-wise Visual mode                                                                 |
| gv            | Reselect the last visual selection                                                               |
| o             | Go to other end of highlighted text                                                              |

### Execution Mode
| Keystrokes                                    | Effect                                                                          |
|-----------------------------------------------|--------------------------------------------------------------------------------:|
| :[range]delete [x]                            | Delete specified lines [into register x]                                        |
| :[range]yank [x]                              | Yank specified lines [into register x]                                          |
| :[line]put [x]                                | Put the text from register x after the specified line                           |
| :[range]copy {address}                        | Copy the specified lines to below the line specified by {address}               |
| :[range]move {address}                        | Move the specified lines to below the line specified by {address}               |
| :[range]join                                  | Join the specified lines                                                        |
| :[range]normal {commands}                     | Execute Normal mode {commands} on each specified line                           |
| :[range]substitute/{pattern}/{string}/[flags] | Replace occurrences of {pattern} with {string} on each specified line           |
| :[range]global/{pattern}/[cmd]                | Execute the Ex command [cmd] on all specified lines where the {pattern} matches |

> :{address}+n : {address} could be a line number, a mark, or a pattern.

| Symbol | Address                                   |
|--------|------------------------------------------:|
| 1      | First line of the file                    |
| $      | Last line of the file                     |
| 0      | Virtual line above first line of the file |
| .      | Line where the cursor is placed           |
| 'm     | Line containing mark m                    |
| '<     | Start of visual selection                 |
| '>     | End of visual selection                   |
| %      | The entire file (shorthand for :1,$)      |
```just for fan'```
| Ex command | abbre |
|------------|-------|
| copy       | t     |
| normal     |       |
| delete     | d     |

> normal : repetitive command on specific range.

| Ex command | Effect                                                               |
|------------|----------------------------------------------------------------------|
| @:         | repeat the last Ex command                                           |
| @@:        | repeat @:                                                            |
| <C-o>:     | The <C-o> command goes back to the previous record in the jump list. |
| <C-d>:     | Vim to reveal a list of possible completions.                        |
| *          | search current word for each occurrence                              |

> Each time we run :bnext (or repeat it with the @: command), it adds a record to the jump list.
> * equal to typing sequence /\<<C-r><C-w> \><CR>.
> <C-r><C-w>: gets the word under the cursor,

| Command | Action                                                   |
|---------|----------------------------------------------------------|
| @:      | repeat the last Ex command                               |
| q/      | Open the command-line window with history of searches    |
| q:      | Open the command-line window with history of Ex commands |
| ctrl-f  | Switch from Command-Line mode to the command-line window In Command-Line mode |

Vim provides a convenient shortcut for setting the range of a :[range]!{filter} command such as this.
> <C-z> / jobs

```
:write !sh
:write ! sh
:write! sh

The first two commands pass the contents of the buffer as standard input to
the external sh command. The last command writes the contents of the buffer
to a file called sh by calling the :write! command.

:write !sh   :   each line of the current buffer is executed in the shell.
```

#### call externel commands
| Command              | Effect                                                                     |
|----------------------|---------------------------------------------------------------------------:|
| :shell               | Start a shell (return to Vim by typing exit)                               |
| :!{cmd}              | Execute {cmd} with the shell                                               |
| :read !{cmd}         | Execute {cmd} in the shell and insert its standard output below the cursor |
| :[range]write !{cmd} | Execute {cmd} in the shell with [range] lines as standard input            |
| :[range]!{filter}    | Filter the specified [range] through external program {filter}             |

> For example, if we place our cursor on line 2 and then invoke !G, Vim opens a prompt with the :.,$! range set up for us.




