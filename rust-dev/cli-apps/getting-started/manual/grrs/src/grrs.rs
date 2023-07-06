
// Getting the arguments directly from the Operating System
let pattern = std::env::args().nth(1).expect("no pattern given");
let path = std::env::args().nth(2).expect("no path given");

// CLI arguments as data type 
struct Cli {
    pattern: String,
    path: std::path::PathBuf,
}
// Now, getting the arguments from OS, then storing them in custom types using a Struct
let pattern = std::env::args().nth(1).expect("no pattern given");
let path = std::env::args().nth(2).expect("no path given");
// the custom type storing the retrieved arguments
let args = Cli {
    pattern: pattern,
    path: std::path::PathBuf::from(path),
};

fn grrs() {

}
