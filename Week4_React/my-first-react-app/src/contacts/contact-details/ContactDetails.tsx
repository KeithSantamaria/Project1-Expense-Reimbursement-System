import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import contactService from '../services/ContactService';
import Contact from '../models/Contact';

const ContactDetails = () => {
  //@ts-ignore
  const { contactId } = useParams();
  const [contact, setContact] = useState<Contact>();

  useEffect(() => {
    setTimeout(() => setContact(contactService.getContact(+contactId)), 2500);
  }, [contactId, setContact]);

  return (
    <div>
      {contact ? (
        <p>Viewing Contact {contact.name}</p>
      ) : (
        <p>Loading contact {contactId}</p>
      )}
    </div>
  );
};

export default ContactDetails;
