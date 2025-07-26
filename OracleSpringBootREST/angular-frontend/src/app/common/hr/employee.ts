export class Employee {
  constructor(
    public id: number | string | null, // Allow string or null for testing
    public firstName: string | null,
    public lastName: string | null,
    public email: string | null,
    public phone: string | null,
    public hireDate: string | null,
    public jobId: string | null
  ) {}
}
