### Matching Patterns and Literals
 - :set ignorecase
 - \c,\C in search pattern : override Vim’s default case sensitivity using the \c and \C items.
 - smartcase : canceling out the ‘ignorecase’ setting any time that we include an uppercase character in our search pattern.

| Pattern | 'ignorecase' | 'smartcase' | Matches     |
|---------|-------------:|:-----------:|-------------|
| foo     | off          | -           | foo         |
| foo     | on           | -           | foo Foo FOO |
| foo     | on           | on          | foo Foo FOO |
| Foo     | on           | on          | Foo         |
| Foo     | on           | off         | foo Foo FOO |
| \cfoo   | -            | -           | foo Foo FOO |
| foo\C   | -            | -           | foo         |

three types of brackets:

- Square brackets: have a special meaning
- Parentheses: match the characters literally, escape them to make them take on a sepcial meaning.
- curly braces: escape only the opening member of the pair. leave the closing brace unescaped. 

- 'very magic' search(\v): all characters assume a special meaning,
with the exception of “_”, uppercase and lowercase letters, and the digits 0 through 9.
- verynomagic literal(\V) : most of the special meanings attached to characters such as ., *, and ?.

```
# has no special meaning and is matched literally
```

##### special syntax in vim regex 
| Pattern          | 'ignorecase'                                                       |
|------------------|-------------------------------------------------------------------:|
| %()              | use parentheses for grouping without capturing the submatch.       |
| <>               | represented word boundary delimiters in very magic searches.       |
| \zs              | marks the start of a match.                                        |
| \ze              | marks the end of a match.                                          |
| /                | search forward                                                     |
| ?                | search backward.                                                   |
| \V literal<C-r>= | switches from the search prompt to the expression register prompt. |

### Meet the Search Command
| Command | Effect |
|---------|-------------:|
| n | Jump to next match, preserving direction and offset |
| N | Jump to previous match, preserving direction and offset |
| /<CR> | Jump forward to next match of same pattern |
| ?<CR> | Jump backward to previous match of same pattern |
| gn | Enable character-wise Visual mode and select next search match |
| gN | Enable character-wise Visual mode and select previous search match |

 - incsearch : review the First Match Before Execution.
 - <C-r><C-w> : autocompletes the search field using the remainder of the current preview match for words and not patterns.
 - /lang/e<CR> : places the cursor at the end of the search match, exactly where we need it.

gUgn :  jumps to the next match and applies the last operation.
Improved Dot Formula.

### Substitution
```
:[range]s[ubstitute]/{pattern}/{string}/[flags]
```
##### flags
- g : makes the substitute command act globally, causing it to change all matches within a line rather than just changing the first one.
- c : gives us the opportunity to confirm or reject each change. 
- n : suppresses the usual substitute behavior, causing the command to report the number of occurrences that would be affected if we ran the substitute command.
- e : silence substitution errors.
- & : reuse the same flags from the previous substitute command.

| Symbol         | represents                                                              |
|----------------|------------------------------------------------------------------------:|
| \r             | Insert a carriage return                                                |
| \t             | Insert a tab character                                                  |
| \\             | Insert a single backslash                                               |
| \1             | Insert the first submatch                                               |
| \2             | Insert the second submatch (and so on, up to \9)                        |
| \0             | Insert the entire matched pattern                                       |
| &              | Insert the entire matched pattern                                       |
| ~              | Use replace parts({string}) from the previous invocation of :substitute |
| \={Vim script} | Evaluate {Vim script} expression; use result as replacement {string}    |

> Vim is a direct descendent of the line editor ed.
> <C-r>/: pastes the contents of the last search register
```
%s : go with entire file
s  : go with current line
```

##### options of c flag
| Trigger | Effect                                          |
|---------|------------------------------------------------:|
| y       | Substitute this match                           |
| n       | Skip this match                                 |
| q       | Quit substituting                               |
| l       | "last"—Substitute this match, then quit         |
| a       | "all"—Substitute this and any remaining matches |
| <C-e>   | Scroll the screen up                            |
| <C-y>   | Scroll the screen down                          |

```
:%s//\=@0/g

the contents of a register as @{register}
```

```
:let @/='Pragmatic Vim'
:let @a='Practical Vim'
:%s//\=@a/g
```

:let @/='Pragmatic Vim' is a programmatic way of setting the search pattern.  
(except that running :let @/='Pragmatic Vim' does not create a record in the search history)

- g& after substitute : == ':%s//~/&'
- && : the first :& Ex command which repeats the last :substitute command, 
    while the second one indicates that the flags from the previous :s command should be reused.
    - :&& by itself acts on the current line.
    - g& == %&&
    - :& repeats the last substitution, but disregards original flags.

- submatch(0) : fetch current match in string parts. 
    ex: 
    
    ```
        :%s//\=submatch(0)-1/g
        :%s//\={"dog":"man","man":"dog"}[submatch(1)]/g
    ```
| Ex Command | Effect                                                       |
|----------------|-------------------------------------------------------------:|
| vimgrep        | perform a project-wide search                                |
| copen          | opens the quickfix window                                    |
| cfdo {command} | run command on every file that appears in the quickfix list. |

### Global Commands
```
:[range] global[!] /{pattern}/ [cmd]
:g/{pattern}/[range][cmd]
:g/{start}/ .,{finish} [cmd]
```
The default range for the :global command is the entire file (%).

:vglobal : invert the behavior of the :global command(mnemonic: invert).

```
ex:

:g/TODO/yank A
:g/{ .+1,/}-1 sort
```
> :> echoes a message each time it is invoked, whereas
> :sort doesn’t
> :silent mute these messages, ```:g/{/sil .+1,/}/-1 >```
