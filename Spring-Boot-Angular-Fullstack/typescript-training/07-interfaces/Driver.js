"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var CricketCoach_1 = require("./CricketCoach");
var Golf_Coach_1 = require("./Golf.Coach");
var myCricketCoach = new CricketCoach_1.CricketCoach();
var myGolfCoach = new Golf_Coach_1.GolfCoach();
// declare an array for coaches ... initially empty
var theCoaches = [];
// and the coaches to the array
theCoaches.push(myCricketCoach);
theCoaches.push(myGolfCoach);
for (var _i = 0, theCoaches_1 = theCoaches; _i < theCoaches_1.length; _i++) {
    var tempCoach = theCoaches_1[_i];
    console.log(tempCoach.getDailyWorkout());
}
