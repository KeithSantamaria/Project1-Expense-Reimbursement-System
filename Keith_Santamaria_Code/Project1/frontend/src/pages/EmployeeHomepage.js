import {useEffect} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

import {logout} from '../helperfunctions/logout';

const EmployeeHomepage = (props) => {
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
			<h2>This is the Employee Home Page!</h2>
			<button  onClick= {() => {logout(history)}} > LogOut</button>
		</div>
	)
}

export default EmployeeHomepage; 

//			<p>Welcome {location.state.userData.username }</p>