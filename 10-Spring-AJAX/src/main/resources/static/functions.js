function listBooks() {
    const table = document.getElementById('bookTable');

    function createNode(elem) {
        return  document.createElement(elem);
    }

    fetch('/book/all')
        .then((response) => response.json())
        .then((bookDto) => bookDto
            .map(function (bookDto) {
                let tr = createNode('tr')
                let tdId = createNode('td')
                let tdTitle = createNode('td')
                let tdAuthor = createNode('td')
                let tdGenre = createNode('td')
                let tdAction = createNode('td')

                tdId.innerHTML = bookDto.id;
                tdTitle.innerHTML = bookDto.title;
                tdAuthor.innerHTML = bookDto.authorLastName + " " + bookDto.authorFirstName;
                tdGenre.innerHTML = bookDto.genre;
                tdAction.innerHTML =
                    "<ul> <form action=/book/save/" + bookDto.id + "><button type=\"submit\">update</button></form>" +
                    "<ul> <button onclick=deleteBook(" + bookDto.id + ")>delete</button></ul>" +
                    "<ul> <form action=/comment/all/" + bookDto.id + "><button type=\"submit\">comments</button></form>";

                tr.append(tdId, tdTitle, tdAuthor, tdGenre, tdAction)
                table.appendChild(tr);
            })
        );
}

function deleteBook(id) {

    fetch(`/book/delete/` + id, {
        method: 'DELETE'
    })
        .then(response => {
            location.reload()
        })
        .then(response => {
            listBooks();
        });
}
