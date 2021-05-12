import {useEffect, useState} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

import {logout} from '../helperfunctions/logout';
import ReimbursementForm from '../forms/ReimbursementForm';

const EmployeeHomepage = (props) => {
	const [editUserInfoFlag, setEditUserInfoFlag] = useState(false);
	const [reimbursementCreateFlag, setReimbursementFlag] = useState(false);

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
						<span>Username:</span>
						<input type="text" placeholder={location.state.userData.username}></input>
					</div>
				</div>
			)
		}
		else{
			return(
				<div>
					<p>Employee ID : {location.state.userData._id} </p>
					<p>Username : {location.state.userData.username} </p>
				</div>
			);
		}

	};

	const RenderReimbursementForm = () =>{
		if(reimbursementCreateFlag === true){
			return <ReimbursementForm userData = {location.state.userData}/>
		}
		else{
			return <div></div>
		}
	}

	return (
		<div>
			<h2>Welcome to your employee account page!</h2>
			<button onClick ={() => {setEditUserInfoFlag(!editUserInfoFlag)}}>Edit Profile</button>
			<button onClick ={() => {setReimbursementFlag(!reimbursementCreateFlag)}}>Submit a new Reinbursment Request</button>
			<button>View Pending Requests</button>
			<button>View Resolved Requests</button>
			<button  onClick= {() => {logout(history)}} > LogOut</button>
			<RenderEmployeeInformation/>
			<RenderReimbursementForm/>
		</div>
	)
}

export default EmployeeHomepage; 