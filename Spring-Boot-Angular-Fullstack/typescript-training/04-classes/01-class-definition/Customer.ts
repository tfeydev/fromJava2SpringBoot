class Customer {

    firstName: string;
    lastName: string;

    constructor(theFirst: string = '', theLast: string = '') {
        this.firstName = theFirst;
        this.lastName = theLast;
    }
}

// lett's create an instance of the Customer class
let myCustomer = new Customer("Martin", "Dixon");

 

console.log(myCustomer.firstName);
console.log(myCustomer.lastName);
