import {useState, useEffect} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

import {logout} from '../helperfunctions/logout';
import {postAllPendingRequests, postUpdateStatus, postListOfEmployees} from '../helperfunctions/postFunctions';

const approve= "APPROVE";
const deny= "DENY";

const ManagerHomePage = (props) => {
	const location = useLocation();
	const history = useHistory();

	const [respData, setRespData] = useState("");
	const [pendingRequests, setPendingRequests] = useState([]);
	const [resolvedRequests, setResolvedRequests] = useState([]);
	const [employeesResp, setEmployeesResp] = useState([]);
	const [employeeFlag, setEmployeeFlag] = useState(false);
	const [filter, setFilter] = useState(null);
	const [filterUsername, setFilterUsername] = useState("");

	useEffect( () => {
		console.log("locationState");
		console.log(location.state);
		if (location.state === undefined){
			alert("Access Denied: redirecting to login");
			history.replace("/")
		}
	},[location,history]);

	useEffect(() => {
		setFilter("");
		postAllPendingRequests({},setRespData);
	},[location])

	useEffect(() => {
		if(respData !== null){
			setPendingRequests(respData.pendingRequests);
			setResolvedRequests(respData.resolvedRequests);
			console.log(respData);
		}
	},[respData])

	useEffect(() => {
		console.log(employeesResp);
	},[employeesResp])

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

	const handleViewEmployees = () => {
		postListOfEmployees(setEmployeesResp);
		setEmployeeFlag(!employeeFlag);
	}

	const RenderEmployees = () => {
		if(employeeFlag && (employeesResp.employees !== undefined)){
			return (
				<div>
					<h3>All Employees</h3>
					<table border ="1">
						<tbody>
							<tr>
								<th>ID</th>
								<th>Username</th>
								<th>Role</th>
								<th>View Requests?</th>
							</tr>
							{
								employeesResp.employees.map((employee) => {
									if(employee.role === "EMPLOYEE"){
										return(
											<tr key = {employee._id.$oid}>
												<td>{employee._id.$oid}</td>
												<td>{employee.username}</td>
												<td>{employee.role}</td>
												<td>
													<button onClick = {() => {
														setFilter(employee._id.$oid); 
														setFilterUsername(employee.username);
														}}
													>
														Filter Requests to this employee
													</button>
												</td>
											</tr>
										)
									}
									if(employee.role === "MANAGER"){
										return(
											<tr key = {employee._id.$oid}>
												<td>{employee._id.$oid}</td>
												<td>{employee.username}</td>
												<td>{employee.role}</td>
												<td>N/A</td>
											</tr>
										)
									}
									return null;
								})
							}
							<tr>
								<td colSpan="4">
									<button onClick = {() => {
										setFilter("");
										setFilterUsername("");
										}}
									>No Filter</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			)
		}
		else{
			return null;
		}
	}

	const RenderRequests = () =>{
		if (pendingRequests !== undefined){
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
									if (filter === request.ownerId.$oid || filter === ""){
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
									}
									else{
										return null;
									}
								})
							}
						</tbody>
					</table>
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
									if (filter === request.ownerId.$oid || filter === ""){
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
									}
									else{
										return null;
									}
								})
							}
						</tbody>
					</table>
				</div>
			)
		}
		else{
			return null;
		}
	}

	return (
		<div>
			<h2>This is the Manager Home Page!</h2>
			<button onClick = {() => {handleViewEmployees()}}>View Employees</button>
			<button  onClick= {() => {logout(history)}} > LogOut</button>
			<h3>Current Filter: {filterUsername}</h3>
			<RenderRequests/>
			<RenderEmployees/>
		</div>
	)
}

export default ManagerHomePage; 