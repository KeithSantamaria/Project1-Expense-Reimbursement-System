import {useState} from 'react';



import LoginForm from '../forms/LoginForm';

const manager = "manager";
const employee = "employee";

const Login = () => {
	const [loginAsFlag, setLoginAsFlag] = useState(employee);

	const RenderLoginMessage = () => {
		let jsx = <p></p>;
		if (loginAsFlag === employee){
			jsx = <h3>Logging in as an employee</h3>
		}
		if (loginAsFlag === manager){
			jsx = <h3>Logging in as a manager</h3>
		}
		return jsx;
	}

	return (
		<div>
			<h2>Login in as: </h2>
			<button onClick = {() => setLoginAsFlag(employee)}>Employee</button>
			<button onClick = {() => setLoginAsFlag(manager)}>Manager</button>
			<RenderLoginMessage/>
			<LoginForm loginAs = {loginAsFlag}/>
		</div>
	)
}

export default Login; 