import {useEffect, useState} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

import {logout} from '../helperfunctions/logout';
import {postEmployeePendingRequests} from '../helperfunctions/postFunctions';
import ReimbursementForm from '../forms/ReimbursementForm';

const EmployeeHomepage = (props) => {
	const [editUserInfoFlag, setEditUserInfoFlag] = useState(false);
	const [reimbursementCreateFlag, setReimbursementFlag] = useState(false);
	const [pendingViewFlag, setPendingViewFlag] = useState(false);
	const [resolvedViewFlag, setResolvedViewFlag] = useState(false);
	const [respData, setRespData] = useState(null);

	const [pendingRequests, setPendingRequests] = useState([]);

	const location = useLocation();
	const history = useHistory();

	useEffect( () => {
		// console.log("locationState");
		// console.log(location.state);
		if (location.state === undefined){
			alert("Access Denied: redirecting to login");
			history.replace("/")
		}
	},[location,history]);

	useEffect( () => {
		console.log(respData);
		if(respData !== null){
			setPendingRequests(respData.pendingRequests);
		}
	},[respData]);

	useEffect(() => {
		const data = {
			_id: location.state.userData._id,
			username: location.state.userData.username
		}
		postEmployeePendingRequests(data,setRespData);
	},[location]);

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
			return (
				<div>
					<h3>Viewing Pending Requests</h3>
					<table border="1">
						<tbody>
							<tr>
								<th>ID</th>
								<th>Reason</th>
								<th>Amount</th>
							</tr>
							{
								pendingRequests.map((request) => {
									return(
										<tr key = {request._id.$oid}>
											<td>{request._id.$oid}</td>
											<td>{request.reason}</td>
											<td>{request.amount}</td>
										</tr>
									)
								})
							}
						</tbody>
					</table>
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
					<table border="1">
						<tbody>
							
						</tbody>
					</table>
				</div>
			)
		}
		else{
			return <div></div>
		}
	}

	const RenderReimbursementForm = () =>{
		if(reimbursementCreateFlag === true){
			return <ReimbursementForm userData = {location.state.userData} pendingRequestsSetter = {setRespData} />
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