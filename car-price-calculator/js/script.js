/**
 * calculates the total cost of the car loan
 * and displays it to the screen
 */
function calculate() {
  let loan = parseFloat(document.getElementById('loan').value).toFixed(0)
  let apr = parseFloat(document.getElementById('apr').value).toFixed(2)
  let time = parseFloat(document.getElementById('time').value).toFixed(0)
  let period = document.getElementById('period').value
  let months
  if(period == 'months') {
    months = time
  } else {
    months = time * 12
  }
  // if year is checked then months = months * 12
  // console.log(loan)
  // console.log(apr)
  // console.log(months)
  // check for valid number
  // if yes add row to table
  // else alert user to invalue input
  if(loan >= 0 && apr >= 0 && months >= 0) {
    let years = months / 12
    let total_cost = loan * (1 + ((apr / 100) * years))
    let percentage = (total_cost / loan) * 100
    document.getElementById('t-loan').innerHTML = loan + ' $'
    document.getElementById('t-apr').innerHTML = apr + ' %'
    document.getElementById('t-months').innerHTML = months
    document.getElementById('t-years').innerHTML = parseFloat(months / 12).toFixed(2)
    document.getElementById('t-percentage').innerHTML = parseFloat(percentage).toFixed(2) + ' %'
    document.getElementById('t-total_cost').innerHTML = parseFloat(total_cost).toFixed(2) + ' $'
    document.getElementById('output').style.display = 'block'
  } else {
    alert('Invalid input')
  }
}