### Tools
#### Index and Navigate Source Code with ctags
```
:nnoremap <f5> :!ctags -R<CR>I
```

Automatically Execute ctags Each Time a File is Saved
```
:autocmd BufWritePost * call system("ctags -R")
```

instruct source control to re-index the repository every time we commit our code.
set up hooks for the post-commit, post-merge, and post-checkout events.

| Keystrokes       | Effect                                                                                                                             |
|------------------|-----------------------------------------------------------------------------------------------------------------------------------:|
| <C-]>            | Jump to the first tag that matches the word under the cursor                                                                       |
| g<C-]>           | Prompt user to select from multiple matches for the word under the cursor. If only one match exists, jump to it without prompting. |
| :tjump {keyword} | Jump to the first tag that matches {keyword}                                                                                       |
| :tag {keyword}   | Prompt user to select from multiple matches for {keyword}. If only one match exists, jump to it without prompting.                 |
| :pop or <C-t>    | Reverse through tag history                                                                                                        |
| :tag             | Advance through tag history                                                                                                        |
| :tnext           | Jump to next matching tag                                                                                                          |
| :tprev           | Jump to previous matching tag                                                                                                      |
| :tfirst          | Jump to first matching tag                                                                                                         |
| :tlast           | Jump to last matching tag                                                                                                          |
| :tselect         | Prompt user to choose an item from the tag match list                                                                              |
```
:tag /{pattern} or :tjump /{pattern}
:tjump /phone$
```
### Compile Code and Navigate Errors with the Quickfix List
- :make
  ```
    get the same result as when we ran make in the shell.
    For each warning, Vim creates a record in the quickfix list.
    We can navigate backward and forward through these records, and Vim jumps
    to the exact line corresponding to the error message.
  ```
- :make!
  ```
    ! character tells Vim to update the quickfix list without jumping to the first item.
  ```
- <C-o>: jump back to the previous position in the jump list.

| Command   | Action                                                             |
|-----------|-------------------------------------------------------------------:|
| :cnext    | Jump to next item                                                  |
| :cprev    | Jump to previous item                                              |
| :cfirst   | Jump to first item                                                 |
| :clast    | Jump to last item                                                  |
| :cnfile   | Jump to first item in next file                                    |
| :cpfile   | Jump to last item in previous file                                 |
| :cc N     | Jump to nth item                                                   |
| :copen    | Open the quickfix window                                           |
| :cclose   | Close the quickfix window                                          |
| :cdo      | {cmd} Execute {cmd} on each line listed in the quickfix list       |
| :cfdo     | {cmd} Execute {cmd} once for each file listed in the quickfix list |
| :colder   | recall an older version of the quickfix list                       |
| :cnewer   | recall an newer version of the quickfix list                       |
| :compiler | activite a compiler plugin, interpret related configuration        |

> location list
> Every command that populates the quickfix list will places the results in a location list instead.
> :lmake, :lgrep, :lvimgrep

:cnext and :cprev can be prepended with a count.
```
:5cnext
```

##### Configure ‘:make’ to Invoke Nodelint
```
:setlocal makeprg=NODE_DISABLE_COLORS=1\ nodelint\ %
```
NODE_DISABLE_COLORS=1 mutes nodelint default colors.
| Command | Action |
|---------|-------:|
| :setglobal errorformat? | inspect the default value of 'errorformat' |
| :setlocal efm={format} | set the error format |
```
    %f stands for the filename
    %l for the line number
    %m for the error message
set error format ex:
    :setlocal efm=%A%f\,\ line\ %l\,\ character\ %c:%m,%Z%.%#,%-G%.%#
```

### Search Project-Wide with grep, vimgrep, and Others
| Command  | Action                                                                        |
|----------|------------------------------------------------------------------------------:|
| :grep    | a wrapper for the external grep program, can't use vim current search pattern |
| :vimgrep | search through multiple files using Vim’s native regular expression engine    |

> Unix's grep's regex syntax is incompatible with the one we use in vim.

On Unix systems, default grepprg and grepformat are these:
```
grepprg="grep -n $* /dev/null"
grepformat="%f:%l:%m,%f:%l%m,%f %l%m"
```
The special tokens used in the ‘grepformat’ string are the same as those used by 'errorformat', 'grepformat' contain multiple formats separated by commas.

```
:vimgrep /going/ clock.txt tough.txt where.txt
:vimgrep /going/g clock.txt tough.txt where.txt     # g flag will match all occurrences of the specified pattern, not just the first match on each line
```

```
:vim[grep][!] /{pattern}/[g][j] {file}
```
- {file} argument must not be blank.
- {file} can be ##, represent the names of each file in the arugment list.
- vim regex pattern default will only affect the first match on the line.

> <C-r>/ : fill the search field with the value of the current pattern.
```
:vim /<C-r>//g ##
```
##### replace :grep with ack
Make ack Jump to Line and Column

```
:set grepprg=ack\ --nogroup\ --column\ $*
:set grepformat=%f:%l:%c:%m
```

### Dial X for Autocompletion
autocompletion insert mode
| Keystrokes | Type of Completion      |
|------------|------------------------:|
| <C-n>      | Generic keywords        |
| <C-x><C-n> | Current buffer keywords |
| <C-x><C-i> | Included file keywords  |
| <C-x><C-]> | tags file keywords      |
| <C-x><C-k> | Dictionary lookup       |
| <C-x><C-l> | Whole line completion   |
| <C-x><C-f> | Filename completion     |
| <C-x><C-o> | Omni-completion         |

Commands for the Pop-Up Menu
| Keystrokes | Effect                                                                                                    |
|------------|----------------------------------------------------------------------------------------------------------:|
| <C-n>      | Use the next match from the word list (next match), updates the document to use the selected word         |
| <C-p>      | Use the previous match from the word list (previous match), updates the document to use the selected word |
| <Down>     | Select the next match from the word list, text in the document is left unchanged                          |
| <Up>       | Select the previous match from the word list, text in the document is left unchanged                      |
| <C-y>      | Accept the currently selected match (yes)                                                                 |
| <C-e>      | Revert to the originally typed text (exit from autocompletion)                                            |
| <C-h>      | (and <BS>) Delete one character from current match                                                        |
| <C-l>      | Add one character from current match                                                                      |
| {char}     | Stop completion and insert {char}                                                                         |

```
<C-n><C-p>/<C-p><C-n> : leave text untounched and show autocompletion pop-up Menu.
```

- generic keyword autocompletion uses the list generated from the buffer list, included files, and tag files into one.
- :ls! : inspect the buffer list.
- <C-x>C-i> : work with file-type plugin.
- :set include? : inspect vim included rule by current file language
- :set spell : enable Vim’s spell checker. 
- unenable spell checker, specify one or more files with dictionary option.

> generic autocompletion default setting is complete=.,w,b,u,t,i. more details lookup :h 'complete' 
> Vim remembers the context from which that word was taken. If we invoke autocomplete a second time, Vim will insert the word that followed the original completion.
> press <C-x><C-p> multiple times will insert a sequence of consecutive words.
> Omni-completion is Vim’s answer to intellisense

:cd - : switches to the previous working directory.
### Find and Fix Typos with Vim’s Spell Checker
| Command    | Effect                                   |
|------------|-----------------------------------------:|
| :set spell | enabling the built-in spell checker      |
| ]s         | Jump to next spelling error              |
| [s         | Jump to previous spelling error          |
| z=         | Suggest corrections for current word     |
| zg         | Add current word to spell file           |
| zw         | Remove current word from spell file      |
| zug        | Revert zg or zw command for current word |
> Vim records words added to the dictionary in a spell file so that they persist from one session to another. 

- <C-x>s trigger an autocomplete command in Insert Mode.
- <C-x>s scans backward from the cursor position, stopping when it finds a misspelled word. It then builds a word list from suggested corrections and presents them in an autocomplete pop-up menu.
- <C-x><C-s> is same with <C-x>s.
