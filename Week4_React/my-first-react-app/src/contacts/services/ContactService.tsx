import Contact from '../models/Contact';

class ContactService {
  private _contacts: Contact[] = [
    {
      id: 1,
      name: 'John Duet',
      telephone: '123-456-7980',
      active: false,
    },
    {
      id: 2,
      name: 'August Duet',
      telephone: '123-456-7980',
      active: true,
    },
    {
      id: 3,
      name: 'Karen',
      telephone: '123-456-7980',
      active: true,
    },
  ];

  get contacts(): Contact[] {
    return this._contacts;
  }

  addContact(contact: Contact): number {
    const lastId = this._contacts[this._contacts.length - 1].id || 0;
    const nextId = lastId + 1;
    contact.id = nextId;
    this._contacts.push(contact);
    return nextId;
  }

  getContact(id: number): Contact | undefined {
    return this._contacts.find((c) => c.id === id);
  }
}

const contactService = new ContactService();

export default contactService;
