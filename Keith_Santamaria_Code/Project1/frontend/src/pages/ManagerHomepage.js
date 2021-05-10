import {useEffect} from 'react';

const ManagerHomePage = (props) => {

	useEffect( () => {
		console.log("props");
		console.log(props);
	})

	return (
		<p>This is the Manager Home Page!</p>
	)
}

export default ManagerHomePage; 