import { render, unmountComponentAtNode } from 'react-dom';
import { act } from 'react-dom/test-utils';
import ContactComp from './ContactComp';
import Contact from '../models/Contact';

let container: any = null;
beforeEach(() => {
  container = document.createElement('div');
  document.body.appendChild(container);
});

afterEach(() => {
  unmountComponentAtNode(container);
  container.remove();
  container = null;
});

it('should render the component', () => {
  const contact: Contact = {
    id: 1,
    name: 'Test Contact',
    email: 'test.email@gmail.com',
    telephone: '123-456-789',
    active: true,
  };
  act(() => {
    render(<ContactComp contact={contact} onDelete={() => {}} />, container);
  });
});
