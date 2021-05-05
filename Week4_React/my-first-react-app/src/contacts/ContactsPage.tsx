import React, { useEffect, useState } from 'react';
import ContactComp from './contact/ContactComp';
import Contact from './models/Contact';
import ContactDetails from './contact-details/ContactDetails';
import { Route, useRouteMatch } from 'react-router-dom';
import contactService from './services/ContactService';

const ContactsPage = () => {
  const { url, path } = useRouteMatch();
  console.log(url);
  console.log(path);
  const [fontColor, setFontColor] = useState('red');
  const [contacts, setContacts] = useState<Contact[]>([]);

  useEffect(() => {
    setTimeout(() => setContacts(contactService.contacts), 5000);
  }, []);

  return (
    <div>
      <span style={{ color: fontColor }}>My Contacts</span>
      {contacts.map((contact: Contact) =>
        contact.active ? (
          <ContactComp key={contact.id} contact={contact} />
        ) : null
      )}
      <Route path={`${url}/:contactId`} component={ContactDetails} />
    </div>
  );
};

export default ContactsPage;
