// test JSON

document.addEventListener("DOMContentLoaded", function () {
  // Assuming you have received the list of booked seats as an array
  // Example: const bookedSeats = [1, 2, 3, 7, 8];
  const bookedSeats = seatDetail();

  console.log(bookedSeats);

  // Loop through each seat in the bookedSeats array and update its status
  bookedSeats.forEach((seatNumber) => {
    const seatElement = document.getElementById(`seat${seatNumber}`);
    if (seatElement) {
      seatElement.classList.add("occupied");
    }
  });
  const price = document.getElementById("price")
  ticketPrice = document.getElementById("priceFromScreeningId").value;
  console.log(ticketPrice);
  price.innerText = ticketPrice + " $";
  localStorage.setItem("price",JSON.stringify(ticketPrice));
});
let ticketPrice = 0;
const main = document.querySelector(".main");
const seats = document.querySelectorAll(".row .seat:not(.occupied");
const count = document.getElementById("count");
const total = document.getElementById("total");


populateUI();

// update total and count
function updateSelectedCount() {
  const selectedSeats = document.querySelectorAll(".row .seat.selected");

  const seatsIndex = [...selectedSeats].map((seat) => [...seats].indexOf(seat));
  const seatsList = [...selectedSeats].map((seat) => seat.textContent);

  localStorage.setItem("selectedSeats", JSON.stringify(seatsIndex));
  localStorage.setItem("selectedNames", JSON.stringify(seatsList));
  //copy selected seats into arr
  // map through array
  //return new array of indexes

  const selectedSeatsCount = selectedSeats.length;
  const sum = selectedSeatsCount * ticketPrice;

  count.innerText = selectedSeatsCount;
  total.innerText = sum + " $";

  const seatAbc = document.getElementById("selected-seats");
  seatAbc.innerText = seatsList.join(", ");
}

// get data from localstorage and populate ui
function populateUI() {
  const selectedSeats = JSON.parse(localStorage.getItem("selectedSeats"));
  if (selectedSeats !== null && selectedSeats.length > 0) {
    seats.forEach((seat, index) => {
      if (selectedSeats.indexOf(index) > -1) {
        seat.classList.add("selected");
      }
    });
  }

  // const selectedMovieIndex = localStorage.getItem("selectedMovieIndex");
  //
  // if (selectedMovieIndex !== null) {
  //   movieSelect.selectedIndex = selectedMovieIndex;
  // }
}

// Movie select event
// movieSelect.addEventListener("change", (e) => {
//   ticketPrice = +e.target.value;
//   setMovieData(e.target.selectedIndex, e.target.value);
//   updateSelectedCount();
// });

// Seat click event
main.addEventListener("click", (e) => {
  if (
    e.target.classList.contains("seat") &&
    !e.target.classList.contains("occupied")
  ) {
    e.target.classList.toggle("selected");

    updateSelectedCount();
  }
});

// intial count and total
updateSelectedCount();


// Call swal alert
// document.addEventListener("DOMContentLoaded", function () {
//   let msg = document.getElementById("message").innerText;
//   // Display a SweetAlert when the document is ready
//   Swal.fire({
//     title: msg,
//     icon: 'success',
//     timer: 1500, // Time in milliseconds (2 seconds)
//     timerProgressBar: true,
//     showConfirmButton: false // Hide the "Confirm" button
//   });
//   const currentUrl = window.location.href;
//   const cleanUrl = currentUrl.split('?')[0];
//   history.replaceState(null, null, cleanUrl);
// });