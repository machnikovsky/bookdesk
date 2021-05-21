import { useParams } from "react-router";
import { useHistory } from 'react-router-dom';
import useFetch from "./useFetch";
import { encode } from "base-64";



const BookDetails = () => {

    const URL = 'http://localhost:8080/api/books/';
    const URLADD = 'http://localhost:8080/books/add';


    const { id } = useParams();
    const book = useFetch(URL + id, 'GET');
    const history = useHistory();
    const loggedInUser = localStorage.getItem("user");
    const username = JSON.parse(loggedInUser).login;
    const password = JSON.parse(loggedInUser).password;


    const handleDelete = () => {
        fetch(URL + id, {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
                'Authorization': 'Basic ' + encode(username + ":" + password),
                },
        })
        .then(res => {
            history.push('/');
         })
    }


    const handleAdd = () => {
        fetch(URLADD, {
            method: 'POST',
            headers: new Headers({
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + encode(username + ":" + password),

              }),
            body: JSON.stringify(book)
        })
        .then(res => {
            history.push('/');
         })
    }

    return ( 
        <div className="book-detials">
        { book && (
            <div>
            <div className="first-line">
                <img src={ book.url } alt={ book.shortSummary }/>    
                <div className="inside">
                <h2>{ book.title }</h2>
                <h4>{ book.author }</h4>
                </div>
            </div>
            <p>{ book.longSummary }</p>
            { loggedInUser && <button onClick={handleDelete}>Delete book</button> }
            { loggedInUser && <button onClick={handleAdd}>Dodaj do moich książek</button> }
            </div>
        )}
        </div> 
     );
}
 
export default BookDetails;