var Customer = /** @class */ (function () {
    function Customer(theFirst, theLast) {
        if (theFirst === void 0) { theFirst = ''; }
        if (theLast === void 0) { theLast = ''; }
        this.firstName = theFirst;
        this.lastName = theLast;
    }
    return Customer;
}());
// lett's create an instance of the Customer class
var myCustomer = new Customer("Martin", "Dixon");
console.log(myCustomer.firstName);
console.log(myCustomer.lastName);
