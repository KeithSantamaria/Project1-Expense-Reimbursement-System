import {useState, useEffect} from 'react';
import {useHistory} from 'react-router-dom'


import {postAsEmployee, postAsManager} from '../helperfunctions/postFunctions';

const LoginForm = (props) => {

	const [userNameText, setUserNameText] = useState("");
	const [passwordText, setPasswordText] = useState("");
	const [respData, setRespData] = useState(null);

	const history = useHistory();

	useEffect( () => {
		if(respData === null){
			console.log("starting up page");
			return;
		}

		console.log(respData);
		if (respData.loginStatus === true){
			console.log("Something Exists!");
			if (respData.role === "MANAGER"){
				alert("Successfully logged in as Manager!");
				history.replace({
					pathname : "/manager",
					state : {
						userData: respData
					}
				});
			}
			if (respData.role === "EMPLOYEE"){
				alert("Successfully logged in as Employee!");
				history.replace({
					pathname : "/employee",
					state : {
						userData: respData
					}
				});
			}
		}
		else{
			alert("Login Failed");
		}
	}, [respData,history])



	const handleSubmit= (event) =>{
		event.preventDefault();
		const data = {
			username: userNameText,
			password: passwordText
		};

		if( props.loginAs === "employee"){
			postAsEmployee(data, setRespData);
		}
		if(props.loginAs === "manager"){
			postAsManager(data, setRespData);
		}


	}

	return(
		<form onSubmit={handleSubmit}>
			<span>Username: </span>
			<input type = "text" name="username" placeholder = "Username" onChange = {e => setUserNameText(e.target.value)}/>
			<span>Password: </span>
			<input type = "password" name="password" placeholder ="Password" onChange = {e => setPasswordText(e.target.value)} />
			<button>Submit</button>
			<div>{userNameText}</div>
		</form>
	)
}

export default LoginForm;


		// else{
		// 	console.log(respData);
		// 	if (props.loginAs === "employee"){
		// 		history.push({
		// 			pathname: '/employee',
		// 			state: {
		// 				userData : respData
		// 			}
		// 		});
		// 	}
		// 	if (props.loginAs ==="manager"){
		// 		history.push({
		// 			pathname: '/manager',
		// 			state: {
		// 				userData : respData
		// 			}
		// 		});
		// 	}
		// }