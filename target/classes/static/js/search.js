const today = new Date();

const tomorrow = new Date(today);
tomorrow.setDate(tomorrow.getDate() + 1);

const datePicker1 = flatpickr("#datePicker1", {
  dateFormat: "d-m-Y",
  altInput: true,
  altFormat: "d M Y",
  minDate: today,

  onChange: function (selectedDates) {
    if (selectedDates.length > 0) {
      const minCheckout = new Date(selectedDates[0]);
      minCheckout.setDate(minCheckout.getDate() + 1);

      datePicker2.set("minDate", minCheckout);
    } else {
      datePicker2.set("minDate", tomorrow);
    }
  }
});

const datePicker2 = flatpickr("#datePicker2", {
  dateFormat: "d-m-Y",
  altInput: true,
  altFormat: "d M Y",
  minDate: tomorrow,

  onChange: function (selectedDates) {
    if (selectedDates.length > 0) {
      const maxCheckin = new Date(selectedDates[0]);
      maxCheckin.setDate(maxCheckin.getDate() - 1);

      datePicker1.set("maxDate", maxCheckin);
    } else {
      datePicker1.set("maxDate", null);
    }
  }
});
