import {useEffect, useState} from "react";
import {Client} from "@stomp/stompjs";
import SockJS from 'sockjs-client'


const SOCKET_URL = 'http://localhost:8081/ws';

export const Websocket = (props) => {

    const [isConnected, setConnected] = useState(false)

    useEffect(() => {
        console.log("from useEffect isConnected" + isConnected)
        const id = setInterval(() => {
            if (!isConnected) {
                newStompConn();
                console.warn("isConnected:" + isConnected)
            }
        }, 5000)

        return () => {
            clearInterval(id);
            // stompClient.deactivate().then(r => console.log("stompClient deactivate"))
        }
    }, [isConnected])

    function newStompConn() {

        const socket = new SockJS(SOCKET_URL)

        // console.log(socket)
        const stompClient = new Client({
            webSocketFactory: () => socket,
            debug: function (str) {
                console.log('STOMP: ' + str);
            },
            onDisconnect: () => onDisconnected("@@@@@@@@@@@@@@@@@"),
            beforeConnect: () => onDisconnected("Starting... before connect"),
            reconnectDelay: 5000,
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
        })

        stompClient.onConnect = () => {
            console.log("Connect to WebSocket")
            setConnected(prevState => true)

            const subscription = stompClient.subscribe(
                `/topic/message`,
                (message) => {
                    onMessageReceived(message)
                })
            // это можно использовать для отправки запроса на получение несчитанных данных,
            // а также идентификации устройства
            // "  // stompClient.publish({
            //     destination: '/get-skipped-messages',
            //     headers: {},
            //     body: JSON.stringify({token: token})
            // })"

        }

        socket.onChangeState = (state) => {
            console.log(`onChangeState ${JSON.stringify(state)}`, 'WS');
        };

        socket.onWebSocketError = (error) => {
            console.error(`onWebSocketError ${JSON.stringify(error)}`, 'WS');
        };

        socket.onStompError = (error) => {
            console.error(`onStompError ${JSON.stringify(error)}`, 'WS');
        };

        socket.onWebSocketClose = (evt) => {
            console.warn(`onWebSocketClose(): ${JSON.stringify(evt)}`, 'WS');
        };

        socket.onDisconnect = (receipt) => {
            console.warn(`onDisconnect ${JSON.stringify(receipt)}`, 'WS');
        };


        stompClient.activate()
        return {socket, stompClient};
    }

    // 2 клиент, не стал убирать. Можно использовать.
    // const new_conn = () => {
    //
    //     console.log("!!trying to connect!!");
    //
    //     const client = new Client()
    //     const sss = client.webSocketFactory = function () {
    //         return new SockJS(SOCKET_URL);
    //     };
    //
    //     // let sss = new SockJS(SOCKET_URL)//, null, {transports: ['polling']});
    //     let stClient = Stomp.over(sss);
    //
    //     sss.onclose = () => {
    //         console.log("connectionclodedaojfowboeboeboebobob")
    //     }
    //
    //     // const stClient = Stomp.over(() => new SockJS(SOCKET_URL));
    //
    //     stClient.connect({}, onConnected, onError);
    //
    //
    //     setStompClient(stClient)
    //
    //     // socket.onopen = function () {
    //     //     clearInterval(recInterval);
    //     // };
    //     //
    //     // socket.onclose = () => {
    //     //     let recIntervall = setInterval(function () {
    //     //         new_conn();
    //     //     }, 5000);
    //     //     setRecInterval(prevState => recIntervall)
    //     // };
    //     //
    //     // socket.onmessage = (msg) => {
    //     //     console.log("msg from SockJS:" + msg)
    //     // }
    //     //
    //     // setSocket(sss)
    // };

    const onMessageReceived = (payload) => {
        console.log("receive message");
        props.onRecieveMes(payload.body)
    }

    const onDisconnected = (msg) => {
        console.log("///////" + msg)
        setConnected(prevState => false)
    }

    const changeState = () => {
        setConnected(prevState => !prevState)
        if (!isConnected) {
            newStompConn();
        }
    }

    return (
        <button onClick={changeState}>
            {!isConnected ? "CONNECT" : "DISCONNECT"}
        </button>
    )

    // 3 клиент. Тоже не стал убирать. Самый простой из всех, но и самый менее настраиваемый.
    // return !isConnected ? (
    //     <div>Connected by SockJsClient{isConnected}
    //         {/*    <SockJsClient*/}
    //         {/*    url={SOCKET_URL}*/}
    //         {/*    topics={['/topic/message']}*/}
    //         {/*    headers={headers}*/}
    //         {/*    subscribeHeaders={headers}*/}
    //         {/*    onConnect={msg => onConnected(msg)}*/}
    //         {/*    onDisconnect={msg => onDisconnected(msg)}*/}
    //         {/*    onMessage={msg => onMesReceived(msg)}*/}
    //         {/*    debug={false}*/}
    //         {/*    onConnectFailure={error => onConnectFailed(error)}*/}
    //         {/*    getRetryInterval={r => getRetryInterval(r)}*/}
    //         {/*/>*/}
    //     </div>
    // ) : <>Disconnected</>
}

export default Websocket