import React from 'react'
import {GoogleMap, Marker, useJsApiLoader} from '@react-google-maps/api';

const api_key = process.env.REACT_APP_PUBLIC_GOOGLE_MAPS_API_KEY2
console.log(api_key)

const containerStyle = {
    width: '80%',
    height: '80vh'
};

const center = {
    lat: 41.303, lng: 69.245
};

const Map = () => {
    const { isLoaded } = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: api_key
    })

    const [map, setMap] = React.useState(null)

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
            { /* Child components, such as markers, info windows, etc. */ }
            <Marker position={center} />
            <></>
        </GoogleMap>
    ) : <></>
}

export default React.memo(Map)