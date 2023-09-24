import React, {useEffect} from 'react'
import {GoogleMap, Marker, useJsApiLoader} from '@react-google-maps/api';

const api_key = process.env.REACT_APP_PUBLIC_GOOGLE_MAPS_API_KEY2
console.log(api_key)

const containerStyle = {
    width: '100%',
    height: '100vh'
};

const INIT_CENTER = {
    // lat: Lat, lng: Lon
    lat: 41.303, lng: 69.245
}

const Map = (props) => {

    const [center, setCenter] = React.useState(INIT_CENTER)
    const [cursorPosition, setCursorPosition] = React.useState(INIT_CENTER)
    const [countRendr, setCountRendr] = React.useState(0)
    const [map, setMap] = React.useState(null)

    if (props.countRendr !== countRendr) {
        // setState(!state)
        setCountRendr(countRendr + 1)
        setCursorPosition({"lat": props.pLat, "lng": props.pLon})
    }

    const {isLoaded} = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: api_key
    })

    const onLoad = React.useCallback(function callback(map) {
        // This is just an example of getting and using the map instance!!! don't just blindly copy!
        const bounds = new window.google.maps.LatLngBounds(center);
        map.fitBounds(bounds);

        setMap(map)
    }, [])

    const onUnmount = React.useCallback(function callback(map) {
        setMap(null)
    }, [])

    return isLoaded ? (
        <GoogleMap
            mapContainerStyle={containerStyle}
            center={center}
            zoom={10}
            onLoad={onLoad}
            onUnmount={onUnmount}
        >
            { /* Child components, such as markers, info windows, etc. */}
            <Marker position={cursorPosition}/>
            <></>
        </GoogleMap>
    ) : <></>

}

export default React.memo(Map)