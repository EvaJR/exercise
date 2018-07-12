var baseUrl = "http://localhost:8080/api/airports/"


$(document).ready(function() {

        $('#airportTable').DataTable( {
                "order": [[ 0, "asc" ]],
                "ajax": {
                        url: baseUrl,
                        dataSrc: ''
                    },
                "columns": [
                    { "data": "name" }
                ]
         } );


    // Functionality for interaction when clicking on rows of the table
        $('#airportTable tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                deselect();
                $(this).addClass('selected');
                var table = $('#airportTable').DataTable();
                var data = table.row(this).data();
                apiGetSingleAirport(data.id);
                $('#airportModal').modal('toggle');
            }
        });

} );

$("#addAiportButton").click(function() {

            var jsonObject = {
                id: $("#id").val(),
                name: $("#name").val()

            };
             $.ajax({
                    contentType : "application/json",
                     // waar moet hij de request op uitvoeren
                     url : baseUrl,
                     // type actie
                     type : "post",
                     data: JSON.stringify(jsonObject),
                     // als de actie lukt, voer deze functie uit

                 });
//                 location.reload();
          });

function apiGetSingleAirport(id){
         var api = baseUrl + id;
         $.get(api, function(data){
             if (data){
                 fillUpdateDiv(data);
             }
         });
     }

function fillUpdateDiv(airport){

    console.log(airport);
    $("#btndelete").attr('onclick', 'submitDelete(' + airport.id + ');');
    $("#btnsubmit").attr('onclick', 'submitEdit(' + airport.id + ');');
    document.getElementById("modal-title").innerHTML="Edit airport";
    $("#modalName").val(airport.name);

    $("#confirmbutton").css('display', 'inline-block');
    deleteID = airport.id;
    var elem = '<button type="button" class="btn btn-danger" onclick="submitDelete();">Confirm delete</button>';
    $('#confirmbutton').popover({
        animation:true,
        content:elem,
        html:true,
        container: airportModal
    });
}

function deselect(){
    $('#airportTable tr.selected').removeClass('selected');
    document.getElementById("airportForm").reset();
}


function submitEdit(id){
// shortcut for filling the formData as a JavaScript object with the fields in the form
    console.log("Formdata");
    var formData = $("#airportForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    console.log(formData);

    for(var key in formData){
        if(formData[key] == "" || formData == null) delete formData[key];
    }
    $.ajax({
        url:baseUrl + id,
        type:"put",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        success: getData,
        error: function(error){
            displayError(error);
        }
    });
    deselect();
    $('#airportModal').modal('toggle');
}


function getData() {
      var api = baseUrl;
        $.get(api, function(data){
            if (data){
                setData(data);
            }
        });
}

function setData(data){
    $("#airportTable").DataTable().clear();
    $("#airportTable").DataTable().rows.add(data);
    $("#airportTable").DataTable().columns.adjust().draw();
}

function submitDelete(){
    console.log("Deleting");
    var formData = $("#airportForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    $.ajax({
        url:baseUrl + deleteID,
        type:"delete",
        data: JSON.stringify(formData),
        success: getData,
        contentType: "application/json; charset=utf-8"
    });

    $('#airportModal').modal('toggle');
    deselect();
}