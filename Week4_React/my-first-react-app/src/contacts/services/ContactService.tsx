import Contact from '../models/Contact';
import * as contactsRoll from '../contacts.json';
class ContactService {
  private _contacts: Contact[] = contactsRoll.contacts;

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

  deleteContact(id: number): void {
    this._contacts = this._contacts.filter((c) => c.id !== id);
  }
}

const contactService = new ContactService();

export default contactService;
