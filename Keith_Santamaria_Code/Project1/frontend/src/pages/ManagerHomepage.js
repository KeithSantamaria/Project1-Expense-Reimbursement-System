import {useState, useEffect} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

import {logout} from '../helperfunctions/logout';
import {postAllPendingRequests, postUpdateStatus} from '../helperfunctions/postFunctions';

const allPending = "allPending";
const allResolved = "allResolved";
const pickEmployee = "pickEmployee";
const approve= "APPROVE";
const deny= "DENY";

const ManagerHomePage = (props) => {
	const location = useLocation();
	const history = useHistory();

	const [requestFilterFlag, setResquestFilterFlag] = useState(false);
	const [currentFilter, setCurrentFilter] = useState("");
	const [respData, setRespData] = useState("");
	const [pendingRequests, setPendingRequests] = useState([]);
	const [resolvedRequests, setResolvedRequests] = useState([]);

	useEffect( () => {
		console.log("locationState");
		console.log(location.state);
		if (location.state === undefined){
			alert("Access Denied: redirecting to login");
			history.replace("/")
		}
	},[location,history]);

	useEffect(() => {
		if(respData !== null){
			setPendingRequests(respData.pendingRequests);
			setResolvedRequests(respData.resolvedRequests);
			console.log(respData);
		}
	},[respData])

	useEffect(() => {
		postAllPendingRequests({},setRespData);
	},[location])

	useEffect(() => {
		if(!requestFilterFlag){
			setCurrentFilter("");
		}
	},[requestFilterFlag])


	const handleStatusChange = (request, status) => {
		const data = {
			_id : request._id.$oid,
			currentStatus : status,
			approvedByName : location.state.userData.username
		};
		postUpdateStatus(data);
		alert("Sucessfully Reviewed Request!");
		window.location.reload(true);

	}

	const RenderRequests = () => {
		if(currentFilter === ""){
			return null;
		}
		if(currentFilter === allPending){
			return(
				<div>
					<h3>Viewing Pending Requests</h3>
					<table border="1">
						<tbody>
							<tr>
								<th>No.</th>
								<th>ID</th>
								<th>Owner</th>
								<th>Reason</th>
								<th>Amount</th>
								<th>Approve/Deny</th>
							</tr>
							{
								pendingRequests.map((request,count) => {
									return(
										<tr key = {request._id.$oid}>
											<td>{count + 1}</td>
											<td>{request._id.$oid}</td>
											<td>{request.username}</td>
											<td>{request.reason}</td>
											<td>${request.amount}</td>
											<td>
												<button onClick = {() => {handleStatusChange(request,approve)}}>Approve</button>
												<button onClick = {() => {handleStatusChange(request,deny)}}>Deny</button>
											</td>
										</tr>
									)
								})
							}
						</tbody>
					</table>
				</div>
			)
		} 
		if(currentFilter === allResolved){
			return (
				<div>
					<h3>Viewing Resolved Requests</h3>
					<table border="1">
						<tbody>
							<tr>
								<th>No.</th>
								<th>ID</th>
								<th>Owner</th>
								<th>Reason</th>
								<th>Amount</th>
								<th>Status</th>
								<th>Reviewed By</th>
							</tr>
							{
								resolvedRequests.map((request,count) => {
									return(
										<tr key = {request._id.$oid}>
											<td>{count + 1}</td>
											<td>{request._id.$oid}</td>
											<td>{request.username}</td>
											<td>{request.reason}</td>
											<td>${request.amount}</td>
											<td>{request.currentStatus}</td>
											<td>{request.approvedByName}</td>
										</tr>
									)
								})
							}
						</tbody>
					</table>
				</div>
			);
		}
		if(currentFilter === pickEmployee){
			return null; 
		}
	}

	const RenderFilterButtons = () => {
		if(requestFilterFlag){
			return(
				<div>
					<button onClick = {() => {setCurrentFilter(allPending)}}>View All Pending Requests</button>
					<button onClick = {() => {setCurrentFilter(allResolved)}}>View All Resolved Requests</button>
					<button onClick = {() => {setCurrentFilter(pickEmployee)}}>View All requests from an Employee</button>
					<button onClick = {() => {setCurrentFilter("")}}>Exit</button>
				</div>
			);
		}
		else{
			return(
				<div></div>
			)
		}
	}

	return (
		<div>
			<h2>This is the Manager Home Page!</h2>
			<button onClick = { () => {setResquestFilterFlag(!requestFilterFlag)}}>View Requests</button>
			<button>View Employees</button>
			<button>Create Employee</button>
			<button  onClick= {() => {logout(history)}} > LogOut</button>
			<RenderFilterButtons/>
			<RenderRequests/>
		</div>
	)
}

export default ManagerHomePage; 