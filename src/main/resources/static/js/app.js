const tableBody = $( "#table-body" );
let number;

window.onload = function () {
    createTableFields();
};

tableBody.on( "click", "td", function() {
    let col = $( this ).parent().children().index( $( this ) );
    let row = $( this ).parent().parent().children().index( $( this ).parent());
    changeOppositeValue( col, row );
});

function createTableFields() {
    number = $( '#fields-create' ).val();
    tableBody.empty();
    createTableHeadings( number );
    createTableBody( number );
}

function createTableHeadings(number) {
    let markup = "<tr><th></th>";
    for(let i = 1; i <= number; i++) {
        markup += "<th>" + i + "</th>";
    }
    markup += "</tr>";
    tableBody.append( markup );
}

function createTableBody(number) {
    for(let i = 1; i <= number; i++) {
        let markup = "<tr>";
        for(let j = 1; j <= number; j++) {
            if(j === 1) {
                markup += "<td id=\"table-first\">" + i + "</td>";
            }
            if(j === i) {
                markup += "<td style=\"text-align: center\">—</td>";
            } else {
                markup += "<td> <input class=\"form-control\" type=\"number\" id=\"fields-create\" name=\"number-fields\" min=\"0\" value=\"0\" required /> </td>";
            }
        }
        markup += "</tr>";
        tableBody.append( markup );
    }
}

function sendData(beginningValue, endingValue) {
    $.ajax({
        url: "http://localhost:8080/api/shortest-path",
        type: "post",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        processData: false,
        data: JSON.stringify( getTableFieldData(beginningValue, endingValue) ),
        success: function ( data ) {
            if(data.sum === 2147483647) {
                displayErrorAlert("Kelias neįmanomas.")
            } else {
                displaySuccessAlert(pathString(data));
            }
            console.log(data);
        },
        error: function () {
            console.log( "Something went wrong" );
        }
    });
}

function changeOppositeValue(col, row) {
    tableBody.find("tr:eq(" + col + ") td:eq(" + row + ") input[type=\"number\"]").val(getTableFieldValue(col, row));
}

function getTableFieldValue(col, row) {
    return tableBody.find("tr:eq(" + row + ") td:eq(" + col + ") input[type=\"number\"]").val();
}

function getTableFieldData(beginningValue, endingValue) {
    let dataArray = {
        beginningCoordinate: beginningValue,
        endingCoordinate: endingValue,
        numberOfValues: number
    };
    let coordinates = [];
    tableBody.find( "tr" ).each( function() {
        let coordinate = [];
        for(let i = 1; i <= number; i++) {
            let value = $( this ).find( "td:eq(" + i + ") input[type=\"number\"]" ).val();
            if ( value === undefined ) {
                coordinate.push( 0 );
            } else {
                coordinate.push( parseInt(value) );
            }
        }
        coordinates.push(coordinate);
    });
    dataArray.coordinates = coordinates.slice(1);
    console.log( dataArray );
    return dataArray;
}

function displayErrorAlert(message) {
    let errorMessage = "<div class=\"alert alert-danger\" role=\"alert\" id=\"alert-message\">\n" +
        message + "\n" +
        "</div>";
    $( "#alert" ).append(errorMessage);
}

function displaySuccessAlert(message) {
    let successMessage = "<div class=\"alert alert-success\" role=\"alert\" id=\"alert-message\">\n" +
        message + "\n" +
        "</div>";
    $( "#alert" ).append(successMessage);
}

function removeAlert() {
    $( "#alert-message" ).remove();
}

function checkForDataValidity() {
    let beginningValue = $( "#beginning-coordinate" ).val();
    let endingValue = $( "#ending-coordinate" ).val();
    removeAlert();

    if(beginningValue === endingValue) {
        displayErrorAlert("Koordinatės negali sutapti!");
    }
    else if( beginningValue > number || endingValue > number ) {
        displayErrorAlert("Blogai įvestos koordinatės");
    }
    else {
        sendData(beginningValue, endingValue);
    }
}

function pathString(data) {
    let successMessage = "Trumpiausio kelio nuėjimo tvarka: ";
    data.path.forEach(function(message){
        successMessage += message + " ";
    });
    return successMessage += ". Trumpiausio kelio suma: " + data.sum;
}