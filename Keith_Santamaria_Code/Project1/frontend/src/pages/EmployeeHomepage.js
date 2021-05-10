import {useEffect} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';


const EmployeeHomepage = (props) => {
	const location = useLocation();
	const history = useHistory();

	useEffect( () => {
		console.log("locationState");
		console.log(location.state);
		if (location.state === undefined){
			alert("Access Denied: redirecting to login");
			history.push("/")
		}
	},[location,history]);

	return (
		<div>
			<p>This is the Employee Home Page!</p>

		</div>
	)
}

export default EmployeeHomepage; 

//			<p>Welcome {location.state.userData.username }</p>