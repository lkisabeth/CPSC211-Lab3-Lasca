public enum BoardSpace {
    // This is using an enum.  If you're not sure what that is, ask me about it.
    Available,
    Red { public String toString () { return "Red";}},
    Black { public String toString () { return "Black";}}
}