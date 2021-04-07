import { Link } from 'react-router-dom';


const BookList = ({books}) => {
    return ( 
        <div className="books">
            {books.map(book => (
                <div className="book-mini" key={ book.id }>
                    <Link to={`/book/${book.id}`}>
                        <div className="first-line">
                            <img src={ book.url } alt={ book.shortSummary }/>    
                            <div className="inside">
                            <h2>{ book.title }</h2>
                            <h4>{ book.author }</h4>
                            </div>
                        </div>
                        <p>{ book.shortSummary }</p>
                    </Link>
                </div>
            ))}
        </div>
     );
}
 
export default BookList;