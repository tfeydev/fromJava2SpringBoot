var found = true;
var grade = 86;
var firstName = "John";
var lastName = "Doe";
// let's break it
/*
found = 0;
grade = "A";
firstName = false
*/
console.log(found);
console.log("The grade is " + grade);
console.log("Hi " + firstName + " " + lastName);
// use template Strings
console.log("Hi ".concat(firstName, " ").concat(lastName));
