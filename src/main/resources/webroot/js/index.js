const editor = CodeMirror.fromTextArea($("#pasteContent")[0], {
	lineNumbers: true,
	indentUnit: 4
});

CodeMirror.modeInfo.forEach((mode) => {
	$("#pasteLanguage").append(`<option name="${mode.mode}" ${mode.mode === "null" ? "selected" : ""}>${mode.name}</option>`);
});

const loadedModes = [];
$("#pasteLanguage").on("change", () => {
	const name = $("#pasteLanguage").val();
	const mode = CodeMirror.findModeByName(name);

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

// https://stackoverflow.com/a/26230865/9920121
[].forEach.call(document.querySelectorAll('code'), function($code) {
	var lines = $code.textContent.split('\n');

	if (lines[0] === '')
	{
		lines.shift()
	}

	var matches;
	var indentation = (matches = /^[\s\t]+/.exec(lines[0])) !== null ? matches[0] : null;
	if (!!indentation) {
		lines = lines.map(function(line) {
			line = line.replace(indentation, '')
			return line.replace(/\t/g, '    ')
		});

		$code.textContent = lines.join('\n').trim();
	}
});