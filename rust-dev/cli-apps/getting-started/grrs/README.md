
# Parsing command-line arguments
> <https://rust-cli.github.io/book/tutorial/cli-args.html>

A typical invocation of our CLI tool will look like this:  
```bash
grrs foobar test.txt
```
We expect our program to look at `test.txt` and print out the lines that contain `foobar`. But how do we get these two values?

The text after the name of the program is often called the "command-line arguments", or "command-line flags" (especially when they look like `--this`).  
Internally, the operating system usually represents them as a list of strings - roughly speaking, they get separated by spaces.

There are many ways to think about these arguments, and how to parse them into something more easy to work with. You will also need to tell the users of  
your program which *arguments* they need to give and in which *format* they are expected.

## Getting the arguments

The standard library contains the function `std::env::args()` that gives you an iterator of the given arguments. The first entry (at index 0) will  
be the name of your program was called as (e.g., `grrs`), the ones that follow are what the user wrote afterwards.

Getting the raw arguments this way is quite easy (in file `src/main.rs`, after `fn main() {`):
```rust
let pattern = std::env::args().nth(1).expect("no pattern given");
let path = std::env::args().nth(2).expect("no path given")
```

## CLI arguments as data type

Instead of thinking about them as a bunch of text, it often pays off to think of CLI arguments as a `custom data type` that represents the inputs to  
your program.

Look at `grrs foobar test.txt`: There are two arguments, first the `pattern` (the string to look for), anf then the `path` (the file to look in).

What more can we say about them? (characteristics) Well, for a start, both are required. We haven't talked about any default values, so we excpect our users to  
always provide two values. Furthermore, we can say a bit about their types: The pattern is expected to be a string, while the second argument is expected to be  
a path to a file.

In Rust, it is common to structure program programs around the data they handle, so this way of looking at CLI arguments fits well. Let's start with this  
(in file `src/main.rs`, before `fn main() {`):  
```rust
struct Cli {
    pattern: String,
    path: std::path::PatHBuf,
}
```

This defines a new structure (a `struct`) that has two fields to store data in: `pattern`, and `path`.  
> **Note**: `PathBuf` is like a `String` but for file system paths that works cross-platform. :)

Now, we still need to get the actual arguments our program got into this form. One option would be to manually parse the list of strings we get from the  
operating system and build the structure ourselves. It would look something like this:  
```rust
let pattern = std::env::args().nth(1).expect("no pattern given");
let path = std::env::args().nth(2).expect("no path given");
let args = cli {
    pattern: pattern,
    path: std::path::PathBuf::from(path),
};
```
This works, but it's not very convenient. How would you deal with the requirement to support `--pattern="foo"` or `--pattern "foo"`? How would you  
implement `--help`?

## Parsing CLI arguments with Clap

A much nicer way is to use of the many available libraries. The most popular library for parsing command-line arguments is called `clap`. It has the  
funtionality you'd expect, including support for sub-commands, shell completions <https://docs.rs/clap_complete/>, and great help messages.

...
