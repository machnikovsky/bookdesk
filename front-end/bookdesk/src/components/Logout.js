import { useEffect } from "react";
import { Link } from "react-router-dom";

const Logout = () => {

    useEffect(() => {
        localStorage.removeItem('user');
    })

    return ( 
        <div className="logout">
            <h1>Udało się poprawnie wylogować</h1>
            <Link to="/">Wróć na stronę główną</Link>
        </div>
     );
}
 
export default Logout;