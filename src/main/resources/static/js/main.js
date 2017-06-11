function main() {
    $('#killme').click(function() {
        if (confirm('Sure???')) {
            $.get('./api/shutdown');
        }
    });
}

$(main);
