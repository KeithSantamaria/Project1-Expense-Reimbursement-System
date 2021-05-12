import {useState, useEffect} from 'react';

const ReimbursementForm = (props) => {
	const [reasonText, setReasonText] = useState("");
	const [amount, setAmount] = useState(0);

	useEffect( () => {
		console.log(reasonText);
	},[reasonText]);
	useEffect( () => {
		console.log(amount);
	}, [amount]);

	const handleSubmit = () => { 
		

	}

	return(
		<div onSubmit = {handleSubmit}>
			<h3>Enter reimbursement information here</h3>
			<form>
				<div>
					<span>Reason: </span>
					<input type="text" placeholder="Enter your reason here" onChange = {e => setReasonText(e.target.value)}></input>
				</div>
				<div>
					<span>Amount: </span>
					<input min="0" step="1" type="number" placeholder="Whole Numbers only" onChange = {e => setAmount(Math.trunc(e.target.value))}></input>
				</div>
				<button>Submit</button>
			</form>
		</div>
	)
}

export default ReimbursementForm;