let counts = { adults: 2, children: 0 };


const MAX_ADULTS = 10;
const MIN_ADULTS = 1;

function toggleGuestDropdown() {
  document.getElementById('guestDropdown').style.display =
    document.getElementById('guestDropdown').style.display === 'block'
      ? 'none' : 'block';
}

function closeGuestDropdown() {
  document.getElementById('guestDropdown').style.display = 'none';
}

function increment(type) {
  if (type === 'adults' && counts.adults >= MAX_ADULTS) return;

  counts[type]++;
  updateDisplay();
}

function decrement(type) {
  if (type === 'adults' && counts.adults <= MIN_ADULTS) return;

  if(type === 'children' && counts.adults <= 0) return;

  counts[type]--;
  updateDisplay();
}

function updateDisplay() {
  updateHiddenFields();
  updateGuestUI();
}

function updateHiddenFields() {
  document.getElementById('adultsHidden').value = counts.adults;
  document.getElementById('childrenHidden').value = counts.children;
}

function updateGuestUI() {
  document.getElementById('adultsCount').textContent = counts.adults;
  document.getElementById('childrenCount').textContent = counts.children;

  document.getElementById('guestInput').value =
    `${counts.adults} adult${counts.adults > 1 ? 's' : ''} · ` +
    `${counts.children} child${counts.children !== 1 ? 'ren' : ''}`;
}

document.addEventListener("click", (e) => {
  const dropdown = document.getElementById("guestDropdown");
  const input = document.getElementById("guestInput");

  if (!dropdown.contains(e.target) && e.target !== input) {
    dropdown.style.display = "none";
  }
});
