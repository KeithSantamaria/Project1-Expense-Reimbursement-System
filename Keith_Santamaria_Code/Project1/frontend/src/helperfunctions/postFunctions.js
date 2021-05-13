import axios from 'axios';

export const postAsEmployee = (data, setRespData) => {
	axios.post('http://localhost:6969/loginemployee', data)
	.then( res => {
		setRespData(res.data);
	})
};

export const postAsManager = (data, setRespData) => {
	axios.post('http://localhost:6969/loginmanager', data)
	.then( res => {
		setRespData(res.data);
	})
}

export const postNewReimbursement = (data ,setRespData) => {
	axios.post('http://localhost:6969/createreimbursement', data)
	.then( res => { 
		setRespData(res.data);
	})
}

export const postEmployeePendingRequests = (data, setRespData) => {
	axios.post('http://localhost:6969/viewemployeepending' , data)
	.then(res => {
		setRespData(res.data);
	})
}