const Counter = ({ count, onIncrement }) => {
  return (
    <div>
      <label>Value: {count}</label>
      <button onClick={() => onIncrement()}>Increment</button>
    </div>
  );
};

export default Counter;
