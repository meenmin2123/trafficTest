import axios from 'axios';

const fetchData = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/greet');
        console.log(response.data); // "Hello from Spring Boot!"
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};

const sendData = async () => {
    try {
        const response = await axios.post('http://localhost:8080/api/data', { name: 'John' });
        console.log(response.data); // "Hello, John!"
    } catch (error) {
        console.error('Error sending data:', error);
    }
};

fetchData();
sendData();
