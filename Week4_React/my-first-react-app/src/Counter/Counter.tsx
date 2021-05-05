import React, { SyntheticEvent, useEffect, useState } from 'react';

const Counter = () => {
  const [counter, setCounter] = useState(0);

  useEffect(() => {
    document.title = `You have clicked ${counter} times`;
    return function () {
      // cleanup
      console.log('this gets called before unmounting');
    };
  });

  function handleIncrement(msg: string, event: SyntheticEvent) {
    console.log(event);
    console.log(msg);
    setCounter(counter + 1);
  }

  return (
    <div>
      <span>{counter}</span>
      <button onClick={(e) => handleIncrement('hello', e)}>Increment</button>
    </div>
  );
};
export default Counter;
