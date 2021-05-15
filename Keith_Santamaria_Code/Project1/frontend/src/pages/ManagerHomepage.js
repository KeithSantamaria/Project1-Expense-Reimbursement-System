import {useState, useEffect} from 'react';
import { useLocation } from 'react-router-dom';
import {useHistory} from 'react-router-dom';

import {logout} from '../helperfunctions/logout';

const allPending = "allPending";
const allResolved = "allResolved";
const pickEmployee = "pickEmployee";

const ManagerHomePage = (props) => {
	const location = useLocation();
	const history = useHistory();

	const [requestFilterFlag, setResquestFilterFlag] = useState(false);
	const [currentFilter, setCurrentFilter] = useState("");

	useEffect( () => {
		console.log("locationState");
		console.log(location.state);
		if (location.state === undefined){
			alert("Access Denied: redirecting to login");
			history.replace("/")
		}
	},[location,history]);

	useEffect(() => {
		if(!requestFilterFlag){
			setCurrentFilter("");
		}
	},[requestFilterFlag])

	const RenderFilterButtons = () => {
		if(requestFilterFlag){
			return(
				<div>
					<button onClick = {() => {setCurrentFilter(allPending)}}>View All Pending Requests</button>
					<button onClick = {() => {setCurrentFilter(allResolved)}}>View All Resolved Requests</button>
					<button onClick = {() => {setCurrentFilter(pickEmployee)}}>View All requests from an Employee</button>
				</div>
			);
		}
		else{
			return(
				<div></div>
			)
		}
	}

	// const 

	return (
		<div>
			<h2>This is the Manager Home Page!</h2>
			<button onClick = { () => {setResquestFilterFlag(!requestFilterFlag)}}>View Requests</button>
			<button>View Employees</button>
			<button>Create Employee</button>
			<button  onClick= {() => {logout(history)}} > LogOut</button>
			<RenderFilterButtons/>
		</div>
	)
}

export default ManagerHomePage; 