export default function Hello(props: { name?: string }) {
  console.log('Hello component', props.name);
  if (props.name) {
    console.log('Saying hello to', props.name);
    return <h1>Hello, {props.name}!</h1>;
  } else {
    console.log('Saying hello to Stranger');
    return <span>Hey, Stranger!</span>;
  }
}
