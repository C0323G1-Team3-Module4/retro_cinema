// test JSON


document.addEventListener("DOMContentLoaded", function () {
  // Assuming you have received the list of booked seats as an array
  // Example: const bookedSeats = [1, 2, 3, 7, 8];
  const bookedSeats = [1, 2];

  // Loop through each seat in the bookedSeats array and update its status
  bookedSeats.forEach((seatNumber) => {
    const seatElement = document.getElementById(`seat${seatNumber}`);
    if (seatElement) {
      seatElement.classList.add("occupied");
    }
  });
});

const main = document.querySelector(".main");
const seats = document.querySelectorAll(".row .seat:not(.occupied");
const count = document.getElementById("count");
const total = document.getElementById("total");
const movieSelect = document.getElementById("movie");

populateUI();
let ticketPrice = +movieSelect.value;

// Save selected movie index and price
function setMovieData(movieIndex, moviePrice) {
  localStorage.setItem("selectedMovieIndex", movieIndex);
  localStorage.setItem("selectedMoviePrice", moviePrice);
}

// update total and count
function updateSelectedCount() {
  const selectedSeats = document.querySelectorAll(".row .seat.selected");

  const seatsIndex = [...selectedSeats].map((seat) => [...seats].indexOf(seat));
  const seatsList = [...selectedSeats].map((seat) => seat.textContent);

  localStorage.setItem("selectedSeats", JSON.stringify(seatsIndex));
  localStorage.setItem("selectedNames", JSON.stringify(seatsList));
  localStorage.setItem("ticketPrice", ticketPrice);
  document.getElementById("price").innerText = ticketPrice + " $";
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

  const selectedMovieIndex = localStorage.getItem("selectedMovieIndex");

  if (selectedMovieIndex !== null) {
    movieSelect.selectedIndex = selectedMovieIndex;
  }
}

// Movie select event
movieSelect.addEventListener("change", (e) => {
  ticketPrice = +e.target.value;
  setMovieData(e.target.selectedIndex, e.target.value);
  updateSelectedCount();
});

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
