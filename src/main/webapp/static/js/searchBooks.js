function searchBooks() {
    let keyword = $('#searchInput').val();
    let container = document.getElementById('searchResultsContainer');
    let submit = document.getElementById('submitSearch');
    if (keyword.trim() !== "") {
        $.ajax({
            type: 'GET',
            url: '/books/search',
            data: {keyword: keyword},
            dataType: 'json',
            success: function (data) {
                if (data != null) {
                    container.style.borderTop = '1px solid #9b9a9a';
                    $('#searchResultsContainer').empty();
                    $.each(data, function (index, book) {
                        $('#searchResultsContainer').append('<div class="bookDiv" ' +
                            'onclick="redirectToBookController(' + book.book_id + ')">' +
                            '§  ' + book.name + '</div>')
                    });
                    submit.disabled = false;
                }
            },
            error: function () {
                $('#searchResultsContainer').empty();
                submit.disabled = true;
                console.error('Error while searching books');
            }
        });

    } else {
        container.style.borderTop = 'none';
        submit.disabled = true;
        $('#searchResultsContainer').empty();
    }
}

function redirectToBookController(bookId) {
    window.location.href = "/books/" + bookId;
}