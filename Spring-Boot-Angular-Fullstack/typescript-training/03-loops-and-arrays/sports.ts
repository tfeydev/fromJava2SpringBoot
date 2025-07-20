let sportsOne: string[] = ["Golf", "Cricket", "Tennis", "Swimming"];

// Let's use the simplied for loop
for (let tempSport of sportsOne) {  

    if (tempSport === "Cricket") {
        console.log(tempSport + " <-- My favorite sport -->");
    }
    else {
        console.log(tempSport);
    }
}
