import { useEffect, useState } from 'react';
import ContactComp from './contact/ContactComp';
import Contact from './models/Contact';
import { useRouteMatch } from 'react-router-dom';
import contactService from './services/ContactService';
import './ContactsPage.css';
import withModal from '../hocs/withModal';

export const NewContactForm = () => (
  <div style={{ height: '100%' }}>
    <form className="form">
      <div className="form-field">
        <label>Contact Name:</label>
        <input type="text" name="name" />
      </div>
      <div className="form-field">
        <label>Contact Email:</label>
        <input type="email" name="email" />
      </div>
      <div className="form-field">
        <label>Contact Phone:</label>
        <input type="tel" name="telephone" />
      </div>
      <div className="form-actions">
        <button type="button" onClick={(e) => e.preventDefault()}>
          Save
        </button>
        <button type="reset">Clear</button>
      </div>
    </form>
  </div>
);
const NewContactModal = withModal(NewContactForm);
const ContactsPage = () => {
  const { url, path } = useRouteMatch();
  console.log(url);
  console.log(path);
  const [contacts, setContacts] = useState<Contact[]>([]);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    setTimeout(() => setContacts(contactService.contacts), 2500);
  }, []);

  const handleDelete = (contact: Contact) => {
    contactService.deleteContact(contact.id);
    setContacts(contactService.contacts);
  };

  return (
    <div>
      {showModal ? <NewContactModal /> : null}
      <button onClick={() => setShowModal(true)}>+</button>
      <div className="container">
        {contacts.map((contact: Contact) =>
          contact.active ? (
            <ContactComp
              key={contact.id}
              contact={contact}
              onDelete={handleDelete}
            />
          ) : null
        )}
      </div>
      {/* <Route path={`${url}/:contactId`} component={ContactDetails} /> */}
    </div>
  );
};

export default ContactsPage;
