import React, { useEffect, useState } from 'react';

const MyForm = () => {
  const [formData, setFormData] = useState({
    name: 'John Doe',
    email: '',
    password: '',
  });

  const formSchema = {
    name: {
      required: true,
      max: 20,
      min: 3,
    },
  };

  const updateFormData = (event: any) => {
    const fieldName = event.target.name;
    const value = event.target.value;
    console.log(`updated ${fieldName} with value ${value}`);
    setFormData({ ...formData, [fieldName]: value });
  };

  useEffect(() => console.log(formData));
  useEffect(() => {
    fetch('http://localhost:8080/java-web/api/hello')
      .then((resp) => resp.json())
      .then((json) => console.log(json));
  }, []);

  const submitForm = (event: any) => {
    event.preventDefault();
    console.log(formData);
  };

  return (
    <React.Fragment>
      <form>
        <div>
          <input
            type="text"
            value={formData.name}
            onChange={updateFormData}
            name="name"
          />
        </div>
        <div>
          <input
            type="email"
            value={formData.email}
            onChange={updateFormData}
            name="email"
          />
        </div>
        <div>
          <input
            type="password"
            value={formData.password}
            onChange={updateFormData}
            name="password"
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

export default MyForm;
