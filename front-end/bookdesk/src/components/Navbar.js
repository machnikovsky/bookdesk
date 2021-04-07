import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import logo from '../assets/logo.png';

const Navbar = () => { 

    const loggedInUser = localStorage.getItem("user");

    return ( 
        <div className="navbar">
            <Link to="/">
                <img src={ logo } alt="logo"/>
            </Link>
            <div className="links">
                { loggedInUser && <Link to="/mybooks">Moje książki</Link>} 
                <Link to="/add">Dodaj książkę</Link>
                {
                    loggedInUser ?
                    <Link to="/logout" >Wyloguj się</Link> :
                    <Link to="/login">Zaloguj się</Link>
                }
            
                <Link to="/register">Zarejestruj się</Link>

            </div>
        </div>
     );
}
 
export default Navbar;