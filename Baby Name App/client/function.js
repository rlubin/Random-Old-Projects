function suggest_onclick() {
  console.log("---")
  console.log("SUGGEST BUTTON CLICKED")

  let first_name = document.getElementById("first-name").value.trim()
  let middle_name = document.getElementById("middle-name").value.trim()
  let last_name = document.getElementById("last-name").value

  if (first_name === "" || !(/^[a-zA-Z-]+$/.test(first_name)) || (middle_name !== "" && !(/^[a-zA-Z-]+$/.test(middle_name))) || first_name.length > 20 || middle_name.length > 20) {
    console.log("INVALID name")
    alert("Invalid name\nFirst name must be included\nNames can only include letters or hyphens\nLimited to 20 characters per name")
  }
  else {
    console.log("VALID name")
    console.log(first_name + " " + middle_name + " " + last_name)

    if(middle_name === "") {
      alert(first_name + " " + last_name + " suggested")
    }
    else {
      alert(first_name + " " + middle_name + " " + last_name + " suggested")
    }

    let name = {
      first: first_name,
      middle: middle_name,
      last: last_name
    }
    let nameJSON = JSON.stringify(name)
    console.log(nameJSON)
    $.post("add_name", nameJSON, function() {
      console.log("POST ADD NAME SENT")
    })

    document.getElementById("first-name").value = ""
    document.getElementById("middle-name").value = ""
    document.getElementById("last-name").value = "Lubin"
  }
}

function view_onclick() {
  console.log("---")
  console.log("VIEW BUTTON CLICKED")

  $.get("view_database", function (response) {
    console.log("POST VIEW DATABASE SENT")
    console.log(response)
    let text = ""
    for(let i = 0; i < response.names.length; i++) {
      text += response.names[i] + "<br>"
    }
    document.getElementById("data").innerHTML = text
  })

}