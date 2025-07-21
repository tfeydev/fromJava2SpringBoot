class Customer {

    constructor(private _firstName: string, private _lastName: string) { 
    }

    public get firstName(): string {
        return this._firstName;
    }

    public set firstName(value: string) {
        this._firstName = value;
    }

    public get lastName(): string {
        return this._lastName;
    }
    public set lastName(value: string) {
        this._lastName = value;
    }
    
}

// lett's create an instance of the Customer class
let myCustomer = new Customer("Susan", "Public");

console.log(myCustomer.firstName);
console.log(myCustomer.lastName);
