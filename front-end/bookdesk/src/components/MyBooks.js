import { useEffect, useState } from 'react';
import BookList from './BookList';
import useFetch from './useFetch';


const MyBooks = () => {

    const books = useFetch('http://localhost:8080/api/books/my', 'GET');
    const loggedInUser = localStorage.getItem("user");


    const [user, setUser] = useState();


    return ( 
        <div className="home-page">
            {
                loggedInUser ?
                (  
                <div>      
                <h1 style={{textAlign: "center"}}>Moje książki</h1>
                {books && <BookList books={books} />}
                </div>
                ) :
                (
                    <p>Zaloguj sie, aby przeglądać książki</p>
                )
            }

        </div>
     );
}
 
export default MyBooks;