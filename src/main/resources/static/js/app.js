const tableBody = $('#table-body');
let number;

window.onload = function () {
    this.printValueToConsole();
};

tableBody.on('click', 'td', function() {
    let col = $( this ).parent().children().index( $( this ) );
    let row = $( this ).parent().parent().children().index($(this).parent());
    changeOppositeValue(col, row);
});

function printValueToConsole() {
    number = $('#fields-create').val();
    this.createTableHeadings(number);
    this.createTableBody(number);
}

function createTableHeadings(number) {
    tableBody.empty();
    let markup = '<tr><th></th>';
    for(let i = 1; i <= number; i++) {
        markup += '<th>' + i + '</th>';
    }
    markup += '</tr>';
    tableBody.append(markup);
}

function createTableBody(number) {
    for(let i = 1; i <= number; i++) {
        let markup = '<tr>';
        for(let j = 1; j <= number; j++) {
            if(j === 1) {
                markup += '<td id="table-first">' + i + '</td>';
            }
            if(j === i) {
                markup += '<td style="text-align: center">—</td>';
            } else {
                markup += '<td>' + "<input class='form-control' type='number' id='fields-create' name='number-fields' min='0' value='0' required />" + '</td>';
            }
        }
        markup += '</tr>';
        tableBody.append(markup);
    }
}

function sendData() {
    let dataArray = [];
    tableBody.find( 'tr' ).each( function() {
        let data = [];
        for(let i = 1; i <= number; i++) {
            data.push($(this).find("td:eq(" + i + ") input[type='number']").val());
        }
        dataArray.push(data);
});
    console.log(dataArray.splice(1));
}

function changeOppositeValue(col, row) {
    tableBody.find("tr:eq(" + col + ") td:eq(" + row + ") input[type='number']").val(getTableFieldValue(col, row));
}

function getTableFieldValue(col, row) {
    return tableBody.find("tr:eq(" + row + ") td:eq(" + col + ") input[type='number']").val();
}