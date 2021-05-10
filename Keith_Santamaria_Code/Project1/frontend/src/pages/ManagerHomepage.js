import {useEffect} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

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
		<h2>This is the Manager Home Page!</h2>
	)
}

export default ManagerHomePage; 