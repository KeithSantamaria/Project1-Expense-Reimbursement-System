import React from 'react';
import { Counter } from './features/counter/Counter';
import './App.css';
import CounterPage from './CounterPage';

function App() {
  return (
    <div className="App">
      <Counter />
      <Counter />
      <CounterPage />
    </div>
  );
}

export default App;
