var Customer = /** @class */ (function () {
    function Customer(theFirst, theLast) {
        if (theFirst === void 0) { theFirst = ''; }
        if (theLast === void 0) { theLast = ''; }
        this._firstName = theFirst;
        this._lastName = theLast;
    }
    Object.defineProperty(Customer.prototype, "lastName", {
        get: function () {
            return this._lastName;
        },
        set: function (value) {
            this._lastName = value;
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(Customer.prototype, "firstName", {
        get: function () {
            return this._firstName;
        },
        set: function (value) {
            this._firstName = value;
        },
        enumerable: false,
        configurable: true
    });
    return Customer;
}());
// lett's create an instance of the Customer class
var myCustomer = new Customer("Susan", "Public");
console.log(myCustomer.firstName);
console.log(myCustomer.lastName);
