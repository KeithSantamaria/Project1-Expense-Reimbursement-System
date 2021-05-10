import {useEffect, useState} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

import {logout} from '../helperfunctions/logout';

const EmployeeHomepage = (props) => {
	const [editUserInfoFlag, setEditUserInfoFlag] = useState(false);

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

	const RenderEmployeeInformation = () => {

		if (editUserInfoFlag === true){
			return(
				<div>
					<p>Employee ID : {location.state.userData._id} </p>
					<div>
						<p>Username:</p>
						<input type="text" ></input>
					</div>

				</div>
			)
		}

		if ( editUserInfoFlag === false){
			return(
				<div>
					<p>Employee ID : {location.state.userData._id} </p>
					<p>Username : {location.state.userData.username} </p>
				</div>
			);
		}
		

		return <div></div>
	};

	return (
		<div>
			<h2>Welcome to your employee account page!</h2>
			<RenderEmployeeInformation/>
			<button onClick ={() => {setEditUserInfoFlag(!editUserInfoFlag)}}>Edit Profile</button>
			<button>Submit a new Reinbursment Request</button>
			<button>View Pending Requests</button>
			<button>View Resolved Requests</button>
			<button  onClick= {() => {logout(history)}} > LogOut</button>
		</div>
	)
}

export default EmployeeHomepage; 

//			<p>Welcome {location.state.userData.username }</p>