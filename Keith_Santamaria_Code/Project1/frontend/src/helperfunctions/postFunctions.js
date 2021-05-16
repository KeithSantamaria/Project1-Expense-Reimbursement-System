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

export const postEmployeeRequests = (data, setRespData) => {
	axios.post('http://localhost:6969/viewemployeerequests' , data)
	.then(res => {
		setRespData(res.data);
	})
}

export const postAllPendingRequests = (data, setRespData) => {
	axios.post('http://localhost:6969/viewall', data)
	.then(res => {
		setRespData(res.data);
	})
}

export const postUpdateStatus = (data) => {
	axios.post("http://localhost:6969/review", data)
}