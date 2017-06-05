### Manage Multiple Files
#### buffer control
| 快捷键      | 作用     |    |
| --------   | -----:  | :----: |
| :ls        | display all the buffers      |   5    |
| :args index.html app.js  | populating the argument list | 
| :args `cat .chapters`  | result of shell as argument for the :args | 
| :bnext     | switch to the next buffer in the list      |   6    |
|  <C-^>     | switch to the next buffer in the list      |   7    |
| :b number  | jump directly to a buffer by number      |   7    |
| :bdelete buffer | delete a buffer |   7    |
| :5,10bd | delete buffers numbered 5 through 10 inclusive |   7    |
| :w[rite] | Write the contents of the buffer to disk|
| :e[dit]! | Read the file from disk back into the buffer (that is, revert changes)|
| :qa[ll]! | Close all windows, discarding changes without warning|
| :wa[ll]  | Write all modified buffers to disk|

#### argument
```
We can change the contents of the argument list at any time,
which means that the :args listing doesn’t necessarily reflect 
the values that were passed to the vim command when we launched 
the editor. 
```

> Tim Pope’s unimpaired.vim plugin.
```
nnoremap <silent> [b :bprevious<CR>.
nnoremap <silent> ]b :bnext<CR>
nnoremap <silent> [B :bfirst<CR>
nnoremap <silent> ]B :blast<CR>
```

### window
| Ex command | Normal Command     |    |
| --------   | -----:  | :----: |
| | \<C-w>s  | Split the current window horizontally, reusing the current buffer in the new window |
| | \<C-w>v  | Split the current window vertically, reusing the current buffer in the new window |
| :sp[lit] {file} | | Split the current window horizontally, loading {file} into the new window |
| :vsp[lit] {file} | | Split the current window vertically, loading {file} into the new window |
| | \<C-w>w | Cycle between open windows |
| | \<C-w>h | Focus the window to the left |
| | \<C-w>j | Focus the window below |
| | \<C-w>k | Focus the window above |
| | \<C-w>l | Focus the window to the right |
| :clo[se] | \<C-w>c | Close the active window |
| :on[ly] | \<C-w>o | Keep only the active window, closing all others |

```
<C-w><C-w>  === <C-w>w
```
#### less used
| Keystrokes | BufferContents     |    |
| --------   | -----:  | :----: |
| \<C-w>= | Equalize width and height of all windows |
| \<C-w>_ | Maximize height of the active window |
| \<C-w>| | Maximize width of the active window |
| [N]\<C-w>_ | Set active window height to [N] rows |
| [N]\<C-w>| | Set active window width to [N] columns |

#### tabs

| Ex Command | Normal Command | Effect     |
| --------   | -----:  | :----: |
| :tabe[dit] | | {filename} Open {filename} in a new tab |
| :lcd {path} | | set the working directory locally for the current window |
| | \<C-w>T | Move the current window into a new tab |
| | {N}gt | goto tab {N} |
| :tabc[lose] | | Close the current tab page and all of its windows |
| :tabo[nly] | | Keep the active tab page, closing all others |
| :tabn[ext] {N} | {N}gt | Switch to tab page number {N} |
| :tabn[ext] | gt | Switch to the next tab page |
| :tabp[revious] | gT | Switch to the previous tab page |
| :tabmove [N] | | When [N] is 0, the current tab page is moved to the beginning, and if we omit [N], the current tab page is moved to the end|

### Open Files and Save Them to Disk
```
1. The 'path' option allows us to specify a set of directories inside of which Vim will search when the :find command is invoked

```
| :edit %<Tab> | The % symbol is a shorthand for the filepath of the active buffer | 
| :edit %:h<Tab> | expanded to the full path of the current file’s directory|
| :set path+=app/** | |


```
1.enable plugin netrw
set nocompatible
filetype plugin on
```
Ex Command  sh
| Ex Command | Shorthand | Effect |
| --------   | -----:  | :----: |
| :edit . | :e.| Open file explorer for current working directory |
| :Explore | :E | Open file explorer for the directory of the active buffer |
| :Sexplore | | open the file explorer in a horizontal split window |
| :Vexplore | | open the file explorer in a vertical split window |
| :w !sudo tee % > /dev/null | | |
```
netrw makes it possible to read and write files across a network. The plugin
can use many protocols, including scp, ftp, curl, and wget, depending on what’s
available on your system
```
