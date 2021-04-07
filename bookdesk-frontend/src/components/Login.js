import { useState } from "react";
import { useHistory } from "react-router";


const Login = () => {

    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [user, setUser] = useState();

    const history = useHistory();



    const handleSubmit = (e) => {
        e.preventDefault();
        const credentails = {login, password};

        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
            "Content-Type": "application/json",
            },
            body: JSON.stringify(credentails),

        }).then(res => {
            if (res.ok) {
                localStorage.setItem('user', JSON.stringify(credentails));
                setUser(credentails);
                history.push('/');
            }
        });
    }

    return ( 
        <div className="login">
            Log In
            <form onSubmit={handleSubmit}>
                <label>Login: </label>
                <input 
                    type="text"
                    required
                    value={login}
                    onChange={e => setLogin(e.target.value)}
                />

                <label>Password: </label>
                <input 
                    type="text"
                    required
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                />

                <button> Login </button>
            </form>
        </div>
     );
}
 
export default Login;