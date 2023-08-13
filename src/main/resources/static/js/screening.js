// Var to Get timeselector:
let timeSelectorValue = 0;

// Get time selector
const timeSelector = document.getElementById("timeSelector");
// Get date selector
const dateSelector = document.getElementById("dateSelector");

// Get screeningJson
const screeningsJson = document.getElementById("screeningsJson");
if (screeningsJson != null) {
    populateDateOptions(getArrayFromJson());
}
//Get ticket price
let seatPrice = JSON.parse(screeningsJson.innerText)[0].movie.price;
document.getElementById("priceFromScreeningId").value = seatPrice;
console.log(seatPrice);

dateSelector.addEventListener("change", (event) => {
    const selectedDate = event.target.value;
    console.log(selectedDate)
    populateShowTimeOptions(selectedDate);
});

function getArrayFromJson() {
    // Json to Array JS
    let screeningsArray = JSON.parse(screeningsJson.innerText);
    return screeningsArray;
}

// Function to populate date options dynamically
function populateDateOptions(screeningsArray) {
    const dateSet = new Set();

    screeningsArray.forEach((screening) => {
        dateSet.add(screening.dateMovie);
    });

    const sortedDates = Array.from(dateSet).sort();
    let defaultOption = document.createElement("option");
    defaultOption.textContent = "Please choose";
    dateSelector.appendChild(defaultOption);
    sortedDates.forEach((date) => {
        let option = document.createElement("option");
        option.value = date;
        option.textContent = date;
        dateSelector.appendChild(option);
        console.log(option);
    });
    dateSelector.options[0].selected = true;
}

// Function to populate show time options based on the selected date
function populateShowTimeOptions(selectedDate) {
    timeSelector.innerHTML = "";
    let screeningsArray = getArrayFromJson();
    console.log(screeningsArray)
    const timesForSelectedDate = screeningsArray.filter(
        (screening) => screening.dateMovie === selectedDate
    );
    console.log(timesForSelectedDate);

    timesForSelectedDate.forEach((screening) => {
        let option = document.createElement("option");
        let text = `${screening.showTimes.startTime}-${screening.showTimes.endTime}`;
        let screeningId = `${screening.id}`
        console.log(screeningId)
        option.value = `${screening.id}`;
        option.textContent = text;
        console.log(option)
        timeSelector.appendChild(option);
    });

    // Show the showTimeSelector element once options are populated
    timeSelector.style.display = "inline-block";
}

// display seats button
function sendScreeningId() {
    timeSelectorValue = parseInt(timeSelector.value);
    let screeningId = parseInt(timeSelector.value);
    document.getElementById("screeningIdInForm").value = screeningId;
    let username = document.getElementById("user-info").innerText;
    document.getElementById("usernameInForm").value = username;
    let url = "/seatDetail/" + screeningId;
    if (url === "/seatDetail/") {
        Swal.fire({
            title: "Please choose date and showtime",
            icon: 'error',
            timer: 1500,
            timerProgressBar: true,
            showConfirmButton: false
        })
    } else {
        document.getElementById("formOfDisplaySeat").submit();
    }
}

function seatDetail() {
    let seatsArray = [];
    const seatDetailJson = document.getElementById("seatDetailJson").innerText;
    console.log(seatDetailJson)
    if (seatDetailJson != null) {
        const seatDetailArray = JSON.parse(seatDetailJson);
        console.log(seatDetailArray)
        seatDetailArray.forEach((seatDetail) => {
            seatsArray.push(seatDetail.seats.id);
        });
        console.log(seatsArray)
        return seatsArray;
    } else {
        return null;
    }

}

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
    localStorage.setItem("price", JSON.stringify(ticketPrice));
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
    console.log(seatsList);

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
}


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

function showTickets() {
    sendSeatsInfo();
}

function sendSeatsInfo() {
    const selectedSeats = document.querySelectorAll(".row .seat.selected");
    const seatsList = [...selectedSeats].map((seat) => seat.textContent);
    let userId = document.getElementById("userId").innerText;
    console.log(userId)
    let screeningId = document.getElementById("trueScreeningId").innerText;
    localStorage.setItem("userId",userId.toString());
    localStorage.setItem("screeningId",screeningId.toString())
    let seatsInfoArr = [];
    let apiSuccess = "";

    seatsList.forEach((seatName) => {
        let flag = true;
        let seatInfo = {
            idAccount: parseInt(userId),
            nameSeats: seatName,
            idScreenings: screeningId,
            flag: flag
        };
        seatsInfoArr.push(seatInfo);
    });
    console.log(seatsInfoArr);
    if (seatsInfoArr.length > 0) {
        Swal.fire({
            title: "Are you want to confirm buying ticket(s)",
            text: 'You can not undo this!!!',
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, I do!",
        }).then((result) => {
            if (result.isConfirmed) {
                fetch('/api/save-seat-details', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(seatsInfoArr),
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.text(); // Use response.text() to read the response body as text
                    })
                    .then(data => {
                        // Handle the response from the back-end
                        console.log('Success:', data); // Log the success message
                        apiSuccess = data;
                        // Display an alert or update UI if needed
                        Swal.fire({
                            title: data,
                            icon: 'success',
                            timer: 1500, // Time in milliseconds (2 seconds)
                            timerProgressBar: true,
                            showConfirmButton: false // Hide the "Confirm" button
                        });
                        // send form to show tickets
                        sendFormShowTickets();
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        Swal.fire({
                            title: error,
                            icon: 'error',
                            timer: 1500, // Time in milliseconds (2 seconds)
                            timerProgressBar: true,
                            showConfirmButton: false // Hide the "Confirm" button
                        });
                    });
            }
        });
        return true;
    } else {
        Swal.fire({
            title: "No tickets for you to submit, please choose!",
            icon: 'success',
            timer: 1500, // Time in milliseconds (2 seconds)
            timerProgressBar: true,
            showConfirmButton: false // Hide the "Confirm" button
        });
        return false;
    }
}

function sendFormShowTickets() {
    let screeningId = parseInt(localStorage.getItem("screeningId"));
    let userId = parseInt(localStorage.getItem("userId"));
    document.getElementById("screeningIdToShowTickets").value = screeningId;
    document.getElementById("userIdToShowTickets").value = userId;
    document.getElementById("formOfShowTickets").submit();
}