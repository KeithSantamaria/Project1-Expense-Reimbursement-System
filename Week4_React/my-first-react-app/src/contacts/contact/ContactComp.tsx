import React from 'react';
import Contact from '../models/Contact';
import { Link, Route } from 'react-router-dom';
import ContactDetails from '../contact-details/ContactDetails';

interface ContactProps {
  contact: Contact;
}

const ContactComp: React.FC<ContactProps> = ({ contact }) => {
  const { name: contactName, telephone } = contact;
  return (
    <div className="contact-card">
      <section className="contact-card-header">
        <Link to={`/contacts/${contact.id}`}>{contactName}</Link>
      </section>
      <section className="contact-card-body">
        <span className="telephone">{telephone}</span>
      </section>
      <section className="contact-card-footer">Footer</section>
    </div>
  );
};

export default ContactComp;
