class Customer {

    private _firstName: string;
    private _lastName: string;

    constructor(theFirst: string = '', theLast: string = '') {
        this._firstName = theFirst;
        this._lastName = theLast;
    }

    public get lastName(): string {
        return this._lastName;
    }
    public set lastName(value: string) {
        this._lastName = value;
    }

    public get firstName(): string {
        return this._firstName;
    }

    public set firstName(value: string) {
        this._firstName = value;
    }

}

// lett's create an instance of the Customer class
let myCustomer = new Customer("Susan", "Public");

console.log(myCustomer.firstName);
console.log(myCustomer.lastName);
