import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import EmployeeHomepage from "./pages/EmployeeHomepage";
import Login from "./pages/Login";
import ManagerHomePage from "./pages/ManagerHomepage";

const Routes = () => {
	return(
		<Router>
			<Switch>
				<Route path="/" exact>
					<Login/>
				</Route>
				<Route path="/employee">
					<EmployeeHomepage/>
				</Route>
				<Route path= "/manager">
					<ManagerHomePage/>
				</Route>
			</Switch>
		</Router>
	)
}

export default Routes;