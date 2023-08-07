// Call swal alert
document.addEventListener("DOMContentLoaded", function () {
  let msg = document.getElementById("message").innerText;
  // Display a SweetAlert when the document is ready
  Swal.fire({
    title: msg,
    icon: 'success',
    timer: 1500, // Time in milliseconds (2 seconds)
    timerProgressBar: true,
    showConfirmButton: false // Hide the "Confirm" button
  });
  const currentUrl = window.location.href;
  const cleanUrl = currentUrl.split('?')[0];
  history.replaceState(null, null, cleanUrl);
});

// Swiper js
var swiper = new Swiper(".popular-content", {
  slidesPerView: 1,
  spaceBetween: 10,
  autoplay: {
    delay: 5580,
    disableOnInteraction: false,
  },
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  breakpoints: {
    280: {
      slidesPerView: 1,
      spaceBetween: 10,
    },
    320: {
      slidesPerView: 2,
      spaceBetween: 10,
    },
    510: {
      slidesPerView: 2,
      spaceBetween: 10,
    },
    758: {
      slidesPerView: 3,
      spaceBetween: 15,
    },
    900: {
      slidesPerView: 4,
      spaceBetween: 20,
    },
  },
});

// Show Video
let playButton = document.querySelector(".play-movie");
let video = document.querySelector(".video-container");
let myvideo = document.querySelector("#myvideo");
let closebtn = document.querySelector(".close-video");

playButton.onclick = () => {
  console.log(video);
  video.classList.add("show-video");
  // Auto play when click on button
  myvideo.play();
};
closebtn.onclick = () => {
  video.classList.remove("show-video");
  // Pause when click on button
  myvideo.pause();
};
