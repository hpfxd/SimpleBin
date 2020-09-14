const editor = CodeMirror.fromTextArea($("#pasteContent")[0], {
	lineNumbers: true,
	indentUnit: 4
});

CodeMirror.modeInfo.forEach((mode) => {
	$("#pasteLanguage").append(`<option name="${mode.mode}" ${mode.mode === "null" ? "selected" : ""}>${mode.name}</option>`);
});

const loadedModes = [];
let selectedMode = "Plain Text";
$("#pasteLanguage").on("change", () => {
	const name = $("#pasteLanguage").val();
	const mode = CodeMirror.findModeByName(name);
	selectedMode = mode.name;

	const cb = () => {
		console.log("Mode loaded, setting.");
		editor.setOption("mode", mode.mimes ? mode.mimes[0] : mode.mime);
	};

	if (!loadedModes.includes(mode.mode)) {
		loadedModes.push(mode.mode);

		$.getScript(`/static/js/mode/${mode.mode}/${mode.mode}.js`, cb);
	} else {
		cb();
	}
});

$("#pasteForm").submit((event) => {
	event.preventDefault();

	const fail = (reason) => {
		$.toast({
			type: "error",
			title: "Error",
			content: reason,
			delay: 10000
		});
	};

	$.ajax({
		url: "/api/paste/new",
		method: "POST",
		data: JSON.stringify({
			name: $("pasteTitle").val(),
			syntax: selectedMode,
			content: editor.getValue()
		}),
		contentType: "application/json",
		dataType: "json"
	}).then((response) => {
		if (response.error) {
			fail(response.error + ": " + response.message);
		} else {
			$.toast({
				type: "success",
				title: "Success!",
				content: "Your paste was uploaded! Redirecting...",
				delay: 1000
			});
			setTimeout(() => {
				window.location.href = "/" + response.id;
			}, 500);
		}
	}).catch((err) => {
		fail(err);
	});
});

// https://stackoverflow.com/a/26230865/9920121
[].forEach.call(document.querySelectorAll("code"), function($code) {
	var lines = $code.textContent.split("\n");

	if (lines[0] === "")
	{
		lines.shift()
	}

	var matches;
	var indentation = (matches = /^[\s\t]+/.exec(lines[0])) !== null ? matches[0] : null;
	if (!!indentation) {
		lines = lines.map(function(line) {
			line = line.replace(indentation, "")
			return line.replace(/\t/g, "    ")
		});

		$code.textContent = lines.join("\n").trim();
	}
});