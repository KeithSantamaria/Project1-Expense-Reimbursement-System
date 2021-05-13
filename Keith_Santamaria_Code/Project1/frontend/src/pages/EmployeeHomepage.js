import {useEffect, useState} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

import {logout} from '../helperfunctions/logout';
import ReimbursementForm from '../forms/ReimbursementForm';

const EmployeeHomepage = (props) => {
	const [editUserInfoFlag, setEditUserInfoFlag] = useState(false);
	const [reimbursementCreateFlag, setReimbursementFlag] = useState(false);
	const [pendingViewFlag, setPendingViewFlag] = useState(false);
	const [resolvedViewFlag, setResolvedViewFlag] = useState(false);
	const [respData, setRespData] = useState(null);


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
		if(location.state === undefined){
			return (
				<div>No User Found</div>
			)
		}

		if (editUserInfoFlag === true){
			return(
				<div>
					<h3>Editing Profile</h3>
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
					<h3>Current Employee Information</h3>
					<p>Employee ID : {location.state.userData._id} </p>
					<p>Username : {location.state.userData.username} </p>
				</div>
			);
		}

	};

	const RenderPendingRequestView = () => {
		if(pendingViewFlag){
			const data = {
				_id: location.state.userData._id,
				username: location.state.userData.username
			}
			return (
				<div>
					<h3>Viewing Pending Requests</h3>
				</div>
			)
		}
		else{
			return <div></div>
		}
	}

	const RenderResolvedRequestView = () => {
		if(resolvedViewFlag){
			return(
				<div>
					<h3>Viewing Resolved Requests</h3>
				</div>
			)
		}
		else{
			return <div></div>
		}
	}

	const RenderReimbursementForm = () =>{
		if(reimbursementCreateFlag === true){
			return <ReimbursementForm userData = {location.state.userData} closePage = {setReimbursementFlag} closePageFlag = {reimbursementCreateFlag} />
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
			<button onClick ={() => {setPendingViewFlag(!pendingViewFlag)}}>View Pending Requests</button>
			<button onClick ={() => {setResolvedViewFlag(!resolvedViewFlag)}}>View Resolved Requests</button>
			<button  onClick= {() => {logout(history)}} > LogOut</button>
			<RenderEmployeeInformation/>
			<RenderReimbursementForm/>
			<RenderPendingRequestView/>
			<RenderResolvedRequestView/>
		</div>
	)
}

export default EmployeeHomepage; 