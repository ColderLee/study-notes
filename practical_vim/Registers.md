### Matching Patterns and Literals

| Registers                                               | Effect                                                                                            |
|---------------------------------------------------------|--------------------------------------------------------------------------------------------------:|
| The Unnamed Register ("")                               |                                                                                                   |
| The Yank Register ("0)                                  | yank not only into the unnamed register but also into the yank register                           |
| The Named Registers ("a–"z)                             | use a lowercase letter mean overwrite, and use upper letter mean append to the specified register |
| The Black Hole Register ("\_)                            |                                                                                                   |
| The System Clipboard ("+) and Selection ("\*) Registers |                                                                                                   |
| The Expression Register ("=)                            |                                                                                                   |
| "+                                                      | The X11 clipboard, used with cut, copy, and paste(not work for me)                                |
| "\*                                                      | The X11 primary, used with middle mouse button(not work for me)                                   |
| "%                                                      | [readonly] Name of the current file                                                               |
| "#                                                      | [readonly] Name of the alternate file                                                             |
| ".                                                      | [readonly] Last inserted text                                                                     |
| ":                                                      | [readonly] Last Ex command                                                                        |
| "/                                                      | [readonly] Last search pattern                                                                    |

 
| Keystrokes | Effect |
| --------   | -----:  |
| "0P | paste from the yank register without change yank register |
| :reg "0 | inspect the contents of the unnamed and yank registers. |

1. In Windows and Mac OS X, there is no primary clipboard, so we can use the "+ and "\* registers interchangeably: they both represent the system clipboard.
2. "/ register is not read-only—it can be set explicitly using the :let command

| Keystrokes | Effect |
| --------   | -----:  |
| <C-R> | Insert the contents of a numbered or named register. |

```
When we use the system paste command in Insert mode,
Vim acts as though each character has been typed by hand.
```
### Macros
| Keystrokes | Effect |
|------------|-------:|
| q | both as the “record” button and the “stop” button |
| @{register} | executes the contents of the specified register |
| @@ | repeats the macro that was invoked most recently |
| :argdo normal @a | update file in parallel |
| :wall | save all files in the buffer list |
| :wnext | == ':write<CR>:next' |

```
update file in series
append a final step that advances to the next buffer in the list | update file in parallel, then run with 3@a
```

| Keystrokes     | Effect                                                         |
|----------------|---------------------------------------------------------------:|
| :put a         | pastes register content below the current line                 |
| "ap            | paste the contents of the a register after the cursor position |
| q/             | summon the command-line window                                 |
| vimgrep // g % | quickfix list with each match found in the current buffer      |
| cnext / cprev  | back and forward in quickfix list                              |

```
in register content: 
 ^[ : represent <Esc> or <C-[>.
<80>kb : represents the backspace key.
```

```
:%s///gn

n flag suppresses the usual behavior.
```

```
As well as overriding the * command, we’ve customized the # command,
which searches backward for selected text.


noremap * :<C-u>call <SID>VSetSearch('/')<CR>/<C-R>=@/<CR><CR>
xnoremap # :<C-u>call <SID>VSetSearch('?')<CR>?<C-R>=@/<CR><CR>
function! s:VSetSearch(cmdtype)
let temp = @s
norm! gv"sy
let @/ = '\V' . substitute(escape(@s, a:cmdtype.'\'), '\n', '\\n', 'g')
let @s = temp
endfunction
```
