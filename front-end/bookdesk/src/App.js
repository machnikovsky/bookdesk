import Navbar from './components/Navbar';
import HomePage from './components/HomePage';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'; 
import AddBook from './components/AddBook';
import BookDetails from './components/BookDetails';
import Login from './components/Login';
import Logout from './components/Logout';
import { useState } from 'react';
import MyBooks from './components/MyBooks';

function App() {



  return (
    <Router>
    <div className="App">
      <Navbar />
      <div className="content">
        <Switch>
          <Route exact path="/">
            <HomePage isLogged={true} />
          </Route>
          <Route exact path="/add">
            <AddBook />
          </Route>
          <Route exact path="/login">
            <Login />
          </Route>
          <Route exact path="/logout">
            <Logout />
          </Route>
          <Route exact path="/mybooks">
            <MyBooks />
          </Route>
          <Route path="/book/:id">
            <BookDetails />
          </Route>
        </Switch>
      </div>
    </div>
    </Router>
  );
}

export default App;
