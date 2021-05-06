import logo from './logo.svg';
import './App.css';
import Counter from './Counter';
import { useState } from 'react';

function App() {
  const [count, setCount] = useState(0);
  const increment = () => {
    setCount(count + 1);
  };
  return <Counter count={count} onIncrement={increment} />;
}

export default App;
