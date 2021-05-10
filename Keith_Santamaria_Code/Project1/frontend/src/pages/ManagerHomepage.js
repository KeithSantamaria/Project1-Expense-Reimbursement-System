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
			history.push("/")
		}
	},[location,history]);

	return (
		<p>This is the Manager Home Page!</p>
	)
}

export default ManagerHomePage; 