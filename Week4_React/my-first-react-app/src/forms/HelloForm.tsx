import React, { useState } from 'react';
import axios from 'axios';

const HelloForm = () => {
  const [formData, setFormData] = useState({
    name: '',
  });
  const updateFormData = (event: any) => {
    setFormData({ name: event.target.value });
  };

  const submitForm = (e: any) => {
    e.preventDefault();
    axios
      .post('http://localhost:8080/java-web/api/hello', formData, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .then((resp) => console.log(resp));
    // fetch('http://localhost:8080/java-web/api/hello', {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type': 'application/json',
    //   },
    //   body: JSON.stringify(formData),
    // }).then((response) => {
    //   switch (response.status) {
    //     case 201:
    //       console.log('Name was added successfully');
    //       break;
    //     case 400:
    //       console.log('There was something wrong with the request format');
    //       break;
    //     default:
    //       console.log('Something unexpected happened');
    //   }
    // });
  };

  return (
    <React.Fragment>
      <form>
        <div>
          <input
            type="text"
            value={formData.name}
            onChange={updateFormData}
            placeholder="Enter a name"
            name="name"
          />
        </div>
        <div>
          <button type="submit" onClick={submitForm}>
            Submit
          </button>
        </div>
      </form>
    </React.Fragment>
  );
};
export default HelloForm;
