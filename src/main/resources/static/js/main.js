function main() {
    $('#killme').click(function() {
        if (confirm('Sure???')) {
            location.href = './api/shutdown';
        }
    });
}

$(main);
