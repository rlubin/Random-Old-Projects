function newFile() {
  document.getElementById("textArea").value = "";
}

//got the code for savefile from http://cssdeck.com/labs/7bx7mmcm
function saveFile() {
  var textToWrite = document.getElementById("textArea").value;
	var textFileAsBlob = new Blob([textToWrite], {type:'text/plain'});
	var fileNameToSaveAs = "DigitalPaper";

	var downloadLink = document.createElement("a");
	downloadLink.download = fileNameToSaveAs;
	downloadLink.innerHTML = "Download File";
	if (window.webkitURL != null)
	{
		// Chrome allows the link to be clicked
		// without actually adding it to the DOM.
		downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
	}
	else
	{
		// Firefox requires the link to be added to the DOM
		// before it can be clicked.
		downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
		downloadLink.onclick = destroyClickedElement;
		downloadLink.style.display = "none";
		document.body.appendChild(downloadLink);
	}

	downloadLink.click();
}
