
<script>


/*


Block right-click

Block F12

Block Ctrl+Shift+I (Inspect)

Block Ctrl+U (View Source)

*/

document.addEventListener('contextmenu', e => e.preventDefault());
document.onkeydown = function(e) {
    if (e.keyCode == 123) { // F12
        return false;
    }
    if (e.ctrlKey && e.shiftKey && e.keyCode == 73) { // Ctrl+Shift+I
        return false;
    }
    if (e.ctrlKey && e.keyCode == 85) { // Ctrl+U
        return false;
    }
};

</script>