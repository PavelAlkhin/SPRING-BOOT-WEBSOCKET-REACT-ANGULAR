import style from './App.css';
import {useRef, useState} from "react";
import Websocket from "./Components/Websocket";
import Map from "./Components/Map";

const App = () => {

    const [Lat, setLat] = useState(41.3)
    const [Lon, setLon] = useState(69.2)

    const [RSSI, setRSSI] = useState(0)
    const [SNR, setSNR] = useState(0)

    const [countRendr, setCountRendr] = useState(0)

    const inputRefRSSI = useRef(RSSI)
    const inputRefSNR = useRef(SNR)
    const receiveMes = (msg) => {

        console.log("msg " + msg)

        let data = JSON.parse(msg)

        const lat = parseFloat(data["Lat"])
        const lon = parseFloat(data["Lon"])
        const rssi = parseFloat(data["RSSI"])
        const snr = parseFloat(data["SNR"])

        if (Lat !== lat || Lon !== lon || RSSI !== rssi || SNR !== snr) {
            setLat(lat)
            setLon(lon)
            setRSSI(rssi)
            setSNR(snr)
            setCountRendr(prevState => prevState + 1)
        }

        inputRefRSSI.current.value = rssi
        inputRefSNR.current.value = snr

    }

    return (
        <div>
            <header className="App">
                <div>
                    RSSI <input className={style.input} id="RSSI" type="text" defaultValue="" ref={inputRefRSSI}/>
                     SNR <input className={style.input} id="SNR" type="text" defaultValue="" ref={inputRefSNR}/>

                    {/*Оставил для тестирования*/}
                    {/*Lon<input id="Lon" type="text" defaultValue={Lon}>{Lon}</input>*/}
                    {/*Lat<input id="Lat" type="text" defaultValue={Lat}></input>*/}

                    <Websocket onRecieveMes={receiveMes} pLat={Lat}/>
                </div>
            </header>
            <Map pLat={Lat} pLon={Lon} countRendr={countRendr}/>
        </div>
    );
}

export default App;
