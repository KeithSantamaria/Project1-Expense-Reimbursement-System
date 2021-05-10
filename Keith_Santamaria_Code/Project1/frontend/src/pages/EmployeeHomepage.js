import {useEffect} from 'react';
import { useLocation } from 'react-router-dom';


const EmployeeHomepage = (props) => {
	const location = useLocation();

	useEffect( () => {
		console.log("locationState");
		console.log(location.state);
	})

	return (
		<div>
			<p>This is the Employee Home Page!</p>

		</div>
	)
}

export default EmployeeHomepage; 

//			<p>Welcome {location.state.userData.username }</p>