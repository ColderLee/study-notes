### 3 Packaging and tooling
### 3.1 Dependency management
create reproducible builds
##### Vendoring dependencies
idea:
copy all the dependencies into a directory inside the project repo,
and then rewrite any import paths that reference those dependencies
by providing the location inside the project itself.

##### gb
Gb replaces the Go tooling workspace metaphor with a project-based approach.
This has natively allowed vendoring without the need for rewriting import paths,
which is mandated by go get and a GOPATH workspace.

```
$PROJECT/vendor/src, $PROJECT/src
```

### 4 Arrays, slices, and maps
#### 4.1 array
After the copy, you have two arrays with identical values

```
var array1 [5]string
// Declare a second string array of five elements.
// Initialize the array with colors.
array2 := [5]string{"Red", "Blue", "Green", "Yellow", "Pink"}
// Copy the values from array2 into array1.
array1 = array2
```

#### 4.2 slice

```
// Create an array of three integers.
array := [3]int{10, 20, 30}
// Create a slice of integers with a length and capacity of three.
slice := []int{10, 20, 30}

// Create a nil slice of integers. without initialization
var slice []int             // length: 0, capacity: 0, point is nil
```

##### empty slice
point to a zero-sized underlying array.

```
// Use make to create an empty slice of integers.
slice := make([]int, 0)
// Use a slice literal to create an empty slice of integers.
slice := []int{}
```

Capacity is always doubled when the existing capacity of the slice is under 1,000 elements.
Once the number of elements goes over 1,000, the capacity is grown by a factor of 1.25, or 25%.
This growth algorithm may change in the language over time.

specify capacity(third index parameter) will result create new underlying array.

##### third index specify capacity
By having the option to set the capacity of a new slice to be the same as the length,
you can force the first append operation to detach the new slice from the underlying
array.

##### Passing slices between functions
Passing a slice between two functions requires nothing more than passing the slice by
value.

#### 4.3 map
todo: learn the implement of map in go

##### implement:
1. an array with the top eight high order bits(HOB) from the same hash key
that was used to select the bucket. This array distinguishes each individual key/value pair
stored in the respective bucket.
2. an array of bytes that stores the key/value pairs.
The byte array pack all the keys and then all the values together for the respective bucket.
The packing of the key/value pairs is implemented to minimize the memory required for each
bucket.


Passing a map between two functions doesn’t make a copy of the map.

### 5 Go’s type system

#### 5.1 methods
When you declare a method using a value receiver,
the method will always be operating against a copy
of the value used to make the method call.

#### 5.2 struct types
When the decision is made that a struct type value should not be mutated
when something needs to be added or removed from the value, then it should follow
the guidelines for the built-in and reference types.

In most cases, struct types don’t exhibit a primitive nature, but a nonprimitive one.

```
// File represents an open file descriptor.
type File struct {
    *file
}

// file is the real representation of *File.
// The extra level of indirection ensures that no clients of os
// can overwrite this data, which could cause the finalizer
// to close the wrong file descriptor.
type file struct {
    fd int
    name string
    dirinfo *dirInfo // nil unless directory being read
    nepipe int32 // number of consecutive EPIPE in Write
}
```

The decision to use a value or pointer receiver should be based on the nature
of the type.

```
Interface values are two-word data structures. The first word contains a pointer to
an internal table called an iTable, which contains type information about the stored
value. The iTable contains the type of value that has been stored and a list of methods
associated with the value. The second word is a pointer to the stored value. The
combination of type information and pointer binds the relationship between the two values.
```

![Alt text](/Users/Issac/mygithub/study_notes/go_in_action/5.1.png)

A value of type T only has methods declared that have a value receiver, as part of its
method set.But pointers of type T have methods declared with both value and pointer
receivers, as part of its method set.

If you implement an interface using a pointer receiver, then only pointers of that type
implement the interface. If you implement an interface using a value receiver, then
both values and pointers of that type implement the interface.

```
Values      Methods Receivers
-----------------------------------------------
T           (t T)
*T          (t T) and (t *T)

Methods Receivers   Values
-----------------------------------------------
(t T)               T and *T
(t *T)              *T
```

***why***: it’s not always possible to get the address of a value.

#### 5.6 Exporting and unexporting identifiers

```
name the package the same as the folder the code is in.

It’s a convention in Go to give factory functions the name of New.
```

### 6 Concurrency
```
When a goroutine makes a blocking syscall, the schedular will
detach the thread from the processor and create a new thread
to service that processor

Then the scheduler will choose another goroutine from the local run queue for execution.
Once the syscall returns, the goroutine is placed back into a local run queue,
and the thread is put aside for future use.

If a goroutine needs to make a network I/O call, the process is a bit different. In
this case, the goroutine is detached from the logical processor and moved to the runtime
integrated network poller

There’s no restriction built into the scheduler for the number of logical processors
that can be created.

But the runtime limits each program to a maximum of 10,000 threads by default.
This value can be changed by calling the SetMaxThreads function from the
runtime/debug package.
```

![Alt text](/Users/Issac/mygithub/study_notes/go_in_action/6.2.png)


#### 6.3 Race conditions
```
go build -race
```

#### 6.4 Locking shared resources

##### 6.4.1 Atomic functions

```
import (
    "sync/atomic"
)

atomic.AddInt64(&counter, 1)

atomic.StoreInt64(&shutdown, 1)
atomic.LoadInt64(&shutdown, 1)
```

```
import "sync"

var {
    mutex sync.Mutex
}

mutext.Lock();
mutext.Unlock();
```

#### 6.5 channel
An unbuffered channel: require both a sending and receiving goroutine to
be ready at the same instant before any send or receive operation can complete.

When a channel is closed, goroutines can still perform receives on the 
channel but can no longer send on the channel.

### 7 Concurrency patterns
#### 7.1 Runner
The default case turns the attempt(select statement) to receive on the interrupt
channel into a nonblocking call.

#### 7.2 Work
Using an unbuffered channel allows the user to know when the pool is performing
the work, and the channel pushes back when it can’t accept any more work because
it’s busy.
### 8 Standard library
#### 8.1 Logging
1. iota instructs the compiler to duplicate the expression for every
constant until the block ends or an assignment statement is found.
2. Another function of the iota keyword is that the value of iota for
each preceding constant gets incremented by 1, with an initial value of 0.

#### 8.2 Decode and Encode
```
err := json.Unmarshal([]byte(JSON), &c)
```
