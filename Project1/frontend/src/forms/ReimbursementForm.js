import {useState} from 'react';
import {postNewReimbursement} from '../helperfunctions/postFunctions';

const ReimbursementForm = (props) => {
	const [reasonText, setReasonText] = useState("");
	const [amount, setAmount] = useState(0);
	// eslint-disable-next-line
	const [respData, setRespData] = useState(null);

	const handleSubmit = () => { 
		const data = {
			ownerId: props.userData._id,
			username: props.userData.username,
			reason: reasonText,
			amount: amount
		};
		postNewReimbursement(data,setRespData);
		alert("Created New Reimbursement Request!");
		window.location.reload(true);
	}

	return(
		<div >
			<h3>Enter reimbursement information here</h3>
				<div>
					<span>Reason: </span>
					<input type="text" placeholder="Enter your reason here" onChange = {e => setReasonText(e.target.value)}></input>
				</div>
				<div>
					<span>Amount: </span>
					<input min="0" step="1" type="number" placeholder="Whole Numbers only" onChange = {e => setAmount(Math.trunc(e.target.value))}></input>
				</div>
				<button onClick = {handleSubmit}>Submit</button>
		</div>
	)
}

export default ReimbursementForm;