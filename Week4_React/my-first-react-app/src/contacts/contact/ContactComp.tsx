import React from 'react';
import Contact from '../models/Contact';
import { Link } from 'react-router-dom';
import './ContactComp.css';
import { TrashIcon } from '@primer/octicons-react';
import withButton from '../../hocs/withButton';

interface ContactProps {
  contact: Contact;
  onDelete: (contact: Contact) => void;
}

const TrashButton = withButton(TrashIcon);

const ContactComp: React.FC<ContactProps> = ({ contact, onDelete }) => {
  const { name: contactName, telephone } = contact;
  return (
    <div className="contact-card">
      <section className="contact-card-header">
        <Link to={`/contacts/${contact.id}`}>{contactName}</Link>
      </section>
      <section className="contact-card-body">
        <span className="contact-info">
          <label>Phone:</label>
          <a href={`tel:+1${telephone}`}>{telephone}</a>
        </span>
        <span className="contact-info">
          <label>Email:</label>
          <a href={`mailto:${contact.email}`}>{contact.email}</a>
        </span>
      </section>
      <section className="contact-card-footer">
        <TrashButton onClick={() => onDelete(contact)} />
      </section>
    </div>
  );
};

export default ContactComp;
