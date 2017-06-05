| Keystrokes                | Effect                                               |
|---------------------------|-----------------------------------------------------:|
| :set ignorecase           |                                                      |
| :set noignorecase         | turn this feature off                                |
| :set ignorecase!          | toggle the setting                                   |
| :set ignorecase?          | current option of this feature                       |
| :set ignorecase&          | reset any option to its default value                |
| :setlocal tabstop=4       | feature apply to the active buffer only              |
| :bufdo setlocal tabstop=4 | feature apply the same value to all existing buffers |
| :windo setlocal tabstop=4 | feature apply the same value to all window           |
| :edit $MYVIMRC            | edit vimrc file                                      |
| :source $MYVIMRC          | load new configuration into our Vim session          |

> :set number: enable line numbering for the current window as well as set a new
> global default. Existing windows would retain their local settings.

> If the vimrc file is the active buffer, then this can be shortened to :so %
