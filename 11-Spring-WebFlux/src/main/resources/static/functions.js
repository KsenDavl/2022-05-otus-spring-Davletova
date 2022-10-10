function listBooks() {
    const table = document.getElementById('bookTable');

    function createNode(elem) {
        return document.createElement(elem);
    }

    fetch('/book/all')
        .then((response) => response.json())
        .then((book) => book
            .map(function (book) {
                let tr = createNode('tr')
                let tdId = createNode('td')
                let tdTitle = createNode('td')
                let tdAuthor = createNode('td')
                let tdGenre = createNode('td')
                let tdAction = createNode('td')

                tdId.innerHTML = book.id;
                tdTitle.innerHTML = book.title;
                tdAuthor.innerHTML = book.author;
                tdGenre.innerHTML = book.genre;
                tdAction.innerHTML =
                    "<ul> <form action=/save/" + book.id + "><button type=\"submit\">update</button></form>" +
                    "<ul> <form action=/comment/all/" + book.id + "><button type=\"submit\">comments</button></form>";

                tr.append(tdId, tdTitle, tdAuthor, tdGenre, tdAction)
                table.appendChild(tr);
            })
        );
}
