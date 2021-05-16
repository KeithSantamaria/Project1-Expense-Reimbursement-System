import {useEffect, useState} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

import {logout} from '../helperfunctions/logout';
import {postEmployeeRequests} from '../helperfunctions/postFunctions';
import ReimbursementForm from '../forms/ReimbursementForm';

const EmployeeHomepage = (props) => {
	const [editUserInfoFlag, setEditUserInfoFlag] = useState(false);
	const [reimbursementCreateFlag, setReimbursementFlag] = useState(false);
	const [respData, setRespData] = useState(null);

	const [pendingRequests, setPendingRequests] = useState([]);
	const [resolvedRequests, setResolvedRequests] = useState([]);
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
			setResolvedRequests(respData.resolvedRequests);
		}
	},[respData]);

	useEffect(() => {
		const data = {
			_id: location.state.userData._id,
			username: location.state.userData.username
		}
		postEmployeeRequests(data,setRespData);
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
		return (
			<div>
				<h3>Viewing Pending Requests</h3>
				<table border="1">
					<tbody>
						<tr>
							<th>No.</th>
							<th>ID</th>
							<th>Reason</th>
							<th>Amount</th>
						</tr>
						{
							pendingRequests.map((request,count) => {
								return(
									<tr key = {request._id.$oid}>
										<td>{count + 1 }</td>
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

	const RenderResolvedRequestView = () => {
		return(
			<div>
				<h3>Viewing Resolved Requests</h3>
				<table border="1">
					<tbody>
						<tr>
							<th>No.</th>
							<th>ID</th>
							<th>Reason</th>
							<th>Amount</th>
							<th>Status</th>
						</tr>
						{
							resolvedRequests.map((request,count) => {
								return(
									<tr key = {request._id.$oid}>
										<td>{count + 1 }</td>
										<td>{request._id.$oid}</td>
										<td>{request.reason}</td>
										<td>{request.amount}</td>
										<td>{request.currentStatus}</td>
									</tr>
								)
							})
						}
					</tbody>
				</table>
			</div>
		)
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
			<button  onClick= {() => {logout(history)}} > LogOut</button>
			<RenderEmployeeInformation/>
			<RenderReimbursementForm/>
			<RenderPendingRequestView/>
			<RenderResolvedRequestView/>
		</div>
	)
}

export default EmployeeHomepage; 