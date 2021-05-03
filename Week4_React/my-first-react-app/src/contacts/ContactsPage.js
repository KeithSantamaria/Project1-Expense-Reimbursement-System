import React from 'react';
import Contact from './contact/Contact';

export default class ContactsPage extends React.Component {
  constructor() {
    super();
    this.state = {
      contacts: [
        {
          id: 1,
          name: 'John Duet',
          telephone: '123-456-7980',
        },
        {
          id: 2,
          name: 'August Duet',
          telephone: '123-456-7980',
        },
        {
          id: 3,
          name: 'Karen',
          telephone: '123-456-7980',
        },
      ],
    };
  }

  render() {
    return (
      <div>
        {this.state.contacts.map((contact) => (
          <Contact key={contact.id} contact={contact} />
        ))}
      </div>
    );
  }
}
