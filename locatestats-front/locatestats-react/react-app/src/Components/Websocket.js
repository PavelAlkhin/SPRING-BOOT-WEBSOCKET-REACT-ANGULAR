import {useState} from "react";
import SockJsClient from 'react-stomp';

const SOCKET_URL = 'http://localhost:8080/ws-message';

const Websocket = () => {
    const [message, setMessage] = useState('Coordinates:');

    let onConnected = () => {
        console.log("Connected!!")
    }

    let onCoordinatesReceived = (coord) => {
        setMessage(coord.message);
    }

    return (
        <div>
            <SockJsClient
                url={SOCKET_URL}
                topics={['/topic/message']}
                onConnect={onConnected}
                onDisconnect={console.log("Disconnected!")}
                onMessage={coord => onCoordinatesReceived(coord)}
                debug={false}
            />
            <div>Coordinates:{message}</div>
        </div>
    );
}

export default Websocket