<script type="text/javascript">

    $(document).ready(function () {
        $('#myTable').DataTable();
        $("a.delete").click(function (e) {
            if (!confirm('Are you sure you want to delete?')) {
                e.preventDefault();
                return false;
            }
            return true;
        });
    });


    function filter() {
        // Declare variables
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }

    }

    /* // below is pagination code////////////////////
      $(function () {
          // Selectors for future use
          var myTable = "#myTable";
          var myTableBody = myTable + " tbody";
          var myTableRows = myTableBody + " tr";
          var myTableColumn = myTable + " th";

          // Starting table state
          function initTable() {
              $(myTableBody).attr("data-pageSize", 5);
              $(myTableBody).attr("data-firstRecord", 0);
              $('#previous').hide();
              $('#next').show();

                       // Start the pagination
              paginate(parseInt($(myTableBody).attr("data-firstRecord"), 10),
                  parseInt($(myTableBody).attr("data-pageSize"), 10));
          }




          // Heading click
          $(myTableColumn).click(function () {

              // Start the pagination
              paginate(parseInt($(myTableBody).attr("data-firstRecord"), 10),
                  parseInt($(myTableBody).attr("data-pageSize"), 10));
          });

          // Pager click
          $("a.paginate").click(function (e) {
              e.preventDefault();
              var tableRows = $(myTableRows);
              var tmpRec = parseInt($(myTableBody).attr("data-firstRecord"), 10);
              var pageSize = parseInt($(myTableBody).attr("data-pageSize"), 10);

              // Define the new first record
              if ($(this).attr("id") == "next") {
                  tmpRec += pageSize;
              } else {
                  tmpRec -= pageSize;
              }
              // The first record is < of 0 or > of total rows
              if (tmpRec < 0 || tmpRec > tableRows.length) return

              $(myTableBody).attr("data-firstRecord", tmpRec);
              paginate(tmpRec, pageSize);
          });

          // Paging function
          var paginate = function (start, size) {
              var tableRows = $(myTableRows);
              var end = start + size;
              // Hide all the rows
              tableRows.hide();
              // Show a reduced set of rows using a range of indices.
              tableRows.slice(start, end).show();
              // Show the pager
              $(".paginate").show();
              // If the first row is visible hide prev
              if (tableRows.eq(0).is(":visible")) $('#previous').hide();
              // If the last row is visible hide next
              if (tableRows.eq(tableRows.length - 1).is(":visible")) $('#next').hide();
          }

          // Table starting state
          initTable();

      });
  */
    /*  function filterExcel() {
          // Declare variables
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("myInput");
          filter = input.value.toUpperCase();
          table = document.getElementById("myExcel");
          tr = table.getElementsByTagName("tr");

          // Loop through all table rows, and hide those who don't match the search query
          for (i = 0; i < tr.length; i++) {
              td = tr[i].getElementsByTagName("td")[0];
              if (td) {
                  txtValue = td.textContent || td.innerText;
                  if (txtValue.toUpperCase().indexOf(filter) > -1) {
                      tr[i].style.display = "";
                  } else {
                      tr[i].style.display = "none";
                  }
              }
          }

      }*/



</script>