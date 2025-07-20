var sportsOne = ["Golf", "Cricket", "Tennis", "Swimming"];
// Let's use the simplied for loop
for (var _i = 0, sportsOne_1 = sportsOne; _i < sportsOne_1.length; _i++) {
    var tempSport = sportsOne_1[_i];
    if (tempSport === "Cricket") {
        console.log(tempSport + " <-- My favorite sport -->");
    }
    else {
        console.log(tempSport);
    }
}
