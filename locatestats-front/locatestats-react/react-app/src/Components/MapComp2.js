import {GoogleMap, Marker, useJsApiLoader} from "@react-google-maps/api";
import React, {useEffect, useState} from "react";

const containerStyle = {
    width: "100%",
    height: "100vh"
};

const defaultOptions = {
    panControl: true,
    zoomControl: true,
    mapTypeControl: false,
    scaleControl: false,
    streetViewControl: false,
    rotateControl: false,
    clickableIcons: false,
    keyboardShortcuts: false,
    scrollwheel: true,
    disableDoubleClickZoom: false,
    fullscreenControl: false
};

function Map() {
    const [map, setMap] = useState(null);
    const [center, setCenter] = useState({
        lat: 41.303, lng: 69.245
    });

    const handleClick = () => {
        window.open("https://goo.gl/maps/PWthgxGizbJLUpcc6");
    };

    const {isLoaded} = useJsApiLoader({
        googleMapsApiKey: "AIzaSyDE2CxG6tgPeO8yuxcU5X3gH_4rxOB1LhY"
    });

    const onLoad = React.useCallback(function callback(map) {
        setMap(map);
    }, []);

    const onUnmount = React.useCallback(function callback(map) {
        setMap(null);
    }, []);

    const [isMounted, setIsMounted] = useState(false);

    useEffect(() => setIsMounted(true), []);

    return isLoaded ? (
        <div>
            <p>#</p>
        <p>{isLoaded ? ("yes"):("no")}</p>
            <p>{isMounted ? ("yes"):("no")}</p>
            <p>#</p>
            <GoogleMap
                id="map"
                mapContainerStyle={containerStyle}
                center={center}
                onLoad={onLoad}
                onUnmount={onUnmount}
                options={defaultOptions}
                zoom={15}
            >
                {isMounted}
                {isMounted && <Marker position={center} onLoad={onLoad} onClick={handleClick}></Marker>}
            </GoogleMap>
        </div>
    ) : (
        <div>  <h1>LOADING...</h1></div>
    );
}

export default Map;