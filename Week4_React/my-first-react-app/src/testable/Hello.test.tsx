import { render, screen } from '@testing-library/react';
import { unmountComponentAtNode } from 'react-dom';
import { act } from 'react-dom/test-utils';
import Hello from './Hello';

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

it('should render without a name', () => {
  //   act(() => {
  //     render(<Hello />, container);
  //   });
  //   console.log(container.querySelector('span'));
  //   expect(container.textContent).toBe('Hey, Stranger!');
  render(<Hello />);
  const span = screen.getByText('Hey, Stranger!');
  expect(span).toBeInTheDocument();
});

it('should render with a name', () => {
  //   act(() => {
  //     render(<Hello />, container);
  //   });
  //   console.log(container.querySelector('span'));
  //   expect(container.textContent).toBe('Hey, Stranger!');
  render(<Hello name="John" />);
  const h1 = screen.getByText('Hello, John!');
  expect(h1).toBeInTheDocument();
});
