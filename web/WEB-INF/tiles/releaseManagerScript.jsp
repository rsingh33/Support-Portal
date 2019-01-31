<script type="text/javascript">

    $(document).ready(function () {
        $('#myTable').DataTable({responsive: true});
        $('#myTableProd').DataTable({responsive: true});
        $('#myTableSit').DataTable({responsive: true});
        $('#myTableUat').DataTable({responsive: true});

        $("a.delete").click(function (e) {
            if (!confirm('Are you sure you want to delete?')) {
                e.preventDefault();
                return false;
            }
            return true;
        });
        countTimer();
    });



    function countTimer() {

        var deadlineCounter = sqlToJsDate('${deadlineDate}');

        console.log("logging: " + deadlineCounter);
        // Set the date we're counting down to

        var countDownDate = new Date(deadlineCounter).getTime();


        // Update the count down every 1 second....
        var x = setInterval(function () {

            // Get todays date and time
            var now = new Date().getTime();

            // Find the distance between now and the count down date
            var distance = countDownDate - now;

            // Time calculations for days, hours, minutes and seconds
            var days = Math.floor(distance / (1000 * 60 * 60 * 24));
            var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((distance % (1000 * 60)) / 1000);

            // Display the result in the element with id="demo"
            document.getElementById("counter").innerHTML = days + "d " + hours + "h "
                + minutes + "m " + seconds + "s ";

            // If the count down is finished, write some text
            if (distance < 0) {
                clearInterval(x);
                document.getElementById("counter").innerHTML = "EXPIRED";
            }
        }, 1000);
    }


    function sqlToJsDate(sqlDate) {

        //sqlDate in SQL DATETIME format ("yyyy-mm-dd hh:mm:ss.ms")

        var sqlDateArr1 = sqlDate.split("-");

        //format of sqlDateArr1[] = ['yyyy','mm','dd hh:mm:ms']

        var sYear = sqlDateArr1[0];

        var sMonth = (Number(sqlDateArr1[1]) - 1).toString();

        var sqlDateArr2 = sqlDateArr1[2].split(" ");

        //format of sqlDateArr2[] = ['dd', 'hh:mm:ss.ms']
        var sDay = sqlDateArr2[0];


        //format of sqlDateArr3[] = ['hh','mm','ss.ms']

        var sHour = '12';

        var sMinute = '00';

        //format of sqlDateArr4[] = ['ss','ms']

        var sSecond = '00';
        var sMillisecond = '00';


        return new Date(sYear, sMonth, sDay, sHour, sMinute, sSecond, sMillisecond);

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

</script>