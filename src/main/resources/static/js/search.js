
const today = new Date();

const datePicker1 = flatpickr("#datePicker1", {
  dateFormat: "d-m-Y",
  altInput: true,
  altFormat: "d M Y",
  minDate: today,
  onChange: function (selectedDates) {
    if (selectedDates.length > 0) {
      datePicker2.set("minDate", selectedDates[0]);
    } else {
      datePicker2.set("minDate", today);
    }
  }
});

const datePicker2 = flatpickr("#datePicker2", {
  dateFormat: "d-m-Y",
  altInput: true,
  altFormat: "d M Y",
  minDate: today,
  onChange: function (selectedDates) {
    if (selectedDates.length > 0) {
      datePicker1.set("maxDate", selectedDates[0]);
    } else {
      datePicker1.set("maxDate", null);
    }
  }
});

const form = document.querySelector("form");
const checkIn = document.getElementById("datePicker1");
const checkOut = document.getElementById("datePicker2");

form.addEventListener("submit", function (e) {
  checkIn.setCustomValidity("");
  checkOut.setCustomValidity("");

  if (!datePicker1.selectedDates.length) {
    e.preventDefault();
    checkIn.setCustomValidity("Please select a check-in date");
    checkIn.reportValidity();
  } else if (!datePicker2.selectedDates.length) {
    e.preventDefault();
    checkOut.setCustomValidity("Please select a check-out date");
    checkOut.reportValidity();
  }
});