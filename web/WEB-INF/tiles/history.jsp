
<script>
$('#myDiv').append(
$('<pre>').text(
JSON.stringify(parsedJson, null, '  ')
)
);
</script>
<pre id=" myDiv" >${history}</pre>