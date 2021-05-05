import './App.css';
import ContactsPage from './contacts/ContactsPage';
import Counter from './Counter/Counter';
import MyForm from './forms/MyForm';
import HelloForm from './forms/HelloForm';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import { useHistory } from 'react-router-dom';

function App() {
  const history = useHistory();
  return (
    <div className="App">
      <Router>
        <nav>
          <span>
            <Link to="/count">Counter</Link>
          </span>
          <span>
            <Link to="/form">My Form</Link>
          </span>
          <span>
            <Link to="/hello">Hello Form</Link>
          </span>
          <span>
            <Link to="/contacts">Contacts</Link>
          </span>
        </nav>
        <Switch>
          <Route path="/" exact></Route>
          <Route path="/count">
            <Counter />
          </Route>
          <Route path="/form">
            <MyForm />
          </Route>
          <Route path="/hello">
            <HelloForm />
          </Route>
          <Route path="/contacts">
            <ContactsPage />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
