import React from 'react';

const Contact = (props) => {
  console.log(props.contact);
  return (
    <div className="contact-card">
      <section className="contact-card-header">{props.contact.name}</section>
      <section className="contact-card-body">
        <span className="telephone">{props.contact.telephone}</span>
      </section>
      <section className="contact-card-footer">Footer</section>
    </div>
  );
};

export default Contact;
