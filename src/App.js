import React, { useState } from 'react';
import axios from 'axios';

function App() {
    const [hotels, setHotels] = useState([]);
    const [selectedHotel, setSelectedHotel] = useState(null);
    const [rooms, setRooms] = useState([]);
    const [feedback, setFeedback] = useState('');

    const findHotels = (latitude, longitude, radius) => {
        axios.get(`/hotels?latitude=${latitude}&longitude=${longitude}&radius=${radius}`)
            .then(response => setHotels(response.data));
    };

    const selectHotel = (hotelId) => {
        axios.get(`/hotel/${hotelId}/rooms`)
            .then(response => {
                setSelectedHotel(hotelId);
                setRooms(response.data);
            });
    };

    const bookRoom = (roomId) => {
        axios.post('/book', { room_id: roomId })
            .then(response => alert(response.data.message))
            .then(() => selectHotel(selectedHotel));
    };

    const cancelBooking = (roomId) => {
        axios.post('/cancel', { room_id: roomId })
            .then(response => alert(response.data.message))
            .then(() => selectHotel(selectedHotel));
    };

    const submitFeedback = () => {
        axios.post('/feedback', { feedback })
            .then(response => alert(response.data.message));
    };

    return (
        <div>
            <h1>Hotel Reservation System</h1>
            <input type="number" placeholder="Latitude" id="latitude" />
            <input type="number" placeholder="Longitude" id="longitude" />
            <input type="number" placeholder="Radius in KM" id="radius" />
            <button onClick={() => findHotels(
                document.getElementById('latitude').value,
                document.getElementById('longitude').value,
                document.getElementById('radius').value
            )}>
                Find Hotels
            </button>

            <ul>
                {hotels.map(hotel => (
                    <li key={hotel.id} onClick={() => selectHotel(hotel.id)}>
                        {hotel.name}
                    </li>
                ))}
            </ul>

            {selectedHotel && (
                <div>
                    <h2>Rooms</h2>
                    <ul>
                        {rooms.map(room => (
                            <li key={room.id}>
                                Room {room.roomNumber} - {room.price} USD
                                {room.isAvailable ? (
                                    <button onClick={() => bookRoom(room.id)}>Book</button>
                                ) : (
                                    <button onClick={() => cancelBooking(room.id)}>Cancel Booking</button>
                                )}
                            </li>
                        ))}
                    </ul>
                </div>
            )}

            <div>
                <h2>Leave Feedback</h2>
                <textarea value={feedback} onChange={(e) => setFeedback(e.target.value)}></textarea>
                <button onClick={submitFeedback}>Submit</button>
            </div>
        </div>
    );
}

export default App;