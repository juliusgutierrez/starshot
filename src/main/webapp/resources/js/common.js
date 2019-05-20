/**
 * Created by juliusgutierrez on 19/05/2019.
 */
$(document).ready(function () {

  var contextPath = $("#contextPath").val();
  var table = $('#employee-table').DataTable({
    "searching" : false,
    "ordering": false,
    "processing": true,
    "ajax": {
      "url": contextPath + "/employee/search"
    },
    columns: [
      {
        data: 'userId',
        render: function (data, type, row) {

        return "<button class='btn btn-info btn-sm' id='edit-btn'>"
            + "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>"
            + " Edit </button>";
      }},
      {data: 'userId'},
      {data: 'fullName'},
      {
        data: 'timeIn',
        render: function (data, type, row) {
          var dateTime = moment(data).format("MM/DD/YYYY hh:mm A");
          return dateTime;
        }
      },
      {
        data: 'timeOut',
        render: function (data, type, row) {
          var dateTime = moment(data).format("MM/DD/YYYY hh:mm A");
          return dateTime;
        }
      },
      {
        data: 'active',
        render: function (data, type, row) {
          return (data === true) ? "Active" : "Not Active";
        }
      }
    ]
  });

  var clearErrorMsg = function() {
    //clear error msg
    $(".error-div small").text("");
  }

  //trigger searching of employee
  $("#search-btn").on('click', function () {
    var name = $('#name').val();
    var active = $('#status').val();
    var url = contextPath + '/employee/search?name=' + name + "&active=" + active;
    table.ajax.url(url).load();
  });

  //trigger edit show modal
  $('#employee-table').on('click', 'button', function () {
    var tr = this.closest('tr');
    var data = table.row(tr).data();

    //fill the details
    $("#userId").val(data.userId);
    $("#fullname").val(data.fullName);

    $('select[name=active]').val(data.active.toString());
    $('.select-div .selectpicker').selectpicker('refresh');

    $('#timeInDate').datetimepicker({
      date : data.timeIn
    });
    $('#timeOutDate').datetimepicker({
      date : data.timeOut
    });

    clearErrorMsg();
    $("#myModal").modal('show');

  });

  //delete employee tracker detail
  $("#delete-btn").on('click', function() {
    var id = $("#userId").val();

    $.ajax({
      url: contextPath + "/employee/delete/" + id,
      success : function(result) {
        $("#myModal").modal('toggle');
        $("#myNotifModal").modal('show');
        table.ajax.reload();
      }
    })
  });

  //save employee tracker detail
  $("#save-btn").on('click', function() {
    var value = $('.select-div .bootstrap-select .filter-option').text();
    var active = (value === "active");

    //clear the error msg
    clearErrorMsg();
    $.ajax({
      url: contextPath + "/employee/save",
      type: 'POST',
      data: $(".employee-form").serialize(),
      success : function(result) {
        $("#myModal").modal('toggle');
        $("#myNotifModal").modal('show');
        table.ajax.reload();
      },
      error : function (xhr, ajaxOptions, thrownError) {
        $(".error-div").removeClass("hide");
        $(".error-div small").append(xhr.responseJSON.message);
      }
    })
  });

  $(document).ajaxStart(function(){
    $(".loader").removeClass("hide");
    $(".loader").css("display", "block");
  });

  $(document).ajaxComplete(function(){
    setTimeout(function() {
      $(".loader").hide();
    }, 1000);
  });


  $("#create-btn").on('click', function() {
    //clear the details
    $("#userId").val("");
    $("#fullname").val("");
    $('.select-div .selectpicker').selectpicker('refresh');
    $('#timeInDate').datetimepicker({date : new Date()});
    $('#timeOutDate').datetimepicker({date : new Date()});
    $("#myModal").modal('show');
  });

});