window.addEventListener('load', function () {
  // Retrieve carrier data from localStorage
  const carrierList = JSON.parse(localStorage.getItem('carrierList')) || [];
  const carrierTableBody = document.getElementById('carrierTable').querySelector('tbody');
  
  // Populate carrier table
  carrierList.forEach(carrier => {
      const row = carrierTableBody.insertRow();
      row.insertCell(0).textContent = carrier.carrierName;
      row.insertCell(1).textContent = carrier._30DaysAdvanceBooking;
      row.insertCell(2).textContent = carrier._60DaysAdvanceBooking;
      row.insertCell(3).textContent = carrier._90DaysAdvanceBooking;
      row.insertCell(4).textContent = carrier.bulkBooking;
  });

  // Retrieve flight data from localStorage
  const flightList = JSON.parse(localStorage.getItem('flightList')) || [];
  const flightTableBody = document.getElementById('flightTable').querySelector('tbody');
  
  // Populate flight table
  flightList.forEach(flight => {
      const row = flightTableBody.insertRow();
      row.insertCell(0).textContent = flight.carrierName;
      row.insertCell(1).textContent = flight.origin;
      row.insertCell(2).textContent = flight.destination;
      row.insertCell(3).textContent = flight.airFare;
      row.insertCell(4).textContent = flight.seatCapacityBusinessClass;
      row.insertCell(5).textContent = flight.seatCapacityEconomyClass;
      row.insertCell(6).textContent = flight.seatCapacityExecutiveClass;
  });
});
