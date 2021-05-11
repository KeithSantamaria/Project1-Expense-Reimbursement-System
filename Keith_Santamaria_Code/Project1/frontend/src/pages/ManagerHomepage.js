import {useEffect} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

import {logout} from '../helperfunctions/logout';

const ManagerHomePage = (props) => {
	const location = useLocation();
	const history = useHistory();

	useEffect( () => {
		console.log("locationState");
		console.log(location.state);
		if (location.state === undefined){
			alert("Access Denied: redirecting to login");
			history.replace("/")
		}
	},[location,history]);

	return (
		<div>
			<h2>This is the Manager Home Page!</h2>
			<button>View Requests</button>
			<button>View Employees</button>
			<button>Create Employee</button>
			<button  onClick= {() => {logout(history)}} > LogOut</button>
		</div>
	)
}

export default ManagerHomePage; 