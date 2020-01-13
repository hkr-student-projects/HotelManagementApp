/*SELECT `number` as room 
FROM hotel.Room 
WHERE `number` 
NOT IN (SELECT `Room_number` FROM hotel.BookedRoom);*/
/*SELECTING NOT EVEN HISTORICALLY BOOKED ROOMS*/

/*SELECT `Room_number`,`movein`,`moveout` 
FROM hotel.BookedRoom, hotel.Booking 
WHERE hotel.BookedRoom.Booking_reference = hotel.Booking.reference;*/
/*SELECTING ALL ROOMS WITH ITS USAGE DATES*/

SELECT @rin:='2019-12-12';
SELECT @rout:='2019-12-19';
SELECT `Room_number`
FROM hotel.BookedRoom, hotel.Booking 
WHERE hotel.BookedRoom.Booking_reference = hotel.Booking.reference AND `Room_number` NOT IN
(SELECT `Room_number`
FROM hotel.BookedRoom, hotel.Booking 
WHERE hotel.BookedRoom.Booking_reference = hotel.Booking.reference AND
(	
	((@rin >= `movein` AND @rin < `moveout`) OR (@rout >= `movein` AND @rout <= `moveout`)) 
    OR 
	(`movein` >= @rin AND `moveout` <= @rout)
))
UNION SELECT `number`
FROM hotel.Room 
WHERE `number` 
NOT IN (SELECT `Room_number` 
FROM hotel.BookedRoom);

-- SELECT @rin:='2019-12-12';
-- SELECT @rout:='2019-12-19';
-- SELECT `Room_number` AS Room
-- FROM hotel.BookedRoom, hotel.Booking 
-- WHERE hotel.BookedRoom.Booking_reference = hotel.Booking.reference AND 
-- (SELECT 1 FROM hotel.BookedRoom, hotel.Booking WHERE ((@rin < `movein` AND @rout < `movein`) OR (@rin > `moveout` AND @rout > `moveout`)))
-- UNION SELECT `number`
-- FROM hotel.Room 
-- WHERE `number` 
-- NOT IN (SELECT `Room_number` 
-- FROM hotel.BookedRoom)
-- ORDER BY AvailableRoom;