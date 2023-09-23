// import { GoogleMap, Marker, useJsApiLoader } from "@react-google-maps/api";
// import React, { useState } from "react";
//
// const containerStyle = {
//     width: "100%",
//     height: "100vh"
// };
//
// const defaultOptions = {
//     panControl: true,
//     zoomControl: true,
//     mapTypeControl: false,
//     scaleControl: false,
//     streetViewControl: false,
//     rotateControl: false,
//     clickableIcons: false,
//     keyboardShortcuts: false,
//     scrollwheel: true,
//     disableDoubleClickZoom: false,
//     fullscreenControl: false
// };
//
// function Map() {
//     const [map, setMap] = useState(null);
//     const [center, setCenter] = useState({
//         lat: 41.303, lng: 69.245
//     });
//
//     const handleClick = () => {
//         window.open("https://goo.gl/maps/PWthgxGizbJLUpcc6");
//     };
//
//     const { isLoaded } = useJsApiLoader({
//         googleMapsApiKey: "AIzaSyDE2CxG6tgPeO8yuxcU5X3gH_4rxOB1LhY"
//     });
//
//     const onLoad = React.useCallback(function callback(map) {
//         setMap(map);
//     }, []);
//
//     const onUnmount = React.useCallback(function callback(map) {
//         setMap(null);
//     }, []);
//
//     return isLoaded ? (
//         <GoogleMap
//             id="map"
//             mapContainerStyle={containerStyle}
//             center={center}
//             onLoad={onLoad}
//             onUnmount={onUnmount}
//             options={defaultOptions}
//             zoom={15}
//         >
//             <Marker position={center} onLoad={onLoad} onClick={handleClick}></Marker>
//         </GoogleMap>
//     ) : (
//         <h1>LOADING...</h1>
//     );
// }
//
// export default Map;

import React, {useState} from "react";
import {useMemo} from "react";
import {GoogleMap, useLoadScript, Marker} from "@react-google-maps/api";

const api_key = process.env.REACT_APP_PUBLIC_GOOGLE_MAPS_API_KEY2
console.log(api_key)

const MapComponent = () => {
    const {isLoaded} = useLoadScript({
        googleMapsApiKey: api_key
    });

    if (!isLoaded) return <div>Loading ... </div>
    return <Map/>
}

const Map = () => {
    const center = useMemo(() => ({lat: 41.303, lng: 69.245}), []);
    return (
        <GoogleMap zoom={15} center={center} mapContainerClassName="map-container">
            <Marker position={center}/>
            {/*<AnyReactComponent*/}
            {/*    lat={49.955413}*/}
            {/*    lng={30.337844}*/}
            {/*    text={"Kreyser Avrora"}*/}
            {/*/>*/}
        </GoogleMap>
    );
}

export default MapComponent

// class MapComponent extends React.Component {
//     constructor(props){
//         super(props)
//         this.state = {
//             editForm: false
//         }
//     }
//     user = this.props.user
//     render() {
//         return (
//             <div className="user">
//                 <p>Map will be here</p>
//             </div>
//         )
//     }
// }
//
// export default MapComponent