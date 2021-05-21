import axios from 'axios';

export const TestGet = () => {
  axios.get("http://localhost:6969")
  .then (res => {
    setMsg(res.data);
  })
  return <p>{msg}</p>
}