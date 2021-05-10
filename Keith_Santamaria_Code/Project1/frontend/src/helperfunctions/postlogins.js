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