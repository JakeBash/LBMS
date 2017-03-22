# SWEN 262 Group C LBMS

* This is our team's repository for Engineering of Software Subsystems (SWEN 262) at the Rochester Institute of Technology

* Our team was tasked with the responsibility to design and implement the Library Book Management System (LBMS). The LBMS is Book Worm Library’s (BWL) system for providing book information to users, tracking library visitor statistics for a library statistics report, tracking borrowedchecked out books, and allowing the library inventory to be updated. It is the server-side system that provides an API used by client-side interfaces that BWL employees use.

DISCLAIMERS AND INFO ON COMMANDS
* REMEMBER YOUR USER ID, Copy to clipboard, you wont get it back...
* The program is VERY FRAGILE. Be careful on imputting commands
* You must search the library store before attempting to buy
* If the command is supposed to have {}, then you must use {} for that field
even if that field is to be replaced by an astrix
    - ex: info,\*,{\*}; and not info,\*,\*;
    - The latter will crash the program
* Reports will generate for the beginning of time. Selecting a time interval
will be implemented in a later release
* When borrowing, the ID is the ISBN and use squiggly brackets
    - ex: borrow,3333398140,{9780762755349};
* Many of the commands to not return a response upon error. This will
also be implemented in a later release
* It wasn't listed as a required command but Shutdown is a thing
    - the command is "shutdown;"
    - This hevily relies on Java's Serializable
* Partial commands Should work.