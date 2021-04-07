import { useState } from "react";
import { useHistory } from 'react-router-dom';
import { encode } from "base-64";


const AddBook = () => {

    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [shortSummary, setShortSummary] = useState('');
    const [longSummary, setLongSummary] = useState('');
    const [url, setUrl] = useState('');
    const history = useHistory();
    const username = 'user';
    const password = 'password';




    const handleSubmit = (e) => {
        e.preventDefault();
        const newBook = {title, author, shortSummary, longSummary, url};

        fetch('http://localhost:8080/api/books', {
            method: 'POST',
            headers: {
            "Content-Type": "application/json",
            'Authorization': 'Basic ' + encode(username + ":" + password),
            },
            body: JSON.stringify(newBook),

        }).then(() => {
            history.push('/');
        });
    }

    return ( 
        <div className="add-book">
            Add a new book here.
            <form onSubmit={handleSubmit}>
                <label>Title: </label>
                <input 
                    type="text"
                    required
                    value={title}
                    onChange={e => setTitle(e.target.value)}
                />

                <label>Author: </label>
                <input 
                    type="text"
                    required
                    value={author}
                    onChange={e => setAuthor(e.target.value)}
                />

                <label>Short summary: </label>
                <input 
                    type="text"
                    required
                    value={shortSummary}
                    onChange={e => setShortSummary(e.target.value)}
                />
                <label>Long summary: </label>
                <input 
                    type="text"
                    required
                    value={longSummary}
                    onChange={e => setLongSummary(e.target.value)}
                />
                <label>URL: </label>
                <input 
                    type="text"
                    required
                    value={url}
                    onChange={e => setUrl(e.target.value)}
                />
                <button> ADD </button>
            </form>
        </div>
     );
}
 
export default AddBook;