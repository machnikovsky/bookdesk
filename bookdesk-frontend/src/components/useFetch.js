import { useEffect, useState } from "react";
import { encode } from "base-64";

const useFetch = (url, HTTPmethod) => {

    const [data, setData] = useState(null);
    const username = 'user';
    const password = 'password';

    const [user, setUser] = useState();
 
    useEffect(() => {
        const abortController = new AbortController();

        fetch(url, {
            signal: abortController.signal,
            method: HTTPmethod,
            headers: new Headers({
                'Authorization': 'Basic ' + encode(username + ":" + password),
                'Content-Type': 'application/json'
              })
        })
        .then(res => {
            console.log(url);
            console.log(res);
            return res.json();
         })
        .then(data => {
            console.log(data);
            setData(data);
        });
    }, [])

    return data;
}
 
export default useFetch;