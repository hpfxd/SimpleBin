const editor = CodeMirror.fromTextArea($("#pasteContent")[0], {
	lineNumbers: true,
	readOnly: true,
	indentUnit: 4
});

editor.setSize("100%", "85vh");

function pasteInit(modeName) {
	if (!modeName || modeName === "Plain Text") return;
	const mode = CodeMirror.findModeByName(modeName);

	$.getScript(`/static/js/mode/${mode.mode}/${mode.mode}.js`, () => {
		console.log("Mode loaded, setting.");
		editor.setOption("mode", mode.mimes ? mode.mimes[0] : mode.mime);
	});
}
