// fn main() {
   //  println!("Hello, world!");
// }

use ferris_says::say; // using the ferris_says dependencies
use std::io::{stdout, BufWriter};

fn main() {
    let stdout = stdout();
    let message  = String::from("Hello fellow Rustaceans!");
    let width = message.chars().count();

    let mut writer = BufWriter::new(stdout.lock()); // mut explicitly tells us that the variable
                                                    // writer is mutable
    say(message.as_bytes(), width, &mut writer).unwrap(); // the say function borrows the mutable
                                                          // variable writer wich is denoted by &mut 
}

// Assignment 1
// Understand the ferris_say library and write a couple of rust apps demonstrating its usefulness


